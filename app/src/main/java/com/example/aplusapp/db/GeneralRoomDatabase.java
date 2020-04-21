package com.example.aplusapp.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.aplusapp.db.dao.CashierDao;
import com.example.aplusapp.db.dao.InventoryDao;
import com.example.aplusapp.db.dao.OrderDao;
import com.example.aplusapp.db.dao.RoleDao;
import com.example.aplusapp.db.dao.UserDao;
import com.example.aplusapp.model.Cashier;
import com.example.aplusapp.model.Inventory;
import com.example.aplusapp.model.Order;
import com.example.aplusapp.model.Role;
import com.example.aplusapp.model.Users;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Users.class, Inventory.class, Order.class, Cashier.class, Role.class}, version = 1, exportSchema = false)
public abstract class GeneralRoomDatabase extends RoomDatabase {

    public abstract UserDao userDao();
    public abstract InventoryDao inventoryDao();
    public abstract OrderDao orderDao();
    public abstract CashierDao cashierDao();
    public abstract RoleDao roleDao();

    private static volatile GeneralRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static GeneralRoomDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (GeneralRoomDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), GeneralRoomDatabase.class, "aplus_db").build();
                }
            }
        }
        return INSTANCE;
    }
}
