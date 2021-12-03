package com.example.myshop;

import android.content.Context;

import com.example.myshop.dao.DataBaseRoom;
import com.example.myshop.dao.ProductRoomDao;

public class Room {
    public  static ProductRoomDao dao ;

    public static ProductRoomDao getDao(Context context) {
        dao= DataBaseRoom.getInstance(context).productRoomDao();
        return dao;
    }
}
