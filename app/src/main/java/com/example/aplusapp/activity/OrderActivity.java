
package com.example.aplusapp.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.aplusapp.R;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class OrderActivity extends Fragment{

    private Button btnOrderComplete,btnOrderDelete;

    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_order, container, false);

        Button btnOrderComplete = rootView.findViewById(R.id.btnOrderComplete);
        Button btnOrderDelete = rootView.findViewById(R.id.btnOrderDelete);

        btnOrderComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"Order is Completed",Toast.LENGTH_SHORT).show();
            }




        });

        btnOrderDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"Order is Deleted",Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;
    }
}
