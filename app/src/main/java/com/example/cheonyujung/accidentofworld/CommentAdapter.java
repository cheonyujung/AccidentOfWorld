package com.example.cheonyujung.accidentofworld;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.cheonyujung.accidentofworld.data.struct.Comment;
import com.example.cheonyujung.accidentofworld.data.struct.Post;

import java.util.ArrayList;

/**
 * Created by cheonyujung on 2016. 6. 19..
 */
public class CommentAdapter extends BaseAdapter{

    ArrayList<Comment> commentList ;
    TextView userIdView;
    TextView timeView;
    TextView contentView;
    Button deleteButton;
    Post post;
    DeleteCommentForPost deleteComment;
    DeleteCommentForPostList deleteCommentForPostList;
    AddCommentForPost addCommentForPost;
    AddCommentForPostList addCommentForPostList;
    public interface DeleteCommentForPost {
        void onDeleteComment();
    }
    public interface DeleteCommentForPostList {
        void onDeleteComment();
    }

    public interface AddCommentForPost {
        void onAddComment();
    }
    public interface AddCommentForPostList {
        void onAddComment();
    }
    public CommentAdapter(Post post) {
        this.commentList = post.getComments();
        this.post = post;
    }

    public void setDeleteCommentForPost(DeleteCommentForPost deleteComment) {
        this.deleteComment = deleteComment;
    }

    public void setDeleteCommentForPostList(DeleteCommentForPostList deleteCommentForPostList) {
        this.deleteCommentForPostList = deleteCommentForPostList;
    }

    public void setAddCommentForPostList(AddCommentForPostList addCommentForPostList) {
        this.addCommentForPostList = addCommentForPostList;
    }
    public void setAddCommentForPost(AddCommentForPost addCommentForPost){
        this.addCommentForPost = addCommentForPost;
    }
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
                deleteComment(comment,position);
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

    public void deleteComment(Comment comment,int position) {
        comment.delete();
        post.getComments().remove(comment);
        deleteComment.onDeleteComment();
        deleteCommentForPostList.onDeleteComment();
        notifyDataSetChanged();
    }
    public void addComment(Comment comment,int position){
        comment.save();
        post.getComments().add(comment);
        addCommentForPost.onAddComment();
        addCommentForPostList.onAddComment();
        notifyDataSetChanged();
    }

}
