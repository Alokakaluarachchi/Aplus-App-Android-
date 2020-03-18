package com.example.aplusapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.aplusapp.R;
import com.example.aplusapp.adepter.UserAdapter;
import com.example.aplusapp.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserListActivity extends AppCompatActivity {

    private List<User> userList = new ArrayList<>();
    private RecyclerView recyclerView;
    private UserAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new UserAdapter(userList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);

        LoadUserList();
    }

    private void LoadUserList(){
        User user = new User("Shalitha", "Administrator");
        userList.add(user);
        user = new User("John", "Manager");
        userList.add(user);
        user = new User("Martin", "Administrator");
        userList.add(user);
        user = new User("Jonny", "Employee");
        userList.add(user);
        user = new User("Peter", "Administrator");
        userList.add(user);
        user = new User("Shalitha Senanayaka", "Administrator");
        userList.add(user);
        user = new User("John", "Manager");
        userList.add(user);
        user = new User("Martin", "Administrator");
        userList.add(user);
        user = new User("Jonny", "Employee");
        userList.add(user);
        user = new User("Peter", "Administrator");
        userList.add(user);
        user = new User("Shalitha Senanayaka", "Administrator");
        userList.add(user);

        mAdapter.notifyDataSetChanged();

    }
}
