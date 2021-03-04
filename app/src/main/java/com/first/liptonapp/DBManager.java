package com.first.liptonapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DBManager extends SQLiteOpenHelper {
    public static final String dbname = " Users.db";
    public DBManager( Context context) {
        super(context, dbname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String query="create table tbl_users (id integer primary key autoincrement,name text, mobile text, email text, age text, friend text)";
        // update table fields for data and time
        sqLiteDatabase.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS tbl_users");
        onCreate(sqLiteDatabase);
    }

    public boolean addRecord (String name, String mobile, String email, String age, String friend){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put("name", name);
        cv.put("mobile", mobile);
        cv.put("email", email);
        cv.put("age", age);
        cv.put("friend", friend);

        long res = sqLiteDatabase.insert("tbl_users",null,cv);
        if(res==-1)
            return false;
        else
            return true;

    }

    public Cursor getAllData(){
        //get all data
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor result=sqLiteDatabase.rawQuery("SELECT * FROM tbl_users",null);
        return result;

    }



}
