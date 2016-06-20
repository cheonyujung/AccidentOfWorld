package com.example.cheonyujung.accidentofworld.parallaxviewpage;

import android.widget.AbsListView;
import android.widget.ScrollView;

/**
 * Created by ohyongtaek on 16. 5. 25..
 */
public interface ScrollTabHolder {

    void adjustScroll(int scrollHeight, int headerHeight);

    void onScrollViewScroll(ScrollView view, int x, int y,
                            int oldX, int oldY, int pagePosition);

    void onListViewScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
                          int totalItemCount, int pagePosition);
}
