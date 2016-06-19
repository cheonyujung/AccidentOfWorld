package com.example.cheonyujung.accidentofworld.data.struct;

/**
 * Created by ohyongtaek on 16. 5. 16..
 */
public class Comment {
    private int _id;
    private String content;
    private int num_like;
    private int num_dislike;
    private String comment_date;
    private String userID;
    private Post post;

    public Comment(int id, String content, int num_like, int num_dislike, String comment_date, String userID, Post post){
        this._id = id;
        this.content = content;
        this.num_like = num_like;
        this.num_dislike = num_dislike;
        this.comment_date = comment_date;
        this.userID = userID;
        this.post = post;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getNum_dislike() {
        return num_dislike;
    }

    public void setNum_dislike(int num_dislike) {
        this.num_dislike = num_dislike;
    }

    public int getNum_like() {
        return num_like;
    }

    public void setNum_like(int num_like) {
        this.num_like = num_like;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getComment_date() {return comment_date;}

    public void setComment_date(String date) {this.comment_date = date;}

    public String getUser_ID(){ return userID;}

    public void setUserID(String userID){ this.userID = userID;}

}
