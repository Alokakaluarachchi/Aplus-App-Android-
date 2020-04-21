package com.example.aplusapp.db.repos;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.aplusapp.db.GeneralRoomDatabase;
import com.example.aplusapp.db.dao.RoleDao;
import com.example.aplusapp.model.Role;

import java.util.List;

public class RoleRepository {
    private RoleDao roleDao;
    private LiveData<List<Role>> allRoles;

    public  RoleRepository(Application application){
        GeneralRoomDatabase db = GeneralRoomDatabase.getDatabase(application);
        roleDao = db.roleDao();
        allRoles = roleDao.fetchAll();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<Role>> fetchAllRoles() {
        return allRoles;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    public void insertRole(Role role) {
        GeneralRoomDatabase.databaseWriteExecutor.execute(() -> {
            roleDao.insertRole(role);
        });
    }

    public void insertRole(List<Role> roles) {
        GeneralRoomDatabase.databaseWriteExecutor.execute(() -> {
            roleDao.insertRole(roles);
        });
    }

    public void updateUser(Role role){
        GeneralRoomDatabase.databaseWriteExecutor.execute(() -> {
            roleDao.updateRole(role);
        });
    }

    public Role findByName(String name){
        Role role = roleDao.findByName(name);
        return role;
    }

}
