package com.example.aplusapp.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.aplusapp.R;
import com.example.aplusapp.adepter.UserAdapter;
import com.example.aplusapp.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserListActivity extends Fragment {

    private List<User> userList = new ArrayList<>();
    private RecyclerView recyclerView;
    private UserAdapter mAdapter;
    private TextView btnReqAccount;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_user_list, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        btnReqAccount = rootView.findViewById(R.id.btnRequestAccount);

        mAdapter = new UserAdapter(userList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(container.getContext(), LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);

        LoadUserList();


        btnReqAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent( getActivity(), ReqAccountActivity.class));
            }
        });

        return rootView;

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
        user = new User("Anne", "Administrator");
        userList.add(user);
        user = new User("Rubble", "Manager");
        userList.add(user);
        user = new User("Ruchi", "Administrator");
        userList.add(user);
        user = new User("Advendeler", "Employee");
        userList.add(user);
        user = new User("Whincsky", "Administrator");
        userList.add(user);

        mAdapter.notifyDataSetChanged();

    }
}
