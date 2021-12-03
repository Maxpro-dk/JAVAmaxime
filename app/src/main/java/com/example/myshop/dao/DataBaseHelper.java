package com.example.myshop.dao;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {
    private static final String  DB_NAME="project.db";
    private static final int DB_VERSION = 1;
    public static DataBaseHelper dataBaseHelper;
    private DataBaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public  static DataBaseHelper getIntence(Context context){
        if(dataBaseHelper==null) {
            dataBaseHelper= new DataBaseHelper(context);
        }
        return dataBaseHelper;
    }





    @Override
    public void onCreate(SQLiteDatabase db) {

               db.execSQL("CREATE TABLE products( id INTEGER PRIMARY KEY AUTOINCREMENT,name VARCHAR(100)," +
                       "description TEXT,price REAL DEFAULT 0," +
                       "stock_quantity INTEGER DEFAULT 0,stock_alert INTEGER DEFAULT 0)");
        Log.d("hdhdj", String.valueOf(new Throwable()));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
