package com.example.myshop.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myshop.entity.Product;

import java.util.List;

@Dao
public interface ProductRoomDao {
    @Query("SELECT * FROM product")
    List<Product> findAll();

    @Query("SELECT * FROM product WHERE id IN (:idProduct)")
    List<Product> findAllbyId(int[] idProduct);
    @Query("SELECT * FROM product WHERE name like :search OR description like :search")
    List<Product> search(String search);
    @Insert
     void insertAll(Product... product);
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void  insert(Product product);
    @Delete
    void delete(Product product);
    @Update(onConflict =OnConflictStrategy.REPLACE)
    void modify(Product product);
    @Delete
    void  deleteAll(Product... products);


}
