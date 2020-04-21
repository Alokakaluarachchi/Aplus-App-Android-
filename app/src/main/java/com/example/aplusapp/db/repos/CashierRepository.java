package com.example.aplusapp.db.repos;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.aplusapp.db.GeneralRoomDatabase;
import com.example.aplusapp.db.dao.CashierDao;
import com.example.aplusapp.model.Cashier;
import com.example.aplusapp.model.Inventory;
import com.example.aplusapp.model.Order;

import java.util.List;

public class CashierRepository {

    private CashierDao cashierDao;

    public CashierRepository(Application application){

        GeneralRoomDatabase db = GeneralRoomDatabase.getDatabase(application);
        cashierDao = db.cashierDao();
    }


    public void insertCashier(Cashier cashier){

        GeneralRoomDatabase.databaseWriteExecutor.execute(() -> {cashierDao.insertCashier(cashier);});

    }

    public void removeCashier(int id){

        GeneralRoomDatabase.databaseWriteExecutor.execute(() -> {cashierDao.removeByID(id);});
    }

    public void updateCashier(Cashier cashier){

        GeneralRoomDatabase.databaseWriteExecutor.execute(() -> {cashierDao.updateCashier(cashier);});
    }

}
