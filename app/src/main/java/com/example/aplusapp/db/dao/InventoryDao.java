package com.example.aplusapp.db.dao;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import com.example.aplusapp.model.Inventory;
import com.example.aplusapp.model.Users;

import java.util.List;

@Dao
public interface InventoryDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertInventory(Inventory inventory);

    @Update
    void updateInventory(Inventory inventory);

    @Delete
    void deleteInventory(Inventory inventory);

    @Query("delete from inventory")
    void removeAll();

    @Query("delete from inventory where ID = :ID")
    void removeByID(int ID);

    @Query("SELECT * FROM inventory ORDER BY InventoryName asc")
    LiveData<List<Inventory>> fetchAll();

    @Query("select * from inventory where ID = :ID")
    Inventory findByID(int ID);
}
