package com.example.aplusapp.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import com.example.aplusapp.model.User;
import com.example.aplusapp.model.Users;

import java.util.List;

@Dao
public interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertUser(Users user);

    @Update
    void updateUser(Users user);

    @Delete
    void deleteUser(Users user);

    @Query("SELECT * FROM user ORDER BY UserName asc")
    LiveData<List<Users>> fetchAll();

    @Query("select * from user where ID = :ID")
    Users findByID(int ID);

    @Query("delete from user")
    void removeAll();

    @Query("delete from user where ID = :ID")
    void removeByID(int ID);
}
