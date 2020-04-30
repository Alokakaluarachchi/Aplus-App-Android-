package com.example.aplusapp.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.aplusapp.model.Order;

import java.util.List;

@Dao
public interface OrderDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertOrder(Order order);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertOrder(List<Order> order);

    @Update
    void updateOrder(Order order);

    @Delete
    void deleteOrder(Order order);

    @Query("SELECT * FROM `order` ORDER BY ID ")
    List<Order> getOrder();

    @Query("SELECT * FROM `order` ORDER BY ID")
    LiveData<List<Order>> fetchAll();

    @Query("delete from `order`")
    void removeAll();

    @Query("delete from `order` where ID = :ID")
    void removeByID(int ID);

}
