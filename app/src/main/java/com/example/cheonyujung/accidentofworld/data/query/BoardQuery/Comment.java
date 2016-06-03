package com.example.cheonyujung.accidentofworld.data.query.BoardQuery;

import android.content.ContentValues;
import android.content.Context;

import com.example.cheonyujung.accidentofworld.data.DBQuery;

/**
 * Created by ohyongtaek on 16. 5. 16..
 */
public class Comment extends DBQuery {
    public Comment(Context context) {
        super(context);
    }

    public void insert(com.example.cheonyujung.accidentofworld.data.struct.Post post, String content, com.example.cheonyujung.accidentofworld.data.struct.User user){
        ContentValues values = new ContentValues();
        values.put("post_id",post.get_id());
        values.put("content",content);
        values.put("user_id",user.get_id());
        values.put("num_like",0);
        values.put("num_dislike",0);
        writeDB().insert("comment", null, values);
    }
    public void update(int id,String content){
        ContentValues values = new ContentValues();
        values.put("content",content);
        writeDB().update("comment", values, "_id=?", new String[]{String.valueOf(id)});
    }
    public void delete(int id){
        writeDB().delete("comment", "_id=?", new String[]{String.valueOf(id)});
    }
}
