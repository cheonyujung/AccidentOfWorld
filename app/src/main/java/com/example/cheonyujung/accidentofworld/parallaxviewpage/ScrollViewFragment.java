package com.example.cheonyujung.accidentofworld.parallaxviewpage;

import android.widget.ScrollView;

/**
 * Created by ohyongtaek on 16. 6. 19..
 */
public class ScrollViewFragment extends ScrollHolderViewFragment {

    protected CustomScrollView mScrollView;

    protected void setScrollViewOnScrollListener() {
        mScrollView.setOnScrollChangedListener(new CustomScrollView.OnScrollChangedListener() {
            @Override
            public void onScrollChanged(ScrollView view, int l, int t, int oldl, int oldt) {
                if (mScrollTabHolder != null) {
                    mScrollTabHolder.onScrollViewScroll(view, l, t, oldl, oldt, mPosition);
                }
            }
        });
    }

    @Override
    public void adjustScroll(int scrollHeight, int headerHeight) {
        if (mScrollView == null) return;

        mScrollView.scrollTo(NO_SCROLL_X, headerHeight - scrollHeight);

        if (mScrollTabHolder != null) {
            mScrollTabHolder.onScrollViewScroll(mScrollView, 0, 0, 0, 0, mPosition);
        }

    }
}
