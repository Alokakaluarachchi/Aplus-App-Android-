package com.example.aplusapp.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.aplusapp.model.Customer;
import com.example.aplusapp.model.Customers;

import java.util.List;

@Dao
public interface CustomerDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertCustomer(Customers customer);

    @Update
    void updateCustomer(Customers customer);

    @Delete
    void deleteCustomer(Customers customer);

    @Query("SELECT * FROM customer ORDER BY Fristname asc")
    LiveData<List<Customers>> fetchAll();

    @Query("select * from customer where ID = :ID")
    Customers findByID(int ID);

    @Query("delete from customer")
    void removeAll();

    @Query("delete from customer where ID = :ID")
    void removeByID(int ID);
}
