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


public class Customeradd extends Fragment {



    private Button add;
    private EditText Id;
    private EditText fristname;
    private EditText lastname;
    private EditText email;
    private EditText nic;
    private EditText phone;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle SavedInstanceState) {
        View view = inflater.inflate(R.layout.add_new_customer, container, false);

        add = view.findViewById(R.id.csubmitbutton);
        Id = view.findViewById(R.id.cid);
        fristname = view.findViewById(R.id.fname);
        lastname = view.findViewById(R.id.lname);
        email = view.findViewById(R.id.cemail);
        nic = view.findViewById(R.id.nic);
        phone = view.findViewById(R.id.cphone);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idinput = Id.getEditableText().toString().trim();
                String fnameinput = fristname.getEditableText().toString().trim();
                String lnameinput = lastname.getEditableText().toString().trim();
                String emailinput = email.getEditableText().toString().trim();
                String nicinput = nic.getEditableText().toString().trim();
                String phoneinput = phone.getEditableText().toString().trim();
                if (fnameinput.isEmpty()) {
                    fristname.setError("Field can't be empty");
                    return;
                }

                if (lnameinput.isEmpty()) {
                    lastname.setError("Field can't be empty");
                    return;
                }

                if (emailinput.isEmpty()) {
                    email.setError("Field can't be empty");
                    return;
                }

                if (nicinput.isEmpty()) {
                    nic.setError("Field can't be empty");
                    return;
                }

                if (phoneinput.isEmpty()) {
                    phone.setError("Field can't be empty");
                    return;
                }

                CustomerRepository customerrep = new CustomerRepository(getActivity().getApplication());
                Customers cmodel = new Customers(Integer.parseInt(idinput),fnameinput,lnameinput,emailinput,nicinput,phoneinput,true);
                customerrep.insertCustomer(cmodel);
            }
        });


        return view;
    }
}
