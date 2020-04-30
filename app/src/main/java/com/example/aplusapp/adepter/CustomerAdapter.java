package com.example.aplusapp.adepter;

import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplusapp.R;
import com.example.aplusapp.activity.Customeradd;
import com.example.aplusapp.activity.Customerupdate;
import com.example.aplusapp.activity.HomeActivity;
import com.example.aplusapp.activity.InventoryListActivity;
import com.example.aplusapp.activity.MainActivity;
import com.example.aplusapp.activity.UserListActivity;
import com.example.aplusapp.db.repos.CustomerRepository;
import com.example.aplusapp.model.Customers;
import com.example.aplusapp.model.Inventory;
import com.example.aplusapp.model.User;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.List;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.MyViewHolder>{
    private List<Customers> customerList;
    private Context context;
    private Application application;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener {
        public TextView txtName,txtNIC;
        public Button btnEdit, btnDelete;
        public MyViewHolder(@NonNull View view) {
            super(view);
            txtName = (TextView) view.findViewById(R.id.txtName);
            txtNIC = (TextView) view.findViewById(R.id.txtNIC);
            btnEdit = view.findViewById(R.id.btnUserEdit);
            btnDelete = view.findViewById(R.id.btnUserDelete);

            view.setOnClickListener(this);
            btnEdit.setOnClickListener(this);
            btnDelete.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == btnEdit.getId()){
                //context.startActivity(new Intent(context, Customerupdate.class));
                FragmentManager manager = ((AppCompatActivity)context).getSupportFragmentManager();

                manager.beginTransaction().replace(R.id.fragmentContainer, new Customerupdate()).commit();

                //Fragment newFragment = new Customerupdate();
                //FragmentTransaction transaction = manager.beginTransaction();

            }
            else if (v.getId() == btnDelete.getId()){
                new MaterialAlertDialogBuilder(v.getContext()).setTitle("Confirm for remove")
                        .setMessage("Are you sure remove the customer("+txtName.getText().toString()+") ?")
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                CustomerRepository cusrep = new CustomerRepository(application);
                                int id = Integer.parseInt(txtName.getTag().toString());
                                cusrep.removeCustomer(id);

                                notifyDataSetChanged();;


                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();

            }else {
                Toast.makeText(v.getContext(), "ROW Pressd = " + String.valueOf(getAdapterPosition()), Toast.LENGTH_SHORT).show();
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

