package com.example.cheonyujung.accidentofworld.data.struct;

import com.example.cheonyujung.accidentofworld.data.Data;

import java.util.ArrayList;

/**
 * Created by ohyongtaek on 16. 5. 16..
 */
public class Post {
    private int _id;
    private String title;
    private String content;
    private int board;
    private int like_count;
    private int dislike_count;
    private String post_date;
    private String write_user;
    private ArrayList<Comment> comments;

    public Post(int _id, String title, String content, int boardID, int like_count, int dislike_count, String post_date, String write_user, ArrayList<Comment> comments) {
        this._id = _id;
        this.title = title;
        this.content = content;
        this.board = boardID;
        this.like_count = like_count;
        this.dislike_count = dislike_count;
        this.post_date = post_date;
        this.write_user = write_user;
        this.comments = comments;
    }

    public Post() {

    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        if (comments == null) {
            this.comments = new ArrayList<>();
        } else {
            this.comments = comments;
        }
    }

    public int getBoard() {
        return board;
    }

    public void setBoard(int board) {
        this.board = board;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getDislike_count() {
        return dislike_count;
    }

    public void setDislike_count(int dislike_count) {
        this.dislike_count = dislike_count;
    }

    public int getLike_count() {
        return like_count;
    }

    public void setLike_count(int like_count) {
        this.like_count = like_count;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPost_date() {
        return post_date;
    }

    public void setPost_date(String date) {
        this.post_date = date;
    }

    public String getWrite_user() {
        return write_user;
    }

    public void setWrite_user(String write_user) {
        this.write_user = write_user;
    }

    public void save() {
        Data.dbPost.insert(board, title, content, write_user);
    }

    public static Post getPost(String countryName) {
        return Data.dbPost.getPost(countryName);
    }

    public int getCommentCount() {
        return comments.size();
    }

    public static Post getPost(int post_id) {
        return Data.dbPost.getPost(post_id);
    }

    public void updateLikeCount() {
        Data.dbPost.update(this._id,this.like_count,this.dislike_count);
    }

    public static ArrayList<Post> getPostAllByBoard_id(int board_id) {
        return Data.dbPost.getPostAllByBoard_id(board_id);
    }

    public void update(){
        Data.dbPost.update(this._id,this.title,this.content);
    }

    public void delete() {
        Data.dbPost.delete(this._id);
    }
}
