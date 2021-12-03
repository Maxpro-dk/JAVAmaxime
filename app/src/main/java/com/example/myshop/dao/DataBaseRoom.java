package com.example.myshop.dao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.myshop.entity.Product;

@Database(entities= {Product.class}, version =4)
public  abstract class DataBaseRoom  extends RoomDatabase {
    private static  DataBaseRoom dataBaseRoom;

    public  static  DataBaseRoom getInstance(Context context)
    {
        if (dataBaseRoom==null){
            dataBaseRoom= Room.databaseBuilder(context,DataBaseRoom.class,"MyShop6").build();
        }
        return dataBaseRoom;
    }
    public abstract ProductRoomDao productRoomDao();
}
