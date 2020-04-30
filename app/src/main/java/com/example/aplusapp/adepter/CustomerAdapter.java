package com.example.aplusapp.adepter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplusapp.R;
import com.example.aplusapp.activity.Customeradd;
import com.example.aplusapp.activity.Customerupdate;
import com.example.aplusapp.model.Customers;
import com.example.aplusapp.model.Inventory;
import com.example.aplusapp.model.User;

import java.util.List;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.MyViewHolder>{
    private List<Customers> customerList;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener {
        public TextView txtName,txtNIC;
        public Button btnEdit, btnDelete;
        public MyViewHolder(@NonNull View view) {
            super(view);
            txtName = (TextView) view.findViewById(R.id.txtName);
            txtNIC = (TextView) view.findViewById(R.id.txtNIC);
            btnEdit = view.findViewById(R.id.btnUserEdit);
            btnDelete = view.findViewById(R.id.btnUserDelete);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == btnEdit.getId()){
                context.startActivity();
            }
        }

        @Override
        public boolean onLongClick(View v) {
            return false;
        }
    }

    public CustomerAdapter(List<Customers> _customerList,Context _context) {
        customerList = _customerList;
        context = _context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.customer_list_row, parent, false);
        return new CustomerAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Customers customers = customerList.get(position);
        holder.txtName.setText(customers.getFristname());
        holder.txtNIC.setText(customers.getNIC());


    }

    @Override
    public int getItemCount() {
        return customerList.size();
    }


}

