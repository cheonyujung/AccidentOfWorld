package com.example.cheonyujung.accidentofworld.data.struct;

/**
 * Created by ohyongtaek on 16. 5. 16..
 */
public class Comment {
    private int _id;
    private String content;
    private int num_like;
    private int num_dislike;
    private Post post;

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
}
