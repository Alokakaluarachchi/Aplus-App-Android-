package com.example.aplusapp.db.repos;

import android.app.Application;

import com.example.aplusapp.db.GeneralRoomDatabase;
import com.example.aplusapp.db.dao.InventoryDao;
import com.example.aplusapp.model.Inventory;


public class InventoryRepository {
    private InventoryDao inventoryDao;

    public  InventoryRepository(Application application){
        GeneralRoomDatabase db =  GeneralRoomDatabase.getDatabase(application);
        inventoryDao = db.inventoryDao();

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

}
