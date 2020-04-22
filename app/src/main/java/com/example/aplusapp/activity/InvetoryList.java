package com.example.aplusapp.activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.aplusapp.R;
import com.example.aplusapp.db.repos.InventoryRepository;
import com.example.aplusapp.model.Inventory;

public class InvetoryList extends Fragment{

    private Button update;
    private Button delete;
    private EditText iid;
    private EditText iname;
    private EditText iquantity;
    private EditText isalesprice;
    private EditText icategory;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.inventory_table, container, false);

        update= rootView.findViewById(R.id.clientimain);
        delete= rootView.findViewById(R.id.clientimain1);
        iid = rootView.findViewById(R.id.icode);
        iname = rootView.findViewById(R.id.iname);
        iquantity= rootView.findViewById(R.id.iqty);
        isalesprice = rootView.findViewById(R.id.iprice);
        icategory = rootView.findViewById(R.id.icat);

        update.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String idInput = iid.getEditableText().toString().trim();
                String nameInput = iname.getEditableText().toString().trim();
                String qtyInput = iquantity.getText().toString().trim();
                String priceInput = isalesprice.getEditableText().toString().trim();
                String categoryInput = icategory.getEditableText().toString().trim();

                InventoryRepository inventoryrepod =new InventoryRepository(getActivity().getApplication());
                Inventory imodeld = new Inventory(Integer.parseInt(idInput),nameInput,Integer.parseInt(qtyInput),priceInput,categoryInput,true);
                inventoryrepod.removeInventory(imodeld.getID());

            }
        });

        return rootView;

    }
}
