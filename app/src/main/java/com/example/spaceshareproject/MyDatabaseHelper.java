package com.example.spaceshareproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    public static final String DATABASE_NAME = "SpaceShare.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "offices";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "office_name";
    private static final String COLUMN_PRICE = "office_price";
    private static final String COLUMN_SIZE = "office_size";
    public static final String COLUMN_image = "office_image";





    public static final String TABLE = "Users";
    public static final String COL1 = "username";
    public static final String COL2 = "password";
    public static final String COLU3 ="lastname";
    public static final String COLU4 ="firstname";
    public static final String COLU5 = "phonenumber";


    MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String table1 = "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_NAME + " TEXT, " +
                        COLUMN_PRICE + " INTEGER, " +
                        COLUMN_SIZE + " INTEGER , " +
                COLUMN_image + " BLOB);";


        String table2 = "CREATE TABLE " +TABLE+"(phonenumber TEXT, password TEXT, firstname TEXT ,username Text primary key, lastname TEXT   )";
        db.execSQL(table2);

        db.execSQL(table1);




    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("drop Table if exists " + TABLE);
        onCreate(db);
    }

    void addOffice(String name, Integer price, int size){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_PRICE, price);
        cv.put(COLUMN_SIZE, size);
        long result = db.insert(TABLE_NAME,null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    void updateData(String row_id, String name, String price, String size){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_PRICE, price);
        cv.put(COLUMN_SIZE, size);

        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
        }

    }

    void deleteOneRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }




    public Boolean insertData(String phonenumber, String password,String firstname, String username , String lastname){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(COL1, username);
        contentValues.put(COL2, password);
        contentValues.put(COLU3 ,lastname);
        contentValues.put(COLU4 ,firstname);
        contentValues.put(COLU5,phonenumber);
        // contentValues.put(COL6, first);

        long result = MyDB.insert(TABLE, null, contentValues);
        if(result==-1)
            return false;
        return true;
    }

    public Boolean checkUsername(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from " + TABLE + " where " + COL1 + " = ?", new String[]{username});
        if (cursor.getCount() > 0) return true;
        return false;
    }

    public Boolean checkPassword(String password) {
        boolean valid = true;

        boolean letter;
        boolean digit ;
        int letters =0;
        int digits = 0;

        for (int i=0; i<password.length(); i++ ){

            letter =Character.isLetter(password.charAt(i)) ;
            digit =Character.isDigit(password.charAt(i)) ;
            if (digit)
                digits++;
            if (letter)
                letters++;
        }
        int specialChar= password.length()-( digits+letters);

        if ((password.length()<8) ||(letters<4) ||(digits<1) || (specialChar<1))
            valid=false;

        return valid;

    }
    public Boolean checkPhone(String phonenumber) {
        boolean valid = true;
        boolean digit ;
        int digits = 0;
        for (int i=0; i<phonenumber.length(); i++ ) {
            digit =Character.isDigit(phonenumber.charAt(i)) ;
            if (digit)
                digits++;
        }
        if (phonenumber.length()!=10 ||digits!=10 || phonenumber.charAt(0)!='0' )
            valid =false;
        return valid;
    }
    public Boolean checkUsernamePassword(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from " + TABLE + " where " + COL1 + " = ? and " + COL2 + " = ?", new String[] {username,password});
        if(cursor.getCount()>0) return true;
        return false;
    }

}


