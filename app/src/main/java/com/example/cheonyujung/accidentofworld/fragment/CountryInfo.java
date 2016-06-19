package com.example.cheonyujung.accidentofworld.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.ScrollView;

import com.example.cheonyujung.accidentofworld.Base;
import com.example.cheonyujung.accidentofworld.R;
import com.example.cheonyujung.accidentofworld.parallaxviewpage.ScrollTabHolder;

/**
 * Created by ohyongtaek on 16. 5. 31..
 */
public class CountryInfo extends Base implements ScrollTabHolder {

    CountryInfoFragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FragmentManager fm = getSupportFragmentManager();
        fragment = new CountryInfoFragment();
        fragment.setArguments(getIntent().getExtras());
        fm.beginTransaction()
                .replace(R.id.body, fragment)
                .commit();
    }

    @Override
    public void adjustScroll(int scrollHeight, int headerHeight) {

    }

    @Override
    public void onScrollViewScroll(ScrollView view, int x, int y, int oldX, int oldY, int pagePosition) {
        if(fragment.mViewPager.getCurrentItem() == pagePosition) {
            fragment.scrollHeader(view.getScrollY());
        }
    }

    @Override
    public void onListViewScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount, int pagePosition) {
    }


}
