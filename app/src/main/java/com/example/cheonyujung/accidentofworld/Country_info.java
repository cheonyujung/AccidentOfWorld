package com.example.cheonyujung.accidentofworld;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.desmond.parallaxviewpager.ParallaxFragmentPagerAdapter;
import com.desmond.parallaxviewpager.ParallaxViewPagerBaseActivity;
import com.example.cheonyujung.accidentofworld.slidingTab.SlidingTabLayout;

import java.util.ArrayList;

/**
 * Created by cheonyujung on 2016. 5. 21..
 */
public class Country_info extends ParallaxViewPagerBaseActivity {

    private View mTopView;
    private SlidingTabLayout mNavigBar;
    private TextView engName;
    private TextView language_text;
    private TextView capital_text;
    private TextView continent_text;
    private TextView currency_text;

    // Tab titles
    ArrayList<String> tabs = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.country_info);

        initValues();

        mTopView = (View) findViewById(R.id.upView);
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mNavigBar = (SlidingTabLayout) findViewById(R.id.navig_tab);
        mHeader = findViewById(R.id.Country_info_basic);

        engName = (TextView) findViewById(R.id.enname_text);
        language_text = (TextView) findViewById(R.id.language_text);
        capital_text = (TextView) findViewById(R.id.capital_text);
        continent_text = (TextView) findViewById(R.id.continent_text);
        currency_text = (TextView) findViewById(R.id.continent_text);

        if (savedInstanceState != null) {
            mTopView.setTranslationY(savedInstanceState.getFloat(IMAGE_TRANSLATION_Y));
            mHeader.setTranslationY(savedInstanceState.getFloat(HEADER_TRANSLATION_Y));
        }

        setupAdapter();
    }

    @Override
    protected void initValues() {
        int tabHeight = 50;
        mMinHeaderHeight = 650;  // header의 크
        mHeaderHeight = 250;
        mMinHeaderTranslation = -mMinHeaderHeight + tabHeight;

        mNumFragments = 3;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putFloat(IMAGE_TRANSLATION_Y, mTopView.getTranslationY());
        outState.putFloat(HEADER_TRANSLATION_Y, mHeader.getTranslationY());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void setupAdapter() {
        if (mAdapter == null) {
            mAdapter = new ViewPagerAdapter(getSupportFragmentManager(), mNumFragments);
        }

        mViewPager.setAdapter(mAdapter);
        mViewPager.setOffscreenPageLimit(mNumFragments);
        mNavigBar.setOnPageChangeListener(getViewPagerChangeListener());
        mNavigBar.setViewPager(mViewPager);
    }

    @Override
    protected void scrollHeader(int scrollY) {
        float translationY = Math.max(-scrollY, mMinHeaderTranslation);
        mHeader.setTranslationY(translationY);
        mTopView.setTranslationY(-translationY / 3);
    }

    private static class ViewPagerAdapter extends ParallaxFragmentPagerAdapter {

        public ViewPagerAdapter(FragmentManager fm, int numFragments) {
            super(fm, numFragments);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment;
            switch (position) {
                case 0:
                    fragment = Tab1.newInstance(0);
                    break;

                case 1:
                    fragment = Tab2.newInstance(1);
                    break;

                case 2:
                    fragment = Tab3.newInstance(2);
                    break;

                default:
                    throw new IllegalArgumentException("Wrong page given " + position);
            }
            return fragment;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "위험 유형";

                case 1:
                    return "사건 사고";

                case 2:
                    return "연락처";

                default:
                    throw new IllegalArgumentException("wrong position for the fragment in vehicle page");
            }
        }
    }

    public void setText(){
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String country_name = bundle.getString("CountryName");


    }


}
