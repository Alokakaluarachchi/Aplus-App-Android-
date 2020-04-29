package com.example.aplusapp.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.TableLayout;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.aplusapp.R;
import com.example.aplusapp.adepter.OrderAdapter;
import com.example.aplusapp.db.repos.CashierRepository;
import com.example.aplusapp.db.repos.OrderRepository;
import com.example.aplusapp.model.Cashier;
import com.example.aplusapp.model.Order;
import com.example.aplusapp.model.OrderMod;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends Fragment{

    private Button btnOrderUpdate,btnOrderDelete;
    private RecyclerView RecyclerView1;

    private List<OrderMod> orderList = new ArrayList<>();
    private OrderAdapter oAdapter;

    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_order, container, false);

        btnOrderUpdate =view.findViewById(R.id.btnOrderUpdate);
        btnOrderDelete = view.findViewById(R.id.btnOrderDelete);

        RecyclerView1 = view.findViewById(R.id.recyclerView2);

        oAdapter = new OrderAdapter(orderList);
        RecyclerView.LayoutManager oLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        RecyclerView1.setLayoutManager(oLayoutManager);
        RecyclerView1.setItemAnimator(new DefaultItemAnimator());
        RecyclerView1.addItemDecoration(new DividerItemDecoration(container.getContext(), LinearLayoutManager.VERTICAL));
        RecyclerView1.setAdapter(oAdapter);

        LoadOrderList();

        btnOrderUpdate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                Toast.makeText(getActivity(),"Order is Updated",Toast.LENGTH_SHORT).show();
            }

            //      CashierRepository cashrepo = new CashierRepository(getActivity().getApplication());
            //      Cashier cmode = new Cashier(Integer.parseInt(IdInput),NameInput,Integer.parseInt(QtyInput),true);
            //      cashrepo.updateCashier(cmode);


        });

        btnOrderDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"Order is Deleted",Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private void LoadOrderList(){

        OrderMod ordermod = new OrderMod(001, "Mugs", 20);
        orderList.add(ordermod);

        ordermod = new OrderMod(002, "Plates", 10);
        orderList.add(ordermod);

        ordermod = new OrderMod(003, "Cups", 15);
        orderList.add(ordermod);

        ordermod = new OrderMod(004, "Spoons", 10);
        orderList.add(ordermod);

        oAdapter.notifyDataSetChanged();

    }
}
