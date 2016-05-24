package com.example.cheonyujung.accidentofworld;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.desmond.parallaxviewpager.ScrollViewFragment;

/**
 * Created by cheonyujung on 2016. 5. 21..
 */
public class Tab3 extends ScrollViewFragment {

    public static final String TAG = Tab3.class.getSimpleName();

    public static Fragment newInstance(int position) {
        Tab3 fragment = new Tab3();
        Bundle args = new Bundle();
        args.putInt(ARG_POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }

    public Tab3() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mPosition = getArguments().getInt(ARG_POSITION);

        View view = inflater.inflate(R.layout.tab3, container, false);
        mScrollView = (com.desmond.parallaxviewpager.NotifyingScrollView) view.findViewById(R.id.scrollview);
        setScrollViewOnScrollListener();
        return view;
    }
}
