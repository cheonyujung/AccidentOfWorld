package com.example.cheonyujung.accidentofworld.data.query.BoardQuery;

import android.content.ContentValues;
import android.content.Context;

import com.example.cheonyujung.accidentofworld.data.DBQuery;
import com.example.cheonyujung.accidentofworld.data.struct.*;

/**
 * Created by ohyongtaek on 16. 5. 16..
 */
public class Post extends DBQuery {
    public Post(Context context) {
        super(context);
    }
    public void insert(com.example.cheonyujung.accidentofworld.data.struct.Board board,String title, String content, com.example.cheonyujung.accidentofworld.data.struct.User user){
        ContentValues values = new ContentValues();
        values.put("board",board.get_id());
        values.put("title",title);
        values.put("content",content);
        values.put("user_id",user.getId());
        values.put("num_like",0);
        values.put("num_dislike",0);
        writeDB().insert("post", null, values);
    }
    public void update(int id,String title,String content){
        ContentValues values = new ContentValues();
        values.put("title",title);
        values.put("content",content);
        writeDB().update("post", values, "_id=?", new String[]{String.valueOf(id)});
    }
    public void delete(int id){
        writeDB().delete("post","_id=?",new String[]{String.valueOf(id)});
    }
}
