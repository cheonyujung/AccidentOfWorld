package com.example.cheonyujung.accidentofworld;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;

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
        //        //setCustomActionbar();
//        tabs.add("Tab1");
//        tabs.add("Tab2");
//        tabs.add("Tab3");
//
//        button1 = (Button) findViewById(R.id.danger_type_button);
//        button2 = (Button) findViewById(R.id.accident_button);
//        button3 = (Button) findViewById(R.id.contact_button);
//
//
//        // Initilization
//        viewPager = (ViewPager) findViewById(R.id.pager);
//
//        mAdapter = new TabsPagerAdapter(getSupportFragmentManager() , tabs);
//
//        viewPager.setAdapter(mAdapter);
//
//        button1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                viewPager.setCurrentItem(0);
//            }
//        });
//
//        button2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                viewPager.setCurrentItem(1);
//            }
//        });
//
//        button3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                viewPager.setCurrentItem(2);
//            }
//        });
//        // Adding Tabs
//        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageSelected(int arg0) {
//                // TODO Auto-generated method stub
//                //Page Position
//                int pagePosition=   viewPager.getCurrentItem();
//                //  Toast.makeText(getApplicationContext(),""+pagePosition,1000).show();
//                if(pagePosition==0){
//                    ClickButtonColor(0);
//                }
//                if(pagePosition==1){
//                    ClickButtonColor(1);
//                }
//                if(pagePosition==2){
//                    ClickButtonColor(2);
//                }
//            }
//            @Override
//            public void onPageScrolled(int arg0, float arg1, int arg2) {
//                // TODO Auto-generated method stub
//            }
//            @Override
//            public void onPageScrollStateChanged(int arg0) {
//                // TODO Auto-generated method stub
//            }
//        });
//    }
//    public void ClickButtonColor(int index){
//        button1.setBackgroundColor(Color.parseColor("#ffffff"));
//        button2.setBackgroundColor(Color.parseColor("#ffffff"));
//        button3.setBackgroundColor(Color.parseColor("#ffffff"));
//        button1.setTextColor(Color.parseColor("#000000"));
//        button2.setTextColor(Color.parseColor("#000000"));
//        button3.setTextColor(Color.parseColor("#000000"));
//        if(index == 0){
//            button1.setBackgroundColor(Color.parseColor("#40C2BE"));
//            button1.setTextColor(Color.parseColor("#ffffff"));
//        }else if(index == 1){
//            button2.setBackgroundColor(Color.parseColor("#40C2BE"));
//            button2.setTextColor(Color.parseColor("#ffffff"));
//        }else if(index == 2){
//            button3.setBackgroundColor(Color.parseColor("#40C2BE"));
//            button3.setTextColor(Color.parseColor("#ffffff"));
//        }
//    }
    public void setText(){
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String country_name = bundle.getString("CountryName");
    }


}
