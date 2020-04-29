package com.example.aplusapp.adepter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplusapp.R;
import com.example.aplusapp.model.Inventory;
import com.example.aplusapp.model.User;

import java.util.List;

public class InventoryAdapter extends RecyclerView.Adapter<InventoryAdapter.MyViewHolder>{
    private List<Inventory> inventoryList;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView inventoryName , inventoryId;
        public MyViewHolder(@NonNull View view) {
            super(view);
            inventoryName = (TextView) view.findViewById(R.id.inventoryName);
            inventoryId = (TextView) view.findViewById(R.id.inventoryId);
            }
    }

    public InventoryAdapter(List<Inventory> _inventoryList) {
        inventoryList = _inventoryList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.inventory_list, parent, false);
        return new InventoryAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Inventory inventory = inventoryList.get(position);
        holder.inventoryName.setText(inventory.getInventoryName());
        holder.inventoryId.setText(inventory.getID());

    }

    @Override
    public int getItemCount() {
        return inventoryList.size();
    }


}

