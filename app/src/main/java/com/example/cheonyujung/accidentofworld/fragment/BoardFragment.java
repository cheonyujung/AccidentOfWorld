package com.example.cheonyujung.accidentofworld.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.cheonyujung.accidentofworld.PostAdapter;
import com.example.cheonyujung.accidentofworld.R;
import com.example.cheonyujung.accidentofworld.data.struct.Post;

/**
 * Created by cheonyujung on 2016. 6. 19..
 */
public class BoardFragment extends Fragment {

    ListView postList;
    PostAdapter postAdapter = new PostAdapter();
    Bundle bundle;
    TextView textView;

    public static BoardFragment getInstence() {
        return new BoardFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.post_list, container, false);
        postList = (ListView) view.findViewById(R.id.postList);

        bundle = getArguments();
        final int country_id = bundle.getInt("Country_id");
        postAdapter.setPostItems(bundle.getString("CountryName"));
        postList.setAdapter(postAdapter);
        Button button = new Button(getActivity());
        button.setText("+");
        button.setTextSize(20);
        button.setGravity(Gravity.CENTER);
        button.setBackgroundColor(Color.LTGRAY);
        postList.addHeaderView(button);
        textView = (TextView) view.findViewById(R.id.noPost);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PostWriteActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("country_id", country_id);
                intent.putExtras(bundle);
                startActivityForResult(intent, 1);
            }
        });

        postList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(getActivity(), PostActivity.class);
                Bundle bundle1 = new Bundle();
                bundle1.putLong("post_id", postAdapter.getPost_id(i - 1));
                bundle1.putString("country_name", bundle.getString("CountryName"));
                bundle1.putInt("list_position", i);
                intent.putExtras(bundle1);
                startActivityForResult(intent, 1);
            }
        });

        setVisibleEmptyView();
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1: {
                if (resultCode == 1) {
                    Bundle bundle = data.getExtras();
                    String content = bundle.getString("post_content");
                    long board_id = bundle.getLong("post_board_id");
                    String title = bundle.getString("post_title");
                    String user_id = bundle.getString("post_user_id");
                    Post post = new Post();
                    post.setWrite_user(user_id);
                    post.setContent(content);
                    post.setTitle(title);
                    post.setBoard(board_id);
                    postAdapter.addPost(post);
                } else if (resultCode == 2) {
                    Bundle bundle = data.getExtras();
                    String content = bundle.getString("post_content");
                    long board_id = bundle.getLong("post_board_id");
                    String title = bundle.getString("post_title");
                    String user_id = bundle.getString("post_user_id");
                    long post_id = bundle.getLong("post_id");
                    Post post = new Post();
                    post.set_id(post_id);
                    post.setWrite_user(user_id);
                    post.setContent(content);
                    post.setTitle(title);
                    post.setBoard(board_id);
                    postAdapter.editPost(post, bundle.getInt("list_position"));
                } else if(resultCode == 3) {
                    Bundle bundle = data.getExtras();
                    int position = bundle.getInt("addComment_list_position");
                    Log.d("test",position+"!!@$");
                    postAdapter.update(position);
                } else if (resultCode == 99) {
                    Bundle bundle = data.getExtras();
                    int list_position = bundle.getInt("list_position");
                    postAdapter.delete(list_position);
                    setVisibleEmptyView();
                }
                break;
            }
        }
    }


    public void setVisibleEmptyView() {
        if (postAdapter.getCount() == 0) {
            textView.setVisibility(View.VISIBLE);
            textView.setGravity(Gravity.CENTER_HORIZONTAL);
        }

    }
}
