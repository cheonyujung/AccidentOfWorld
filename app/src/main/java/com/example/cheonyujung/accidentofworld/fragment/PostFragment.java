package com.example.cheonyujung.accidentofworld.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cheonyujung.accidentofworld.Base;
import com.example.cheonyujung.accidentofworld.CommentAdapter;
import com.example.cheonyujung.accidentofworld.R;
import com.example.cheonyujung.accidentofworld.data.DBHelper;
import com.example.cheonyujung.accidentofworld.data.struct.Comment;
import com.example.cheonyujung.accidentofworld.data.struct.Post;

/**
 * Created by cheonyujung on 2016. 6. 19..
 */
public class PostFragment extends Fragment{

    CommentAdapter commentAdapter;
    ListView commentList;
    TextView postCountry;
    TextView postTitle;
    TextView postWriter;
    TextView postTime;
    TextView postContent;
    TextView commentCount;
    TextView likeCount;
    TextView dislikeCount;
    Button commentWrite;
    Button postModify;
    Button postDelete;
    ImageButton like_btn;
    ImageButton dislike_btn;
    Post post;
    long post_id;
    int list_position;
    TextView textView;
    public static PostFragment getInstence(){
        return new PostFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.comment_list, container, false);

        Bundle bundle = getArguments();
        post_id = bundle.getLong("post_id");
        list_position = bundle.getInt("list_position");
        post = Post.getPost(post_id);
        final String country_name = bundle.getString("country_name");
        commentAdapter = new CommentAdapter(post);

        commentList = (ListView) view.findViewById(R.id.commentList);
        commentAdapter.setCommentList(post.getComments());
        commentList.setAdapter(commentAdapter);
        View Headerview = inflater.inflate(R.layout.post, container, false);
        commentList.addHeaderView(Headerview);
        textView = (TextView) view.findViewById(R.id.noComment);

        postCountry = (TextView) Headerview.findViewById(R.id.country_post);
        postTitle = (TextView) Headerview.findViewById(R.id.postTitle);
        postWriter = (TextView) Headerview.findViewById(R.id.writer);
        postTime = (TextView) Headerview.findViewById(R.id.postTime);
        commentCount = (TextView) Headerview.findViewById(R.id.commentCount);
        likeCount = (TextView) Headerview.findViewById(R.id.likeCount);
        dislikeCount = (TextView) Headerview.findViewById(R.id.disLikeCount);
        postContent = (TextView) Headerview.findViewById(R.id.content);

        Toast.makeText(getActivity(), post.getWrite_user(), Toast.LENGTH_SHORT).show();

        postCountry.setText(country_name);
        postTitle.setText(post.getTitle());
        postWriter.setText(post.getWrite_user());
        postTime.setText(post.getPost_date());
        commentCount.setText(post.getComments().size()+"");
        likeCount.setText(post.getLike_count()+"");
        dislikeCount.setText(post.getDislike_count()+"");
        postContent.setText(post.getContent());



        commentWrite = (Button) Headerview.findViewById(R.id.commentWriteBtn);
        commentWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CommentWriteActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("postTitle", post.getTitle());
                bundle.putString("postWriter", post.getWrite_user());
                bundle.putString("postDate", post.getPost_date());
                bundle.putLong("post_id", post.get_id());
                intent.putExtras(bundle);
                startActivityForResult(intent, 1);
            }
        });

        postModify = (Button) Headerview.findViewById(R.id.postModify);
        postModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PostWriteActivity.class);
                Bundle bundle = new Bundle();
                bundle.putBoolean("edit", true);
                bundle.putLong("post_id", post.get_id());
                bundle.putLong("post_board_id", post.getBoard());
                bundle.putString("post_content", post.getContent());
                bundle.putString("post_title", post.getTitle());
                bundle.putString("post_user_id", Base.user.getId());

                intent.putExtras(bundle);
                startActivityForResult(intent, 2);
            }
        });

        postDelete = (Button) Headerview.findViewById(R.id.postDelete);
        postDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getActivity().getIntent();
                Bundle bundle1 = intent.getExtras();
                bundle1.putLong("delete_post_id", post_id);
                bundle1.putInt("list_position",list_position-1);
                intent.putExtras(bundle1);
                getActivity().setResult(99,intent);
                getActivity().finish();
            }
        });

        like_btn = (ImageButton) Headerview.findViewById(R.id.likeBtn);
        like_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                post.updateLikeCount();
                likeCount.setText(post.getLike_count() + "");
            }
        });

        dislike_btn = (ImageButton) Headerview.findViewById(R.id.dislikeBtn);
        dislike_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                post.updateDisLikeCount();
                dislikeCount.setText(post.getDislike_count() + "");
            }
        });

        CommentAdapter.deleteComment deleteComment = new CommentAdapter.deleteComment() {
            @Override
            public void onDeleteComment() {
                commentCount.setText(post.getComments().size() + "");
            }
        };
        commentAdapter.setDeleteComment(deleteComment);
        setVisibleEmptyView();
        return view;
    }

    public void setVisibleEmptyView() {
        if(commentAdapter.getCount() == 0){
            textView.setVisibility(View.VISIBLE);
            textView.setGravity(Gravity.CENTER_HORIZONTAL);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:{
                if(resultCode == Activity.RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    String content = bundle.getString("comment_content");
                    long post_id = bundle.getLong("comment_post_id");
                    String user_id = Base.user.getId();
                    Comment comment = new Comment();
                    comment.setUserID(user_id);
                    comment.setContent(content);
                    comment.setPost(post_id);
                    commentAdapter.addComment(comment);
                    commentCount.setText(post.getComments().size() + "");
                    bundle.putInt("addComment_list_position",list_position-1);
                    data.putExtras(bundle);
                    getActivity().setResult(3,data);
                }
                break;
            }
            case 2:{
                if (resultCode == 2) {
                    Bundle bundle = data.getExtras();
                    bundle.putInt("list_position", list_position - 1);
                    data.putExtras(bundle);
                    getActivity().setResult(2, data);
                    getActivity().finish();
                }
                break;
            }
        }
    }
}
