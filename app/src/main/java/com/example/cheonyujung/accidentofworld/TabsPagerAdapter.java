package com.example.cheonyujung.accidentofworld;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by cheonyujung on 2016. 5. 21..
 */
public class TabsPagerAdapter extends FragmentPagerAdapter{
    ArrayList<String> tabs;
    public TabsPagerAdapter(FragmentManager fm, ArrayList<String> tabs){
        super(fm);
        this.tabs=tabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                // Tab1 Fragment
                return new Tab1();
            case 1:
                // Tab2 Fragment
                return new Tab2();
            case 2:
                // Tab3 Fragment
                return new Tab3();
        }
        return null;
    }

    @Override
    public int getCount() {
        return tabs.size();
    }
}
