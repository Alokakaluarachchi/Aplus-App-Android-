package com.example.aplusapp.activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.aplusapp.R;
import com.example.aplusapp.db.repos.InventoryRepository;
import com.example.aplusapp.model.Inventory;

public class InvetoryList extends Fragment{

    private Button update;
    private Button delete;
    private Button add;
    private EditText iid;
    private EditText iname;
    private EditText iquantity;
    private EditText isalesprice;
    private EditText icategory;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.inventory_table, container, false);
        add=rootView.findViewById(R.id.clientadd);
        update= rootView.findViewById(R.id.clientimain);
        delete= rootView.findViewById(R.id.clientimain1);
        iid = rootView.findViewById(R.id.icode);
        iname = rootView.findViewById(R.id.iname);
        iquantity= rootView.findViewById(R.id.iqty);
        isalesprice = rootView.findViewById(R.id.iprice);
        icategory = rootView.findViewById(R.id.icat);

        delete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String idInput = iid.getText().toString().trim();
                String nameInput = iname.getText().toString().trim();
                String qtyInput = iquantity.getText().toString().trim();
                String priceInput = isalesprice.getText().toString().trim();
                String categoryInput = icategory.getText().toString().trim();

                InventoryRepository inventoryrepod =new InventoryRepository(getActivity().getApplication());
                Inventory imodeld = new Inventory(Integer.parseInt(idInput),nameInput,Integer.parseInt(qtyInput),priceInput,categoryInput,true);
                inventoryrepod.removeInventory(imodeld.getID());

                Toast.makeText(getActivity(),"Successfully deleted",Toast.LENGTH_SHORT).show();

            }
        });

        update.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new InventoryUpdate()).commit();

            }
        });

        add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new InventoryAdd()).commit();
            }
        });

        return rootView;

    }
}
