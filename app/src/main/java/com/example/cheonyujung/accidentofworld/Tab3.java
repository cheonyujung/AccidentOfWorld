package com.example.cheonyujung.accidentofworld;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.example.cheonyujung.accidentofworld.data.struct.Contact;
import com.example.cheonyujung.accidentofworld.parallaxviewpage.CustomScrollView;
import com.example.cheonyujung.accidentofworld.parallaxviewpage.ScrollHolderViewFragment;
import com.example.cheonyujung.accidentofworld.parallaxviewpage.ScrollViewFragment;

/**
 * Created by cheonyujung on 2016. 5. 21..
 */
public class Tab3 extends ScrollViewFragment {

    WebView webView;
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
        mScrollView = (CustomScrollView) view.findViewById(R.id.scrollview);

        webView = (WebView) view.findViewById(R.id.webview);
        Contact contact = Contact.getContact(getArguments().getString("CountryName"));
        String source = contact.getTel();
        webView.loadData(source, "text/html; charset=UTF-8",null);
        // 자바스크립트 허용
        webView.getSettings().setJavaScriptEnabled(true);

// 스크롤바 없애기
        webView.setHorizontalScrollBarEnabled(false);
        webView.setVerticalScrollBarEnabled(false);
        webView.setBackgroundColor(0);
        setScrollViewOnScrollListener();
        return view;
    }
}
