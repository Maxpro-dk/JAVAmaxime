package com.example.myshop.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myshop.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductDao {
    private  DataBaseHelper dataBaseHelper ;
    public  static String TABLE_NAME="products";

    public ProductDao(Context context){
        dataBaseHelper= DataBaseHelper.getIntence(context);

    }

    public long insert(Product product){
       SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put("name",product.getName());
        content.put("description",product.getDescription());
        content.put("price",product.getPrice());
        content.put("stock_quantity",product.getStockQuantity());
        content.put("stock_alert",product.getStockAlert());
        return  db.insert(TABLE_NAME,null,content);

    }


    @SuppressLint("Range")
    public Product getOne(int id){
        SQLiteDatabase database= dataBaseHelper.getReadableDatabase();
        String[] colum= new String[]{"id","name","description","price","stock_quantity","stock_alert"};
        String whereN=  "id=?";
        String[] whereValue = new String[]{id+""};
        @SuppressLint("Recycle")
        Cursor cursor = database.query(TABLE_NAME,colum,whereN,whereValue,"","","");
        if (cursor!=null && cursor.moveToNext() ){
            Product product= new Product();
            product.setId(cursor.getInt(cursor.getColumnIndex("id")));
            product.setName(cursor.getString(cursor.getColumnIndex("name")));
            product.setDescription(cursor.getString(cursor.getColumnIndex("description")));
            product.setStockQuantity(cursor.getInt(cursor.getColumnIndex("stock_quantity")));
            product.setPrice(cursor.getFloat(cursor.getColumnIndex("price")));
            product.setStockAlert(cursor.getInt(cursor.getColumnIndex("stock_alert")));
            return  product;
        }
        cursor.close();


        return null ;
    }
    @SuppressLint("Range")
    public List<Product> findAll(){

        List<Product> list = new ArrayList<>();
        SQLiteDatabase database= dataBaseHelper.getReadableDatabase();
        String[] colum= new String[]{"id","name","description","price","stock_quantity","stock_alert"};

        @SuppressLint("Recycle")
        Cursor cursor = database.query(TABLE_NAME,colum,"",null,"","","");

        if (cursor!=null){
            while (cursor.moveToNext()){
                Product product= new Product();
                product.setId(cursor.getInt(Math.max(cursor.getColumnIndex("id"),0)));
                product.setName(cursor.getString(Math.max(cursor.getColumnIndex("name"),0)));
                product.setDescription(cursor.getString(Math.max(cursor.getColumnIndex("description"),0)));
                product.setStockQuantity(cursor.getInt(Math.max(cursor.getColumnIndex("stock_quantity"),0)));
                product.setPrice(cursor.getFloat(Math.max(cursor.getColumnIndex("price"),0)));
                product.setStockAlert(cursor.getInt(Math.max(cursor.getColumnIndex("stock_alert"),0)));
                list.add(product);
            }
            cursor.close();
        }




        return list;
    }
    public Product update(int id , Product product){
        return null;
    }
    public boolean delete(int id){
        SQLiteDatabase database= dataBaseHelper.getWritableDatabase();
        return database.delete(TABLE_NAME,"id=?",new String[]{id+""})>0;

    }

}
