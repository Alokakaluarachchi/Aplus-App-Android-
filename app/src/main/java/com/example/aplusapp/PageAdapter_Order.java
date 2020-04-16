package com.example.aplusapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PageAdapter_Order extends FragmentPagerAdapter {

    private int numoftabs;

    public PageAdapter_Order(@NonNull FragmentManager fm , int numOfTabs) {
        super(fm);
        this.numoftabs= numOfTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position){

            case 0:
                return new orTab1();
            case 1:
                return new orTab2();
            case 2:
                return new orTab3();
            default:
                return null;
        }


    }

    @Override
    public int getCount() {
        return numoftabs;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }
}
