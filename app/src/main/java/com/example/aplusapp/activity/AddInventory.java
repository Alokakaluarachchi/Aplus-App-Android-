package com.example.aplusapp.activity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.aplusapp.R;
import com.google.android.material.textfield.TextInputLayout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class AddInventory extends AppCompatActivity {

    private static final Pattern ID_PATTERN = Pattern.compile("^" +".{4,}" + "$"+"(?=.*[A,T])"+"(?=\\S+$)" );
    //private  static final Pattern NO_PATTERN = Pattern.compile("^" + )
    private Button add;
    private EditText iid;
    private EditText iname;
    private EditText iquantity;
    private EditText isalesprice;
    private EditText icategory;

   // @Override
    public void onCreate(LayoutInflater inflater, ViewGroup container, Bundle   savedInstanceState) {
        //View view =  inflater.inflate(R.layout.secondefragment, container, false);
        //super.onCreate(savedInstanceState);
        setContentView(R.layout.add_inventory);
        //add = rootView.findViewById(R.id.iadd);
        //iid = rootView.findViewById(R.id.icode);
        //iname = rootView.findViewById(R.id.iname);
        iquantity= findViewById(R.id.iqty);
        isalesprice = findViewById(R.id.iprice);
        icategory = findViewById(R.id.icat);
    }
    private boolean validateID(){
        String idInput = iid.getEditableText().toString().trim();
        if(idInput.isEmpty()){
            iid.setError("Field can't be empty");
            return false;
        }
        else if(!ID_PATTERN.matcher(idInput).matches()){
            iid.setError("Wrong ID type");
            return false;
        }
        else{
            iid.setError(null);
            return false;
        }
    }
    private boolean validateName(){
        String nameInput = iname.getText().toString().trim();
        if(nameInput.isEmpty()){
            iname.setError("Field can't be empty");
            return false;
        }
        else{
            iid.setError(null);
            return false;
        }
    }
    private boolean validateQuantity(){
        String qtyInput = iquantity.getText().toString().trim();
        if(qtyInput.isEmpty()){
            iquantity.setError("Field can't be empty");
            return false;
        }
        else{
            iid.setError(null);
            return false;
        }
    }
    private boolean validatePrice(){
        String priceInput = isalesprice.getEditableText().toString().trim();
        if(priceInput.isEmpty()){
            isalesprice.setError("Field can't be empty");
            return false;
        }
        else{
            isalesprice.setError(null);
            return false;
        }
    }
    private boolean validateCategory(){
        String categoryInput = icategory.getEditableText().toString().trim();
        if(categoryInput.isEmpty()){
            icategory.setError("Field can't be empty");
            return false;
        }
        else{
            icategory.setError(null);
            return false;
        }
    }
}
