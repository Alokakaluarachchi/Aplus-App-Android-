package com.example.aplusapp.db.repos;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.aplusapp.db.GeneralRoomDatabase_Impl;
import com.example.aplusapp.db.dao.UserDao;
import com.example.aplusapp.model.Users;

import java.util.List;

public class UserRepository {
    private UserDao userDao;
    private LiveData<List<Users>> allUsers;

    public  UserRepository(Application application){
        GeneralRoomDatabase_Impl db = (GeneralRoomDatabase_Impl) GeneralRoomDatabase_Impl.getDatabase(application);
        userDao = db.userDao();
        allUsers = userDao.fetchAll();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    LiveData<List<Users>> fetchAllUsers() {
        return allUsers;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    public void insertUser(Users user) {
        GeneralRoomDatabase_Impl.databaseWriteExecutor.execute(() -> {
            userDao.insertUser(user);
        });
    }
}
