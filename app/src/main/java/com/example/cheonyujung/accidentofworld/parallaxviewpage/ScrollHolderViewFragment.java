package com.example.cheonyujung.accidentofworld.parallaxviewpage;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ScrollView;


/**
 * Created by ohyongtaek on 16. 5. 25..
 */
public class ScrollHolderViewFragment extends Fragment implements ScrollTabHolder {

    protected static final String ARG_POSITION = "position";
    protected ScrollTabHolder mScrollTabHolder;

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

    @Override
    public void adjustScroll(int scrollHeight, int headerHeight) {
    }

    @Override
    public void onScrollViewScroll(ScrollView view, int x, int y, int oldX, int oldY, int pagePosition) {

    }

    @Override
    public void onListViewScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount, int pagePosition) {

    }

}
