package com.example.aplusapp.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.aplusapp.model.Cashier;

@Dao
public interface CashierDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertCashier(Cashier cashier);

    @Update
    void updateCashier(Cashier cashier);

    @Delete
    void deleteCashier(Cashier cashier);

    @Query("delete from cashier")
    void removeAll();

    @Query("delete from cashier where ID = :ID")
    void removeByID(int ID);
}
