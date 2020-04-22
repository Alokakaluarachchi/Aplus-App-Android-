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

public class Customerupdate extends Fragment {

    private Button update;
    private EditText Id;
    private EditText fristname;
    private EditText lastname;
    private EditText email;
    private EditText nic;
    private EditText phone;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle SavedInstanceState) {
        View view = inflater.inflate(R.layout.customer_detailupdate_delete, container,false);

        update = view.findViewById(R.id.cuupdate);
        Id = view.findViewById(R.id.cid);
        fristname = view.findViewById(R.id.fname);
        lastname = view.findViewById(R.id.lname);
        email = view.findViewById(R.id.cemail);
        nic = view.findViewById(R.id.nic);
        phone = view.findViewById(R.id.cphone);
        
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idupdate = Id.getEditableText().toString().trim();
                String fnameupdate = fristname.getEditableText().toString().trim();
                String lnameupdate = lastname.getEditableText().toString().trim();
                String emailupdate = email.getEditableText().toString().trim();
                String nicupdate = nic.getEditableText().toString().trim();
                String phoneupdate = phone.getEditableText().toString().trim();
                if(fnameupdate.isEmpty()){
                    fristname.setError("Field can't be empty");
                    return;
                }
                
                if (lnameupdate.isEmpty()){
                    lastname.setError("Field can't be empty");
                    return;
                }
                
                if (emailupdate.isEmpty()){
                    email.setError("Field can't be empty");
                    return;
                }
                
                if (nicupdate.isEmpty()){
                    nic.setError("Field can't be empty");
                    return;
                }
                
                if (phoneupdate.isEmpty()){
                    phone.setError("Field can't be empty");
                    return;
                }

                CustomerRepository customerreop = new CustomerRepository(getActivity().getApplication());
                Customers cmodell = new Customers(Integer.parseInt(idupdate),fnameupdate,lnameupdate,emailupdate,nicupdate,phoneupdate,true);
                customerreop.updateCustomer(cmodell);
                
            }
        });
        return view;
    }

}
