package com.example.aplusapp.db.repos;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.aplusapp.db.GeneralRoomDatabase;
import com.example.aplusapp.db.dao.InventoryDao;
import com.example.aplusapp.model.Customers;
import com.example.aplusapp.model.Inventory;
import com.example.aplusapp.model.Users;

import java.util.List;


public class InventoryRepository {
    private InventoryDao inventoryDao;
    private LiveData<List<Inventory>> allInventories;

    public  InventoryRepository(Application application){
        GeneralRoomDatabase db =  GeneralRoomDatabase.getDatabase(application);
        inventoryDao = db.inventoryDao();
        allInventories = inventoryDao.fetchAll();

    }
    public LiveData<List<Inventory>> fetchAllUsers() {
        return allInventories;
    }

    public void insertInventory(Inventory inventory) {
        GeneralRoomDatabase.databaseWriteExecutor.execute(() -> {
            inventoryDao.insertInventory(inventory);
        });
    }

    public void removeInventory(int id){
        GeneralRoomDatabase.databaseWriteExecutor.execute(() -> {
            inventoryDao.removeByID(id);
        });
    }

    public void updateInventory(Inventory inventory){
        GeneralRoomDatabase.databaseWriteExecutor.execute(() -> {
            inventoryDao.updateInventory(inventory);
        });
    }
    public Inventory findByID(int id){
        Inventory inventory = inventoryDao.findByID(id);
        return inventory;
    }


}
