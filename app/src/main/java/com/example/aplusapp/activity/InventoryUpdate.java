package com.example.aplusapp.activity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aplusapp.R;
import com.example.aplusapp.db.repos.InventoryRepository;
import com.example.aplusapp.model.Inventory;


public class InventoryUpdate extends Fragment {
    private Button update;
    private EditText uid;
    private EditText uname;
    private EditText uquantity;
    private EditText usalesprice;
    private EditText ucategory;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle   savedInstanceState) {
        View view = inflater.inflate(R.layout.update_inventory, container, false);
        update = view.findViewById(R.id.update);
        uid = view.findViewById(R.id.icode);
        uname = view.findViewById(R.id.uname);
        uquantity= view.findViewById(R.id.uqty);
        usalesprice = view.findViewById(R.id.uprice);
        ucategory = view.findViewById(R.id.ucat);

        update.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String idInput = uid.getText().toString().trim();
                String nameInput = uname.getText().toString().trim();
                String qtyInput = uquantity.getText().toString().trim();
                String priceInput = usalesprice.getText().toString().trim();
                String categoryInput = ucategory.getText().toString().trim();

                if(idInput.isEmpty()){
                    uid.setError("Field can't be empty");
                    return ;
                }

                if(nameInput.isEmpty()){
                    uname.setError("Field can't be empty");
                    return ;
                }

                if(qtyInput.isEmpty()){
                    uquantity.setError("Field can't be empty");
                    return ;
                }

                if(priceInput.isEmpty()){
                    usalesprice.setError("Field can't be empty");
                    return ;
                }

                if(categoryInput.isEmpty()){
                    ucategory.setError("Field can't be empty");
                    return ;
                }

                InventoryRepository inventoryrepo = new InventoryRepository(getActivity().getApplication());
                Inventory imodel = new Inventory(Integer.parseInt(idInput),nameInput,Integer.parseInt(qtyInput),priceInput,categoryInput,true);
                inventoryrepo.updateInventory(imodel);

                Toast.makeText(getActivity(),"Successfully Updated",Toast.LENGTH_SHORT).show();

            }
        });

        return view;
    }
}
