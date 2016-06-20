package com.example.cheonyujung.accidentofworld;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.cheonyujung.accidentofworld.data.struct.Post;
import com.example.cheonyujung.accidentofworld.data.struct.PostItem;

import java.util.ArrayList;

/**
 * Created by cheonyujung on 2016. 6. 19..
 */
public class PostAdapter extends BaseAdapter {

    TextView writeUser;
    TextView postTitle;
    TextView postDate;
    TextView commentCount;

    Board board = new Board();
    ArrayList<PostItem> postList;

    public void setPostItems(String countryName) {
        postList = PostItem.getPostItems(countryName);
    }

    public PostAdapter() {
        postList = new ArrayList<>();
    }

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

    public int getPost_id(int i) {
        return postList.get(i).getPost_id();
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

        writeUser.setText(postList.get(i).getUserName());
        postTitle.setText(postList.get(i).getTitle());
        postDate.setText(postList.get(i).getDate());
        commentCount.setText(postList.get(i).getCommentCount() + "");

        return view;
    }

    public void addPost(Post post) {
        post.save();
        PostItem postItem = new PostItem();
        postItem.setTitle(post.getTitle());
        postItem.setCommentCount(0);
        postItem.setUserName(post.getWrite_user());
        postItem.setDate(post.getPost_date());
        postList.add(postItem);
        notifyDataSetChanged();
    }

    public void delete(int position) {
        PostItem postItem = postList.get(position);
        Post post = Post.getPost(postItem.getPost_id());
        post.delete();
        postList.remove(postItem);
        notifyDataSetChanged();
    }
}
