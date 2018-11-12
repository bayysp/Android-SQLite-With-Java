package com.example.administator.resepku;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class DatabaseHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "resepku.db";
    public static final String TABLE_NAME = "AKUN_USER";

    public static final String COL_1 = "EMAIL";
    public static final String COL_2 = "USERNAME";
    public static final String COL_3 = "PASSWORD";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + TABLE_NAME +"(EMAIL TEXT,USERNAME TEXT PRIMARY KEY ,PASSWORD TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertRegister(String email,String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();


        contentValues.put(COL_1,email);
        contentValues.put(COL_2,username);
        contentValues.put(COL_3,password);

        long result = db.insert(TABLE_NAME,null,contentValues);

        if(result == -1){
            return false;
        }else
            return true;
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM "+TABLE_NAME,null);


        return result;
    }



    public boolean LoginAkun(String user,String pass){
        String[] columns = {COL_2};
        String selection = COL_2 + " = ?" + " AND " + COL_3 + " = ?";
        String[] selectionArgs = {user,pass};

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();


        if(cursorCount > 0){
            return true;
        }else
            return false;
    }
}
