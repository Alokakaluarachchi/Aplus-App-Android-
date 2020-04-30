package com.example.aplusapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aplusapp.R;
import com.example.aplusapp.db.repos.OrderRepository;
import com.example.aplusapp.model.Order;

public class EditOrder extends Fragment {

    private Button edit;
    private EditText name,qty;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_order_manage,container,false);
        edit = view.findViewById(R.id.buttonOrderEdit);
        name= view.findViewById(R.id.Text_input_Item_Name);
        qty= view.findViewById(R.id.Text_input_Qty);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nameInput = name.getText().toString().trim();
                String qtyInput = qty.getText().toString().trim();

                if (nameInput.isEmpty()){
                    name.setError("Field can't be empty");
                    return;
                }
                if (qtyInput.isEmpty()){
                    qty.setError("Field can't be empty");
                    return;
                }


                OrderRepository orderepo = new OrderRepository(getActivity().getApplication());
                Order omode = new Order(12,nameInput,Integer.parseInt(qtyInput),true);
                orderepo.updateOrder(omode);

                Toast.makeText(getActivity(),"Successfully Updated",Toast.LENGTH_SHORT).show();
                getFragmentManager().beginTransaction().replace(R.id.fragmentContainer,new OrderActivity()).commit();

            }
        });
        return view;
    }
}
