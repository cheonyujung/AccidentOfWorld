package com.example.cheonyujung.accidentofworld;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.example.cheonyujung.accidentofworld.data.struct.Accident;
import com.example.cheonyujung.accidentofworld.parallaxviewpage.CustomScrollView;
import com.example.cheonyujung.accidentofworld.parallaxviewpage.ScrollViewFragment;

/**
 * Created by cheonyujung on 2016. 5. 21..
 */
public class Tab2 extends ScrollViewFragment {

    public static final String TAG = Tab2.class.getSimpleName();

    public static Fragment newInstance(int position) {
        Tab2 fragment = new Tab2();
        Bundle args = new Bundle();
        args.putInt(ARG_POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }

    public Tab2() {}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mPosition = getArguments().getInt(ARG_POSITION);

        View view = inflater.inflate(R.layout.tab2, container, false);
        WebView webView = (WebView) view.findViewById(R.id.accident_web);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setVerticalScrollBarEnabled(false);
        Accident accident = Accident.getAccident(getArguments().getString("CountryName"));
        String source = accident.getDisater();
        webView.loadData(source, "text/html; charset=UTF-8", null);
        webView.setHorizontalScrollBarEnabled(false);
        webView.setVerticalScrollBarEnabled(false);
        webView.setBackgroundColor(0);
        mScrollView = (CustomScrollView) view.findViewById(R.id.scrollview);
        setScrollViewOnScrollListener();
        return view;
    }
}
