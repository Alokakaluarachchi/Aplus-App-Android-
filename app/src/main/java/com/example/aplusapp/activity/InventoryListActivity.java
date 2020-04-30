package com.example.aplusapp.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.aplusapp.R;
import com.example.aplusapp.adepter.ClickListener;
import com.example.aplusapp.adepter.InventoryAdapter;
import com.example.aplusapp.adepter.UserAdapter;
import com.example.aplusapp.db.repos.CustomerRepository;
import com.example.aplusapp.db.repos.InventoryRepository;
import com.example.aplusapp.model.Customers;
import com.example.aplusapp.model.Inventory;
import com.example.aplusapp.model.User;

import java.util.ArrayList;
import java.util.List;


public class InventoryListActivity extends Fragment {

    private List<Inventory> inventoryList = new ArrayList<>();
    private RecyclerView recyclerViewInventory;
    private InventoryAdapter iAdapter;
    private TextView btnInventory;

    private SharedPreferences preferences;
    private String token;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_inventory_list, container, false);
        recyclerViewInventory = (RecyclerView) rootView.findViewById(R.id.recycler_view_inventory);
        btnInventory = rootView.findViewById(R.id.btnAddInventory);

        iAdapter = new InventoryAdapter(inventoryList,getActivity().getApplication(), token, getChildFragmentManager(), InventoryListActivity.this , new ClickListener() {
            @Override
            public void onPositionClicked(int position) {

            }

            @Override
            public void onLongClicked(int position) {

            }
        });

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());

        recyclerViewInventory.setLayoutManager(mLayoutManager);
        recyclerViewInventory.setItemAnimator(new DefaultItemAnimator());
        recyclerViewInventory.addItemDecoration(new DividerItemDecoration(container.getContext(), LinearLayoutManager.VERTICAL));
        recyclerViewInventory.setAdapter(iAdapter);

        LoadInventoryList();


        btnInventory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new InventoryAdd()).commit();

            }
        });

        return rootView;

    }


    private void LoadInventoryList() {
        inventoryList.clear();
        InventoryRepository inventoryRepository = new InventoryRepository(getActivity().getApplication());
        inventoryRepository.fetchAllUsers().observe(getViewLifecycleOwner(), new Observer<List<Inventory>>() {
            @Override
            public void onChanged(List<Inventory> inventory) {
                inventoryList.addAll(inventory);
                iAdapter.notifyDataSetChanged();
            }

        });
    }


}