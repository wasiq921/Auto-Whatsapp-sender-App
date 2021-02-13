package com.first.liptonapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBManager extends SQLiteOpenHelper {
    private static final String dbname = " Users.db";
    public DBManager( Context context) {
        super(context, dbname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String query="create table tbl_users (id integer primary key autoincrement, name text, mobile text, email text, age text, friend text)";
        sqLiteDatabase.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS tbl_users");
        onCreate(sqLiteDatabase);
    }

    public String addRecord (String name, String mobile, String email, String age, String friend){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("mobile", mobile);
        cv.put("email", email);
        cv.put("age", age);
        cv.put("friend", friend);

        long res = sqLiteDatabase.insert("tbl_users",null,cv);
        if(res==-1)
            return "Failed";
        else
            return "Successfully Inserted";

    }
    public void viewRecord(SQLiteDatabase sqLiteDatabase){
        String query = "SELECT * FROM Users.db";
         sqLiteDatabase.execSQL(query);

    }



}
