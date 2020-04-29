package com.example.aplusapp.adepter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplusapp.R;
import com.example.aplusapp.model.Order;
import com.example.aplusapp.model.OrderMod;
import com.example.aplusapp.model.User;
import com.google.android.material.button.MaterialButton;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrdrViewHolder>{

    private List<OrderMod> orderList;

    public class OrdrViewHolder extends RecyclerView.ViewHolder {

        public TextView OrderId, OrderName, OrderQty;
        public MaterialButton BtnOrdrEdit, BtnOrdrDelete;

        public OrdrViewHolder(View view) {
            super(view);
            OrderId = (TextView) view.findViewById(R.id.OrdrID);
            OrderName = (TextView) view.findViewById(R.id.OrdrName);
            OrderQty = (TextView) view.findViewById(R.id.OrdrQty);

            BtnOrdrEdit = (MaterialButton) view.findViewById(R.id.buttonOrderEdit);
            BtnOrdrDelete = (MaterialButton) view.findViewById(R.id.buttonOrderDelete);
        }

        public void onClick(View v){

            BtnOrdrEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(v.getContext(),"Order is Edited",Toast.LENGTH_SHORT).show();
                }
            });

            BtnOrdrDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(v.getContext(),"Order is Deleted",Toast.LENGTH_SHORT).show();
                }
            });

        }

    }

    public OrderAdapter(List<OrderMod> _orderList) {
        orderList = _orderList;
    }

    @NonNull
    @Override
    public OrdrViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_order_manage, parent, false);

        return new OrdrViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull OrdrViewHolder holder, int position) {
        OrderMod orderMod = orderList.get(position);
        holder.OrderId.setText(Integer.toString(orderMod.getOid()));
        holder.OrderName.setText(orderMod.getOitem());
        holder.OrderQty.setText(Integer.toString(orderMod.getOqty()));
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }
}
