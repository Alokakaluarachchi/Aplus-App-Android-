package com.example.aplusapp.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.aplusapp.R;
import com.example.aplusapp.adepter.OrderAdapter;
import com.example.aplusapp.db.repos.OrderRepository;
import com.example.aplusapp.model.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends Fragment{

    private RecyclerView RecyclerView1;

    private List<Order> orderList = new ArrayList<>();
    private OrderAdapter oAdapter;

    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_order, container, false);

        RecyclerView1 = view.findViewById(R.id.recyclerView2);

        oAdapter = new OrderAdapter(orderList, getContext(),getActivity().getApplication());
        RecyclerView.LayoutManager oLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        RecyclerView1.setLayoutManager(oLayoutManager);
        RecyclerView1.setItemAnimator(new DefaultItemAnimator());
        RecyclerView1.addItemDecoration(new DividerItemDecoration(container.getContext(), LinearLayoutManager.VERTICAL));
        RecyclerView1.setAdapter(oAdapter);

        LoadOrderList();

        return view;
    }

    private void LoadOrderList(){

        orderList.clear();
        OrderRepository orderRepository = new OrderRepository(getActivity().getApplication());
        orderRepository.fetchAllOrders().observe(getViewLifecycleOwner(), new Observer<List<Order>>() {
            @Override
            public void onChanged(List<Order> orders) {
                orderList.addAll(orders);
                oAdapter.notifyDataSetChanged();
            }
        });
    }
}
