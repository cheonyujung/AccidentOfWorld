package com.example.cheonyujung.accidentofworld.parallaxviewpage;

import android.support.v4.util.SparseArrayCompat;
import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by ohyongtaek on 16. 5. 26..
 */
public class CustomViewPagerChangeListener implements ViewPager.OnPageChangeListener {

    private View mHeader;
    private ViewPager mViewPager;
    private CustomFragmentPagerAdapter mAdapter;
    private int mNumFragments;
    public CustomViewPagerChangeListener(View mHeader, ViewPager mViewPager, CustomFragmentPagerAdapter mAdapter) {
        this.mHeader = mHeader;
        this.mViewPager = mViewPager;
        this.mAdapter = mAdapter;
        this.mNumFragments = mAdapter.getCount();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        int currentItem = mViewPager.getCurrentItem();
        if (positionOffsetPixels > 0) {
            SparseArrayCompat<ScrollTabHolder> scrollTabHolders = mAdapter.getmScrollTabHolders();
            ScrollTabHolder fragment;
            if (position < currentItem) {
                fragment = scrollTabHolders.valueAt(position);
            } else {
                fragment = scrollTabHolders.valueAt(position + 1);
            }
            fragment.adjustScroll((int) (mHeader.getHeight() + mHeader.getTranslationY()),mHeader.getHeight());
        }
    }

    @Override
    public void onPageSelected(int position) {
        SparseArrayCompat<ScrollTabHolder> scrollTabHolders = mAdapter.getmScrollTabHolders();

        if (scrollTabHolders == null || scrollTabHolders.size() != mNumFragments) {
            return;
        }
        ScrollTabHolder fragment = scrollTabHolders.valueAt(position);

        fragment.adjustScroll((int) (mHeader.getHeight() + mHeader.getTranslationY()), mHeader.getHeight());

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
