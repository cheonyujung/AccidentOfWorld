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
    Button deleteButton;
    public void setCommentList(ArrayList<Comment> commentList){ this.commentList = commentList;}
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
        return commentList.get(i).get_id();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        final int position = i;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) viewGroup.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.comment_item, viewGroup, false);
        }

        userIdView = (TextView) view.findViewById(R.id.userID);
        timeView = (TextView) view.findViewById(R.id.commentTime);
        contentView = (TextView) view.findViewById(R.id.commentContent);
        deleteButton = (Button) view.findViewById(R.id.commentDelete);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Comment comment = commentList.get(position);
                deleteComment(comment);
            }
        });
        if(Base.user.getId().equals(commentList.get(position).getUser_ID())){
            deleteButton.setVisibility(View.VISIBLE);
        }else{
            deleteButton.setVisibility(View.GONE);
        }
        userIdView.setText(commentList.get(position).getUser_ID());
        timeView.setText(commentList.get(position).getComment_date());
        contentView.setText(commentList.get(position).getContent());




        return view;
    }

    public void deleteComment(Comment comment) {
        comment.delete();
        commentList.remove(comment);
    }
    public void addComment(Comment comment){
        comment.save();
        commentList.add(comment);
        notifyDataSetChanged();
    }
}
