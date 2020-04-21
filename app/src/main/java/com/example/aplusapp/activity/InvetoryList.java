package com.example.aplusapp.activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.aplusapp.R;

public class InvetoryList extends Fragment{

    private Button update;
    private Button delete;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.inventory_table, container, false);

        update= rootView.findViewById(R.id.clientimain);
        delete= rootView.findViewById(R.id.clientimain1);

        //update.setOnClickListener(new View.OnClickListener() {

        //});

        return rootView;

    }
}
