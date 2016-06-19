package com.example.cheonyujung.accidentofworld;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.cheonyujung.accidentofworld.data.Data;
import com.example.cheonyujung.accidentofworld.data.query.TravelInfoQuery.Danger_area;
import com.example.cheonyujung.accidentofworld.data.struct.CountryDangerMap;
import com.example.cheonyujung.accidentofworld.data.struct.Danger;
import com.example.cheonyujung.accidentofworld.parallaxviewpage.CustomScrollView;
import com.example.cheonyujung.accidentofworld.parallaxviewpage.ScrollHolderViewFragment;
import com.example.cheonyujung.accidentofworld.parallaxviewpage.ScrollViewFragment;

/**
 * Created by cheonyujung on 2016. 5. 21..
 */
public class Tab1 extends ScrollViewFragment {

    public static final String TAG = Tab1.class.getSimpleName();
    DangerInfoAdapter adapter;
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
        Bundle bundle = getArguments();
        String countryName = bundle.getString("CountryName");
        View view = inflater.inflate(R.layout.tab1, container, false);
        mScrollView = (CustomScrollView) view.findViewById(R.id.scrollview);
        adapter = new DangerInfoAdapter(countryName);
        adapter.setDataSet(Data.dbDanger_area.getDanger_areaByCountryName(countryName));

        Bitmap dangerMap = CountryDangerMap.getDangerMap(countryName).getImage();
        ImageView imageView = new ImageView(getContext());
        imageView.setImageBitmap(dangerMap);

        ListView listView = (ListView) view.findViewById(R.id.listview);
        listView.setAdapter(adapter);
        listView.addHeaderView(imageView);

        if(adapter.getCount() == 0) {
            view.findViewById(R.id.empty).setVisibility(View.VISIBLE);
        }else {
            view.findViewById(R.id.empty).setVisibility(View.GONE);
        }
        adapter.notifyDataSetChanged();
        setScrollViewOnScrollListener();
        return view;
    }
}