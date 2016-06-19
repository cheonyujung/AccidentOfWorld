package com.example.cheonyujung.accidentofworld.parallaxviewpage;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ScrollView;


/**
 * Created by ohyongtaek on 16. 5. 25..
 */
public class ScrollHolderViewFragment extends Fragment implements ScrollTabHolder {

    protected static final String ARG_POSITION = "position";
    protected ScrollTabHolder mScrollTabHolder;
    protected CustomScrollView mScrollView;
    protected int mPosition;

    public static final String TAG = ScrollHolderViewFragment.class.getSimpleName();

    protected static final int NO_SCROLL_X = 0;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mScrollTabHolder = (ScrollTabHolder) context;
        }catch (Exception e) {
            throw new ClassCastException(context.toString() + " must implement ScrollTabHolder");
        }
    }



    @Override
    public void onDetach() {
        mScrollTabHolder = null;
        super.onDetach();
    }

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

    @Override
    public void onScrollViewScroll(ScrollView view, int x, int y, int oldX, int oldY, int pagePosition) {

    }
}
