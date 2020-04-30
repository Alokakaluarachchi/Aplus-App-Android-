package com.example.aplusapp.adepter;

import android.app.Application;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplusapp.R;
import com.example.aplusapp.activity.CircularProgressBarDialog;
import com.example.aplusapp.activity.InventoryListActivity;
import com.example.aplusapp.activity.UserEditPopupActivity;
import com.example.aplusapp.activity.UserListActivity;
import com.example.aplusapp.db.repos.InventoryRepository;
import com.example.aplusapp.db.repos.UserRepository;
import com.example.aplusapp.model.Inventory;
import com.example.aplusapp.model.User;
import com.example.aplusapp.network.APIClient;
import com.example.aplusapp.network.UserApiService;
import com.example.aplusapp.utils.SharedConst;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.List;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class InventoryAdapter extends RecyclerView.Adapter<InventoryAdapter.MyViewHolder>{

    private final ClickListener listener;
    private Application application;
    private FragmentManager fragmentManager;
    private String jwtToken;
    private Retrofit retrofit;
    private UserApiService apiService;
    private List<Inventory> inventoryList;
    private CircularProgressBarDialog circularProgressBarDialog;
    InventoryListActivity inventoryListActivity;


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        public TextView inventoryName , inventoryId, inventoryQty, inventoryPrice;
        public Button btnEdit, btnDelete;

        private WeakReference<ClickListener> listenerRef;
        private CallBackListener callBackListener;

        public MyViewHolder(@NonNull View view) {
            super(view);

            listenerRef = new WeakReference<>(listener);
            retrofit = APIClient.getClient(); //initialize Retrofit Client
            apiService = retrofit.create(UserApiService.class); //Register the Api Service

            inventoryName = (TextView) view.findViewById(R.id.inventoryName);
            inventoryId = (TextView) view.findViewById(R.id.inventoryId);
            inventoryQty=(TextView) view.findViewById(R.id.inventoryQty);
            inventoryPrice=(TextView) view.findViewById(R.id.inventoryPrice);

            btnEdit = view.findViewById(R.id.btnInventoryEdit);
            btnDelete = view.findViewById(R.id.btnInventoryDelete);

            circularProgressBarDialog = new CircularProgressBarDialog();

            view.setOnClickListener(this);
            btnEdit.setOnClickListener(this);
            btnDelete.setOnClickListener(this);

            }

        @Override
        public void onClick(View v) {
            if (v.getId() == btnEdit.getId()) {

            }
        }




        @Override
        public boolean onLongClick(View v) {
            return false;
        }
    }
    public InventoryAdapter(List<Inventory> _inventoryList, Application _application, String _jwtToken, FragmentManager _fragmentManager, InventoryListActivity _inventoryListActivity, ClickListener listener) {
        this.listener = listener;
        inventoryList = _inventoryList;
        application = _application;
        jwtToken = _jwtToken;
        fragmentManager = _fragmentManager;
        inventoryListActivity = _inventoryListActivity;
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
        holder.inventoryId.setText(Integer.toString(inventory.getID()));
        holder.inventoryQty.setText(Integer.toString(inventory.getQuantity()));
        holder.inventoryPrice.setText(inventory.getSalesPrice());

    }

    @Override
    public int getItemCount() {
        return inventoryList.size();
    }
}

