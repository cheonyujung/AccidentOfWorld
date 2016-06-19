package com.example.cheonyujung.accidentofworld.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.cheonyujung.accidentofworld.CommentAdapter;
import com.example.cheonyujung.accidentofworld.R;

/**
 * Created by cheonyujung on 2016. 6. 19..
 */
public class PostFragment extends Fragment{

    CommentAdapter commentAdapter = new CommentAdapter();
    ListView commentList;
    TextView country_post;
    Button commentWrite;
    Button postModify;
    Button postDelete;
    ImageButton like_btn;
    ImageButton dislike_btn;

    public static PostFragment getInstence(){
        return new PostFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.comment_list, container, false);

        commentList = (ListView) view.findViewById(R.id.commentList);
        commentList.setAdapter(commentAdapter);
        View Headerview = inflater.inflate(R.layout.post, container, false);
        commentList.addHeaderView(Headerview);

        commentAdapter.addComment("천유정1", "으아아아아아아");
        commentAdapter.addComment("천유정2", "으아아아아아아");
        commentAdapter.addComment("천유정3", "으아아아아아아");
        commentAdapter.addComment("천유정4", "으아아아아아아");
        commentAdapter.addComment("천유정5", "으아아아아아아");

        commentWrite = (Button) Headerview.findViewById(R.id.commentWriteBtn);
        commentWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), CommentWriteActivity.class));
            }
        });

        postModify = (Button) Headerview.findViewById(R.id.postModify);
        postModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        postDelete = (Button) Headerview.findViewById(R.id.postDelete);
        postDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        like_btn = (ImageButton) Headerview.findViewById(R.id.likeBtn);
        like_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        dislike_btn = (ImageButton) Headerview.findViewById(R.id.dislikeBtn);
        dislike_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return view;
    }
}
