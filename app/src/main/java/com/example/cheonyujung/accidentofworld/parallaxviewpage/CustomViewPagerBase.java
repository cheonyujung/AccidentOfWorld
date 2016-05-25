package com.example.cheonyujung.accidentofworld.parallaxviewpage;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ScrollView;

import com.example.cheonyujung.accidentofworld.Base;

/**
 * Created by ohyongtaek on 16. 5. 25..
 */
public abstract class CustomViewPagerBase extends Base implements ScrollTabHolder {

    protected static final String IMAGE_TRANSLATION_Y = "image_translation_y";
    protected static final String HEADER_TRANSLATION_Y = "header_translation_y";

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

    protected int getScrollYOfListView(AbsListView view) {
        View child = view.getChildAt(0);
        if (child == null) {
            return 0;
        }

        int firstVisiblePosition = view.getFirstVisiblePosition();
        int top = child.getTop();

        int headerHeight = 0;
        if (firstVisiblePosition >= 1) {
            headerHeight = mHeaderHeight;
        }

        return -top + firstVisiblePosition * child.getHeight() + headerHeight;
    }

    protected CustomViewPagerChangeListener getViewPagerChangeListener() {
        return new CustomViewPagerChangeListener(mHeader, mViewPager, mAdapter);
    }

    @Override
    public void adjustScroll(int scrollHeight, int headerHeight) {

    }

    @Override
    public void onScrollViewScroll(ScrollView view, int x, int y, int oldX, int oldY, int pagePosition) {
        if(mViewPager.getCurrentItem() == pagePosition) {
            scrollHeader(view.getScrollY());
        }
    }
}
