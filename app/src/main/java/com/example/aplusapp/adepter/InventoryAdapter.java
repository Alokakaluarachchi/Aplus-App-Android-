package com.example.aplusapp.adepter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplusapp.model.Inventory;
import com.example.aplusapp.model.User;

import java.util.List;

public class InventoryAdapter extends RecyclerView.Adapter<InventoryAdapter.MyViewHolder>{
    private List<Inventory> inventoryList;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


}

