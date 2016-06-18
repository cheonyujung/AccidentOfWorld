package com.example.cheonyujung.accidentofworld;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cheonyujung.accidentofworld.data.query.TravelInfoQuery.Danger_area;
import com.example.cheonyujung.accidentofworld.parallaxviewpage.CustomScrollView;
import com.example.cheonyujung.accidentofworld.parallaxviewpage.ScrollHolderViewFragment;

/**
 * Created by cheonyujung on 2016. 5. 21..
 */
public class Tab1 extends ScrollHolderViewFragment {

    public TextView dangerTypeText;
    public TextView dangerContent;
    public static final String TAG = Tab1.class.getSimpleName();
    Danger_area danger_area;

    public static Tab1 newInstance(int position) {
        Tab1 fragment = new Tab1();
        Bundle args = new Bundle();
        args.putInt(ARG_POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }

    public Tab1() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mPosition = getArguments().getInt(ARG_POSITION);

        View view = inflater.inflate(R.layout.tab1, container, false);
        mScrollView = (CustomScrollView) view.findViewById(R.id.scrollview);

        setScrollViewOnScrollListener();
        return view;
    }
}