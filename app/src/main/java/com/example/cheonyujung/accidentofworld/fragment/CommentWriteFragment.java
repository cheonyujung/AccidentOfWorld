package com.example.cheonyujung.accidentofworld.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.cheonyujung.accidentofworld.R;

/**
 * Created by cheonyujung on 2016. 6. 19..
 */
public class CommentWriteFragment extends Fragment {

    public static CommentWriteFragment getInstence(){ return new CommentWriteFragment();}

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.comment_write, container, false);

        Button CommentOk = (Button) view.findViewById(R.id.commentOK);
        Button commentCancle = (Button) view.findViewById(R.id.commentCancel);

        CommentOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });

        commentCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });
        return view;
    }
}
