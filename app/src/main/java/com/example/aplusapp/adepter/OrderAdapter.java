package com.example.aplusapp.adepter;

import android.icu.util.ValueIterator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplusapp.R;

public class OrderAdapter extends RecyclerView.Adapter {


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_cashier, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

     //   ((ListViewHolder) holder).blindView(position);

    }

    @Override
    public int getItemCount() {
        return 0;
    }


    private class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    private EditText inputItem_ID;
    private EditText inputItem_Name;
    private EditText input_Qty;


     public ListViewHolder(View itemView){

        super(itemView);
        inputItem_ID = (EditText) itemView.findViewById(R.id.Text_input_Item_ID);
        inputItem_Name = (EditText) itemView.findViewById(R.id.Text_input_Item_Name);
        input_Qty = (EditText) itemView.findViewById(R.id.Text_input_Qty);
        itemView.setOnClickListener(this);

     }

     public void onClick(View view){

     }
    }
}
