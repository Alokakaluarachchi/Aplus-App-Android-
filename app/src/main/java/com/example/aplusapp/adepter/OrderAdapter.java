package com.example.aplusapp.adepter;

import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplusapp.activity.EditOrder;
import com.example.aplusapp.R;
import com.example.aplusapp.db.repos.OrderRepository;
import com.example.aplusapp.db.repos.UserRepository;
import com.example.aplusapp.model.Order;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrdrViewHolder>{

    private List<Order> orderList;
    private Context context;
    private Application application;

    public class OrdrViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener{

        public TextView OrderId, OrderName, OrderQty;
        public MaterialButton BtnOrdrEdit, BtnOrdrDelete;

        public OrdrViewHolder(View view) {
            super(view);
            OrderId = (TextView) view.findViewById(R.id.OrdrID);
            OrderName = (TextView) view.findViewById(R.id.OrdrName);
            OrderQty = (TextView) view.findViewById(R.id.OrdrQty);

            BtnOrdrEdit = (MaterialButton) view.findViewById(R.id.buttonOrderEdit);
            BtnOrdrDelete = (MaterialButton) view.findViewById(R.id.buttonOrderDelete);

            view.setOnClickListener(this);
            BtnOrdrEdit.setOnClickListener(this);
            BtnOrdrDelete.setOnClickListener(this);
        }

        public void onClick(View v){

            BtnOrdrEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (v.getId() == BtnOrdrEdit.getId()) {

                        context.startActivity(new Intent(context, EditOrder.class));
                        Toast.makeText(v.getContext(), "Edit button is clicked", Toast.LENGTH_SHORT).show();

                    } else if (v.getId() == BtnOrdrDelete.getId()) {

                        new MaterialAlertDialogBuilder(v.getContext()).setTitle("Confirm for remove")
                                .setMessage("Are you sure want to remove the Order(" + OrderName.getText().toString() + ") ?")
                                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        OrderRepository repo = new OrderRepository(application);
                                        int id = Integer.parseInt(OrderName.getTag().toString());
                                        repo.removeOrder(id);

                                        notifyDataSetChanged();
                                        //circularProgressBarDialog.dismiss();
                                    }

                                })
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                }).show();
                    }else {
                        Toast.makeText(v.getContext(),"ROW PRESSED = " + String.valueOf(getAdapterPosition()), Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }

        @Override
        public boolean onLongClick(View v) {
            return false;
        }
    }

    public OrderAdapter(List<Order> _orderList,Context _context,Application _application) {
        orderList = _orderList;
        context = _context;
        application = _application;

    }

    @NonNull
    @Override
    public OrdrViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_order_manage, parent, false);

        return new OrderAdapter.OrdrViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull OrdrViewHolder holder, int position) {
        Order order = orderList.get(position);
        holder.OrderId.setText(Integer.toString(order.getID()));
        holder.OrderName.setText(order.getItems());
        holder.OrderQty.setText(Integer.toString(order.getQty()));
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }
}
