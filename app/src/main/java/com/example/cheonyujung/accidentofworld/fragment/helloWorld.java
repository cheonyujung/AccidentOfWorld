package com.example.cheonyujung.accidentofworld.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cheonyujung.accidentofworld.R;

/**
 * Created by vmffkxlgnqh1 on 2016. 5. 25..
 *
 */
public class helloWorld extends Fragment {

    public static helloWorld getInstence(){
        return new helloWorld();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.helloworld,container,false);
        return view;
    }
}
