package com.example.aplusapp.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.aplusapp.model.Role;

import java.util.List;

@Dao
public interface RoleDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertRole(Role role);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertRole(List<Role> roleList);

    @Update
    void updateRole(Role role);

    @Query("SELECT * FROM role ORDER BY Role asc")
    LiveData<List<Role>> fetchAll();


}
