package com.example.cheonyujung.accidentofworld;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.cheonyujung.accidentofworld.data.struct.Post;

import java.util.ArrayList;

/**
 * Created by cheonyujung on 2016. 6. 19..
 */
public class PostAdapter extends BaseAdapter{

    TextView writeUser;
    TextView postTitle;
    TextView postDate;
    TextView commentCount;

    Board board = new Board();
    ArrayList<Post> postList = new ArrayList<Post>();

    @Override
    public int getCount() {
        return postList.size();
    }

    @Override
    public Object getItem(int i) {
        return postList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) viewGroup.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.post_item, viewGroup, false);
        }

        writeUser = (TextView) view.findViewById(R.id.postWriter);
        postTitle = (TextView) view.findViewById(R.id.postName);
        postDate = (TextView) view.findViewById(R.id.postDate);
        commentCount = (TextView) view.findViewById(R.id.LargecommentCount);

        writeUser.setText(postList.get(i).getWrite_user());
        postTitle.setText(postList.get(i).getTitle());
        postDate.setText(postList.get(i).getPost_date());
        commentCount.setText(postList.get(i).getCommentCount());

        return null;
    }

    public void addItem(String writeUser, String title, String date, int commentCount){
        //Post post = new Post(postList.size(), title, null, null, 0, 0, date, writeUser, );
    }
}
