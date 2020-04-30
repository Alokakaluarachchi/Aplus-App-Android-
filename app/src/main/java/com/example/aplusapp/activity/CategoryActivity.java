package com.example.aplusapp.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.example.aplusapp.R;

public class CategoryActivity extends Fragment {

    ConstraintLayout btnUsers;
    ConstraintLayout btnInventory;
    ConstraintLayout btnOrder;
    ConstraintLayout btnSales;
    ConstraintLayout btnCustomer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_category, container, false);

        btnUsers = rootView.findViewById(R.id.btnUsers);
        btnInventory = rootView.findViewById(R.id.btnInventory);
        btnOrder = rootView.findViewById(R.id.btnOrder);
        btnSales = rootView.findViewById(R.id.btnSales);
        btnCustomer = rootView.findViewById(R.id.imgrow3row1);

        btnUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new UserListActivity()).commit();
            }
        });

        btnInventory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new InventoryListActivity()).commit();
            }
        });
        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new OrderActivity()).commit();
            }
        });
        btnSales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new CashierActivity()).commit();
            }
        });
        btnCustomer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new CustomerListActivity()).commit();
            }
        });
        return rootView;
    }
}
