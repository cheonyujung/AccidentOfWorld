package com.example.cheonyujung.accidentofworld.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.cheonyujung.accidentofworld.PostAdapter;
import com.example.cheonyujung.accidentofworld.R;

/**
 * Created by cheonyujung on 2016. 6. 19..
 */
public class BoardFragment extends Fragment{

    ListView postList;
    PostAdapter commentAdapter = new PostAdapter();
    public static PostFragment getInstence(){
        return new PostFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.post_list, container, false);
        postList = (ListView) view.findViewById(R.id.postList);

        postList.setAdapter(commentAdapter);


        return view;
    }
}
