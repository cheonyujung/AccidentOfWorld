package com.example.cheonyujung.accidentofworld.fragment;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.TaskStackBuilder;
import android.widget.TextView;

import com.example.cheonyujung.accidentofworld.Base;
import com.example.cheonyujung.accidentofworld.R;

/**
 * Created by vmffkxlgnqh1 on 2016. 5. 28..
 */
public class ListActivity extends Base {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        super.setTitle("Country List");

        FragmentManager fm = getFragmentManager();
        fm.beginTransaction()
                .replace(R.id.body, com.example.cheonyujung.accidentofworld.fragment.WorldListFragment.getInstence())
                .commit();

    }
}
