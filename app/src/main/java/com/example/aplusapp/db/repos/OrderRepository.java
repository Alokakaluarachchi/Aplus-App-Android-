package com.example.aplusapp.db.repos;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.aplusapp.db.GeneralRoomDatabase;
import com.example.aplusapp.db.dao.OrderDao;
import com.example.aplusapp.model.Order;
import com.example.aplusapp.model.Users;

import java.util.List;

public class OrderRepository {

    private OrderDao orderDao;
    private LiveData<List<Order>> allOrders;


    public OrderRepository(Application application){

        GeneralRoomDatabase db = GeneralRoomDatabase.getDatabase(application);
        orderDao = db.orderDao();
        allOrders = orderDao.fetchAll();
    }

    public void insertOrder(Order order){

        GeneralRoomDatabase.databaseWriteExecutor.execute(() -> {orderDao.insertOrder(order);});

    }

    public void removeOrder(int id){

        GeneralRoomDatabase.databaseWriteExecutor.execute(() -> {orderDao.removeByID(id);});
    }

    public void updateOrder(Order order){

        GeneralRoomDatabase.databaseWriteExecutor.execute(() -> {orderDao.updateOrder(order);});
    }
    public LiveData<List<Order>> fetchAllOrders() {
        return allOrders;
    }
}

