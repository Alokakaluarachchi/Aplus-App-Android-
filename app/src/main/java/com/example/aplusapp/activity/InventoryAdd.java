package com.example.aplusapp.activity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.aplusapp.R;

import java.util.regex.Pattern;

public class InventoryAdd extends Fragment {

    private static final Pattern ID_PATTERN = Pattern.compile("^" +".{4,}" + "$"+"(?=.*[A,T])"+"(?=\\S+$)" );
    //private  static final Pattern NO_PATTERN = Pattern.compile("^" + )
    private Button add;
    private EditText iid;
    private EditText iname;
    private EditText iquantity;
    private EditText isalesprice;
    private EditText icategory;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle   savedInstanceState) {
        View view =  inflater.inflate(R.layout.add_inventory, container, false);
        //super.onCreate(savedInstanceState);
        //setContentView(R.layout.add_inventory);
        add = view.findViewById(R.id.iadd);
        iid = view.findViewById(R.id.icode);
        iname = view.findViewById(R.id.iname);
        iquantity= view.findViewById(R.id.iqty);
        isalesprice = view.findViewById(R.id.iprice);
        icategory = view.findViewById(R.id.icat);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idInput = iid.getEditableText().toString().trim();
                String nameInput = iname.getEditableText().toString().trim();
                String qtyInput = iquantity.getText().toString().trim();
                String priceInput = isalesprice.getEditableText().toString().trim();
                String categoryInput = icategory.getEditableText().toString().trim();
                if(idInput.isEmpty()){
                    iid.setError("Field can't be empty");
                    return ;
                }

                if(nameInput.isEmpty()){
                    iname.setError("Field can't be empty");
                    return ;
                }

                if(qtyInput.isEmpty()){
                    iquantity.setError("Field can't be empty");
                    return ;
                }

                if(priceInput.isEmpty()){
                    isalesprice.setError("Field can't be empty");
                    return ;
                }

                if(categoryInput.isEmpty()){
                    icategory.setError("Field can't be empty");
                    return ;
                }

            }


        });

        return  view;
    }









}
