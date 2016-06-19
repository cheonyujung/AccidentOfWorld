package com.example.cheonyujung.accidentofworld;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.cheonyujung.accidentofworld.data.struct.Comment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by cheonyujung on 2016. 6. 19..
 */
public class CommentAdapter extends BaseAdapter {

    ArrayList<Comment> commentList = new ArrayList<Comment>();
    TextView userIdView;
    TextView timeView;
    TextView contentView;

    @Override
    public int getCount() {
        return commentList.size();
    }

    @Override
    public Object getItem(int i) {
        return commentList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) viewGroup.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.comment_item, viewGroup, false);
        }

        userIdView = (TextView) view.findViewById(R.id.userID);
        timeView = (TextView) view.findViewById(R.id.commentTime);
        contentView = (TextView) view.findViewById(R.id.commentContent);

        Button modifyComment = (Button) view.findViewById(R.id.commentModify);
        Button deleteComment = (Button) view.findViewById(R.id.commentDelete);

        userIdView.setText(commentList.get(i).getUser_ID());
        timeView.setText(commentList.get(i).getComment_date());
        contentView.setText(commentList.get(i).getContent());

        modifyComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        deleteComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return view;
    }

    public void addComment(String ID, String content){
        SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd");
        Date today = new Date();
//post

        //commentList.add(comment);
    }
}
