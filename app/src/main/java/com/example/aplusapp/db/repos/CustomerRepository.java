package com.example.aplusapp.db.repos;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.aplusapp.db.GeneralRoomDatabase;
import com.example.aplusapp.db.dao.CustomerDao;
import com.example.aplusapp.model.Customer;
import com.example.aplusapp.model.Customers;

import java.util.List;

public class CustomerRepository {
    private CustomerDao customerDao;
    private LiveData<List<Customers>> allCustomerss;

    public CustomerRepository(Application application) {
        GeneralRoomDatabase db = GeneralRoomDatabase.getDatabase(application);
        customerDao = db.customerDao();
        allCustomerss = customerDao.fetchAll();
    }
    public LiveData<List<Customers>> fetchAllUses() {
        return allCustomerss;
    }
    public void insertCustomer(Customers customer) {
        GeneralRoomDatabase.databaseWriteExecutor.execute(() -> {
            customerDao.insertCustomer(customer);
        });
    }
    public void removeCustomer(int id){
        GeneralRoomDatabase.databaseWriteExecutor.execute(() -> {
            customerDao.removeByID(id);
        });
    }
    public void updateCustomer(Customers customer){
        GeneralRoomDatabase.databaseWriteExecutor.execute(() -> {
            customerDao.updateCustomer(customer);
        });
    }

}
