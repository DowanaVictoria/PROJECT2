package com.example.myandroidapp;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

        public class DbHelper extends SQLiteOpenHelper {
            public DbHelper(Context context) {
                super(context, "MainActivity.db", null, 1);
            }

            @Override
            public void onCreate(SQLiteDatabase myDb) {

                myDb.execSQL("create Table users(username Text primary key, password Text,repassword Text,email Text)");
            }

            @Override
            public void onUpgrade(SQLiteDatabase myDb, int oldVersion, int newVersion) {
                myDb.execSQL("drop Table if exists users");
            }

            public Boolean insertData(String username, String password, String repassword,String email) {
                SQLiteDatabase myDb = this.getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                contentValues.put("username", username);
                contentValues.put("password", password);
                contentValues.put("repassword", repassword);
                contentValues.put("email", email);
                long result = myDb.insert("users", null, contentValues);
                if (result == -1) {
                    return false;
                } else {
                    return true;
                }
            }

            public Boolean checkusername(String username) {
                SQLiteDatabase myDb = this.getWritableDatabase();
                Cursor cursor = myDb.rawQuery("select * from users where username =?", new String[]{username});
                if (cursor.getCount() > 0) {
                    return true;
                } else {
                    return false;
                }
            }

            public Boolean checkusernamePassword(String username, String password) {
                SQLiteDatabase myDb = this.getWritableDatabase();
                Cursor cursor = myDb.rawQuery("select * from users where username =? and password =?", new String[]{username, password});
                if (cursor.getCount() > 0) {
                    return true;
                }
                else {
                    return false;
                }
            }

            public Boolean checkusernamePasswordemail(String username, String password, String email) {
                SQLiteDatabase myDb = this.getWritableDatabase();
                Cursor cursor = myDb.rawQuery("select * from users where username =? and password =? and email=?", new String[]{username, password ,email});
                if (cursor.getCount() > 0) {
                    return true;
                }
                else {
                    return false;
                }
            }
        }

