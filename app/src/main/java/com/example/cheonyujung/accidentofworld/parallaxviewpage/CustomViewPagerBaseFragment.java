package com.example.cheonyujung.accidentofworld.parallaxviewpage;


import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ScrollView;

/**
 * Created by ohyongtaek on 16. 5. 31..
 */
public abstract class CustomViewPagerBaseFragment extends Fragment {


    protected View mHeader;
    protected ViewPager mViewPager;
    protected CustomFragmentPagerAdapter mAdapter;

    protected int mMinHeaderHeight;
    protected int mHeaderHeight;
    protected int mMinHeaderTranslation;
    protected int mNumFragments;

    protected abstract void initValues();
    protected abstract void scrollHeader(int scrollY);
    protected abstract void setupAdapter();

    protected CustomViewPagerChangeListener getViewPagerChangeListener() {
        return new CustomViewPagerChangeListener(mHeader, mViewPager, mAdapter);
    }



}
