package com.example.cheonyujung.accidentofworld.data.struct;

import com.example.cheonyujung.accidentofworld.data.Data;

import java.util.ArrayList;

/**
 * Created by ohyongtaek on 16. 5. 16..
 */
public class Comment {
    private long _id;
    private String content;
    private String comment_date;
    private String userID;
    private long post;

    public Comment(int id, String content, String comment_date, String userID, int post) {
        this._id = id;
        this.content = content;
        this.comment_date = comment_date;
        this.userID = userID;
        this.post = post;
    }

    public Comment() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public long getPost() {
        return post;
    }

    public void setPost(long post) {
        this.post = post;
    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public String getComment_date() {
        return comment_date;
    }

    public void setComment_date(String date) {
        this.comment_date = date;
    }

    public String getUser_ID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void save() {
        Data.dbComment.insert(this);
    }
    public static ArrayList<Comment> getCommentsByPost_id(long post_id) {
        return Data.dbComment.getCommentByPost_id(post_id);
    }

    public void delete() {
        Data.dbComment.delete(this._id);

    }
}
