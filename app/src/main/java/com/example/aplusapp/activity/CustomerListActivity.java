package com.example.aplusapp.activity;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplusapp.R;
import com.example.aplusapp.adepter.CallBackListener;
import com.example.aplusapp.adepter.CustomerAdapter;
import com.example.aplusapp.adepter.UserAdapter;
import com.example.aplusapp.db.repos.CustomerRepository;
import com.example.aplusapp.model.Customers;


import java.util.ArrayList;
import java.util.List;

public class CustomerListActivity extends Fragment implements CallBackListener {
    private List<Customers> customerList = new ArrayList<>();
    private RecyclerView recyclerView;
    private CustomerAdapter mAdapter;
    private TextView btnCustomer;

    private SharedPreferences pref;

    private String token;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.customerview, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        btnCustomer = rootView.findViewById(R.id.btnRequestAccount);

        mAdapter = new CustomerAdapter(customerList, getContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(container.getContext(), LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);

        LoadCustomerList();

        btnCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.fragmentContainer,new Customeradd()).commit();
            }
        });

        return rootView;
    }



    @Override
    public void onDismiss() {

    }

    public void LoadCustomerList(){
        customerList.clear();
        CustomerRepository customerRepo = new CustomerRepository(getActivity().getApplication());
        customerRepo.fetchAllUses().observe(getViewLifecycleOwner(), new Observer<List<Customers>>() {
            @Override
            public void onChanged(List<Customers> customers) {
                customerList.addAll(customers);
                mAdapter.notifyDataSetChanged();
            }
        });
    }
}
