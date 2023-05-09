package com.example.spaceshareproject;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBase3 extends SQLiteOpenHelper {


    public static final String DBNAME = "Login.db";

    // public static final String TABLENAME1 = "users2";
    public static final String TABLE = "Users";
    public static final String COL1 = "username";
    public static final String COL2 = "password";
    public static final String COLU3 ="lastname";
    public static final String COLU4 ="firstname";
    public static final String COLU5 = "phonenumber";

    public DataBase3(@Nullable Context context) {
        super(context, DBNAME, null, 1);
    }

    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1  ) {
        sqLiteDatabase.execSQL("drop Table if exists " + TABLE);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String statement = "CREATE TABLE Users(phonenumber TEXT, password TEXT, firstname TEXT ,username Text primary key, lastname TEXT   )";
      sqLiteDatabase.execSQL(statement);
       // sqLiteDatabase.execSQL("create Table " + TABLE + "(" + COL1 + " TEXT primary key, " + COL2 + " TEXT,  " +  COLU3 + " TEXT, " +  COLU4 + " TEXT, " +  COLU5 + " TEXT)  ");
    }

   // @Override

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

    public Boolean checkUsernamePassword(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from " + TABLE + " where " + COL1 + " = ? and " + COL2 + " = ?", new String[] {username,password});
        if(cursor.getCount()>0) return true;
        return false;
    }

}
