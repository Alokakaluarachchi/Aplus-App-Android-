package com.example.aplusapp.activity;
import androidx.appcompat.app.AppCompatActivity;
import com.example.aplusapp.R;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Pattern;

public class AddInventory extends AppCompatActivity {

    private static final Pattern ID_PATTERN = Pattern.compile("^" +".{4,}" + "$"+"(?=.*[A,T])");
    private Button add;
    private EditText iid;
    private EditText iname;
    private EditText iquantity;
    private EditText isalesprice;
    private EditText icategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_inventory);
        add = findViewById(R.id.iadd);
        iid = findViewById(R.id.icode);
        iname = findViewById(R.id.iname);
        iquantity= findViewById(R.id.iqty);
        isalesprice = findViewById(R.id.iprice);
        icategory = findViewById(R.id.icat);

    }

    private boolean validateID(){
        String idInput = iid.getEditableText().toString().trim();
        if(idInput.isEmpty()){
            //idInput.setError("Field can't be empty");
            return false;
        }
        return false;
    }


}