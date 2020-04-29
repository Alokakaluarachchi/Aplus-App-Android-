package com.example.aplusapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.aplusapp.R;
import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;
import com.luseen.spacenavigation.SpaceOnClickListener;

public class HomeActivity extends AppCompatActivity {

    SpaceNavigationView spaceNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        spaceNavigationView = findViewById(R.id.space);
        spaceNavigationView.initWithSaveInstanceState(savedInstanceState);
        spaceNavigationView.addSpaceItem(new SpaceItem("", R.drawable.ic_notifications_black_24dp));
        spaceNavigationView.addSpaceItem(new SpaceItem("", R.drawable.ic_equalizer_black_24dp));
        spaceNavigationView.addSpaceItem(new SpaceItem("", R.drawable.ic_account_circle_black_24dp));
        spaceNavigationView.addSpaceItem(new SpaceItem("", R.drawable.ic_open_in_browser_black_24dp));
        spaceNavigationView.setCentreButtonSelectable(true);
        spaceNavigationView.setCentreButtonSelected();

        //set category screen
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new CategoryActivity()).commit();

        spaceNavigationView.setSpaceOnClickListener(new SpaceOnClickListener() {
            @Override
            public void onCentreButtonClick() {
                //Toast.makeText(HomeActivity.this,"onCentreButtonClick", Toast.LENGTH_SHORT).show();

                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new CategoryActivity()).commit();
            }

            @Override
            public void onItemClick(int itemIndex, String itemName) {
                //Toast.makeText(HomeActivity.this, itemIndex + " " + itemName, Toast.LENGTH_SHORT).show();

                if(itemIndex == 2){
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new UserProfileActivity()).commit();
                }else if(itemIndex == 3){
                    startActivity(new Intent(HomeActivity.this, MainActivity.class));
                }

            }

            @Override
            public void onItemReselected(int itemIndex, String itemName) {
                Toast.makeText(HomeActivity.this, itemIndex + " " + itemName, Toast.LENGTH_SHORT).show();
            }
        });
    }


}
