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
    public static final String DATABASE_NAME = "DataBase2.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "offices";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "office_name";
    private static final String COLUMN_PRICE = "office_price";
    private static final String COLUMN_SIZE = "office_size";
    public static final String COLUMN_image = "office_image";

    private static final String  COLUMN_Availability = "Availability";
    private static final String COLUMN_OwnerNAME = "OwnerName";
    private static final String COLUMN_seekerNAME = "seekerName";


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
                COLUMN_SIZE + " INTEGER , " + COLUMN_Availability+" TEXT,"+ COLUMN_OwnerNAME+" TEXT,"+ COLUMN_seekerNAME+" TEXT,"+
                COLUMN_image + " BLOB);";


        String table2 = "CREATE TABLE " +TABLE+"(phonenumber TEXT, password TEXT, firstname TEXT ,username Text primary key, lastname TEXT   )";
        db.execSQL(table2);

        db.execSQL(table1);




    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);

    }

    void addOffice(String name, Integer price, int size,String ownerName,String seekerName){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_PRICE, price);
        cv.put(COLUMN_SIZE, size);
        cv.put(COLUMN_OwnerNAME, ownerName);
        cv.put(COLUMN_seekerNAME, seekerName);
        String av="available";
        cv.put(COLUMN_Availability,av);
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
        cv.put(COLUMN_Availability,"available");
        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
        }

    }

    void deleteOneRow(String row_id) {


        SQLiteDatabase DB = this.getReadableDatabase();
        Cursor cursor = DB.rawQuery("SELECT " + COLUMN_Availability + " FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " =?", new String[]{row_id});
        if (cursor.moveToFirst()) {
            String availability = cursor.getString(0);
            if (availability.equals("available")) {
                SQLiteDatabase db = this.getWritableDatabase();
                long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
                if (result == -1) {
                    Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
                }

            } else Toast.makeText(context, "Sorry, can't delete it's rented.", Toast.LENGTH_SHORT).show();
        }
    }
    void deleteAllData(){

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }





    public boolean rent(String row_id,String seeker) {

        SQLiteDatabase DB = this.getReadableDatabase();
        Cursor cursor = DB.rawQuery("SELECT " + COLUMN_Availability + " FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " =?", new String[]{row_id});
        if (cursor.moveToFirst()) {
            String availability = cursor.getString(0);
            if (availability.equals("available")) {
                SQLiteDatabase db = this.getWritableDatabase();
                ContentValues cv = new ContentValues();
                cv.put(COLUMN_Availability, "not available");
                cv.put(COLUMN_seekerNAME, seeker);
                long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});

                if (result == -1) {
                    Toast.makeText(context, "failed to rent", Toast.LENGTH_SHORT).show();
                    return false;
                }
                return true;
            }
            Toast.makeText(context, "office not available", Toast.LENGTH_SHORT).show();
            return false;
        }
        Toast.makeText(context, "office not available", Toast.LENGTH_SHORT).show();
        return false;
    }



    public boolean  annnulment(String row_id) {

//add condition for duration
        SQLiteDatabase DB = this.getReadableDatabase();
        Cursor cursor = DB.rawQuery("SELECT " + COLUMN_Availability + " FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " =?", new String[]{row_id});

            String availability = cursor.getString(0);

                SQLiteDatabase db = this.getWritableDatabase();
                long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
                if (result == -1) {
                    Toast.makeText(context, "Failed to annnulment.", Toast.LENGTH_SHORT).show();
                    return false;
                } else {
                    Toast.makeText(context, "Successfully annnulment the contract!", Toast.LENGTH_SHORT).show();
                    return true;
                }



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

