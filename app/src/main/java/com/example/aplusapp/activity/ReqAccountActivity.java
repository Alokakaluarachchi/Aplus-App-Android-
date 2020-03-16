package com.example.aplusapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.example.aplusapp.R;

import fr.ganfra.materialspinner.MaterialSpinner;

public class ReqAccountActivity extends AppCompatActivity {

    MaterialSpinner spinner;
    String[] ITEMS = {"Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_req_account);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ITEMS);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner = (MaterialSpinner) findViewById(R.id.spinnerRole);
        spinner.setAdapter(adapter);

    }
}
