package com.example.aplusapp.db.dao;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import com.example.aplusapp.model.Inventory;

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

}
