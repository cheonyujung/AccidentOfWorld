package com.example.cheonyujung.accidentofworld.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.cheonyujung.accidentofworld.R;
import com.example.cheonyujung.accidentofworld.Tab1;
import com.example.cheonyujung.accidentofworld.Tab2;
import com.example.cheonyujung.accidentofworld.Tab3;
import com.example.cheonyujung.accidentofworld.data.struct.Country;
import com.example.cheonyujung.accidentofworld.data.struct.CountryDangerMap;
import com.example.cheonyujung.accidentofworld.parallaxviewpage.CustomFragmentPagerAdapter;
import com.example.cheonyujung.accidentofworld.parallaxviewpage.CustomViewPagerBaseFragment;
import com.example.cheonyujung.accidentofworld.parallaxviewpage.CustomViewPagerChangeListener;
import com.example.cheonyujung.accidentofworld.parallaxviewpage.ScrollTabHolder;
import com.example.cheonyujung.accidentofworld.slidingTab.SlidingTabLayout;

/**
 * Created by ohyongtaek on 16. 5. 31..
 */
public class CountryInfoFragment extends CustomViewPagerBaseFragment {

    private View mTopView;
    private SlidingTabLayout mNavigBar;
    private TextView engName;
    private TextView language_text;
    private TextView capital_text;
    private TextView continent_text;
    private TextView currency_text;
    private ImageView imageView;
    Bundle bundle;

    protected static final String IMAGE_TRANSLATION_Y = "image_translation_y";
    protected static final String HEADER_TRANSLATION_Y = "header_translation_y";

    public View mHeader;
    public ViewPager mViewPager;
    public CustomFragmentPagerAdapter mAdapter;

    protected int mMinHeaderHeight;
    protected int mHeaderHeight;
    protected int mMinHeaderTranslation;
    protected int mNumFragments;

    protected CustomViewPagerChangeListener getViewPagerChangeListener() {
        return new CustomViewPagerChangeListener(mHeader, mViewPager, mAdapter);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.country_info,container,false);
        initValues();
        bundle = getArguments();
        mTopView = view.findViewById(R.id.upView);
        mViewPager = (ViewPager) view.findViewById(R.id.pager);
        mNavigBar = (SlidingTabLayout) view.findViewById(R.id.navig_tab);
        mHeader = view.findViewById(R.id.Country_info_basic);

        imageView = (ImageView) view.findViewById(R.id.Country_flag);
        engName = (TextView) view.findViewById(R.id.enname_text);
        language_text = (TextView) view.findViewById(R.id.language_text);
        capital_text = (TextView) view.findViewById(R.id.capital_text);
        continent_text = (TextView) view.findViewById(R.id.continent_text);
        currency_text = (TextView) view.findViewById(R.id.currency_money);
        setText();

        if (savedInstanceState != null) {
            mTopView.setTranslationY(savedInstanceState.getFloat(IMAGE_TRANSLATION_Y));
            mHeader.setTranslationY(savedInstanceState.getFloat(HEADER_TRANSLATION_Y));
        }

        setupAdapter();

        return view;
    }


    @Override
    protected void initValues() {
        int tabHeight = 50;
        mMinHeaderHeight = 750;  // header의 크기
        mHeaderHeight = 300;
        mMinHeaderTranslation = -mMinHeaderHeight + tabHeight;

        mNumFragments = 3;
    }

    @Override
    protected void scrollHeader(int scrollY) {
        float translationY = Math.max(-scrollY, mMinHeaderTranslation);
        mHeader.setTranslationY(translationY);
        mTopView.setTranslationY(-translationY / 3);
    }
    @Override
    protected void setupAdapter() {
        if (mAdapter == null) {
            mAdapter = new ViewPagerAdapter(getFragmentManager(), mNumFragments,bundle);
        }

        mViewPager.setAdapter(mAdapter);
        mViewPager.setOffscreenPageLimit(mNumFragments);
        mNavigBar.setOnPageChangeListener(getViewPagerChangeListener());
        mNavigBar.setViewPager(mViewPager);
    }

    public void setText(){

        String country_name = bundle.getString("CountryName");
        Country country = Country.getCountry(country_name);
        imageView.setImageBitmap(CountryDangerMap.getDangerMap(country).getImage());
        engName.setText(country.getName_en());
        language_text.setText(country.getLanguage());
        capital_text.setText(country.getCapital());
        continent_text.setText(country.getContinent());
        currency_text.setText(country.getCurrency());
    }

    public Bundle getBundle(){
        return bundle;
    }



    private static class ViewPagerAdapter extends CustomFragmentPagerAdapter {

        Bundle bundle;
        public ViewPagerAdapter(FragmentManager fm, int numFragments,Bundle bundle) {
            super(fm, numFragments);
            this.bundle = bundle;
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
            fragment.getArguments().putString("CountryName",bundle.getString("CountryName"));
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
}
