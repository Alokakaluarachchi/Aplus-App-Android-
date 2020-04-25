package com.example.aplusapp.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.example.aplusapp.R;
import com.example.aplusapp.db.repos.CustomerRepository;
import com.example.aplusapp.model.Customers;

public class CustomerDelete extends Fragment {

    private Button delete;
    private EditText nic;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle SavedInstanceState) {
        View view = inflater.inflate(R.layout.customerdetail, container,false);

        delete = view.findViewById(R.id.cudelete);
        nic = view.findViewById(R.id.cid);

        delete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String nicdelete = nic.getEditableText().toString().trim();

                if(nicdelete.isEmpty()){
                    nic.setError("Field can't be empty");
                    return;
                }

                CustomerRepository customerRepository = new CustomerRepository(getActivity().getApplication());
                Customers cmodel = new Customers(nicdelete);
                customerRepository.removeCustomer(cmodel);

            }
        });
        return view;
    }
}
