package com.example.cheonyujung.accidentofworld;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

<<<<<<< HEAD
import com.example.cheonyujung.accidentofworld.parallaxviewpage.CustomScrollView;
import com.example.cheonyujung.accidentofworld.parallaxviewpage.ScrollHolderViewFragment;
=======
import com.desmond.parallaxviewpager.ScrollViewFragment;
import com.example.cheonyujung.accidentofworld.data.struct.Danger_area;
>>>>>>> 4a94910... 게시판 UI

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
<<<<<<< HEAD
        mScrollView = (CustomScrollView) view.findViewById(R.id.scrollview);
=======
        mScrollView = (com.desmond.parallaxviewpager.NotifyingScrollView) view.findViewById(R.id.scrollview);

//        dangerTypeText = (TextView) view.findViewById(R.id.dangerType);
//        dangerTypeText.setText(danger_area.getDegree());
//
//        dangerContent = (TextView) view.findViewById(R.id.dangerContents);
//        dangerContent.setText(danger_area.getContents());

>>>>>>> 4a94910... 게시판 UI
        setScrollViewOnScrollListener();
        return view;
    }
}