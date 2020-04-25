
package com.example.aplusapp.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.aplusapp.R;
import com.example.aplusapp.db.GeneralRoomDatabase;
import com.example.aplusapp.db.repos.CashierRepository;
import com.example.aplusapp.model.Cashier;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textfield.TextInputLayout;

public class CashierActivity extends Fragment {

    private EditText TextInputID;
    private EditText TextInputItemName;
    private EditText TextInputQty;
    private Button BtnPlaceOrder;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_cashier, container, false);

        TextInputID = view.findViewById(R.id.Text_input_Item_ID);
        TextInputItemName = view.findViewById(R.id.Text_input_Item_Name);
        TextInputQty = view.findViewById(R.id.Text_input_Qty);
        BtnPlaceOrder = view.findViewById(R.id.btnPlaceOrder);

        BtnPlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String IdInput = TextInputID.getText().toString().trim();
                String NameInput = TextInputItemName.getText().toString().trim();
                String QtyInput = TextInputQty.getText().toString().trim();

                if (IdInput.isEmpty()) {

                    TextInputID.setError("Field can't be Empty");
                    return;
                }

                if (NameInput.isEmpty()) {

                    TextInputItemName.setError("Field cannot be Empty");
                    return;
                }

                if (QtyInput.isEmpty()) {

                    TextInputQty.setError("Field cannot be Empty");
                    return;
                }

                CashierRepository cashrepo = new CashierRepository(getActivity().getApplication());
                Cashier cmode = new Cashier(Integer.parseInt(IdInput),NameInput,Integer.parseInt(QtyInput),true);
                cashrepo.insertCashier(cmode);

                    Toast.makeText(getActivity(),"Order is Placed",Toast.LENGTH_SHORT).show();


            }
        });

        return view;
    }
}