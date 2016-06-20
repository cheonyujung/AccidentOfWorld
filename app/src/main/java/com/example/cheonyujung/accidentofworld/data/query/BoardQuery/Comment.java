package com.example.cheonyujung.accidentofworld.data.query.BoardQuery;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.cheonyujung.accidentofworld.data.DBQuery;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by ohyongtaek on 16. 5. 16..
 */
public class Comment extends DBQuery {
    public Comment(Context context) {
        super(context);
    }


    public void insert(com.example.cheonyujung.accidentofworld.data.struct.Comment comment) {
        SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd");
        ContentValues values = new ContentValues();
        values.put("post_id", comment.getPost());
        values.put("contents", comment.getContent());
        values.put("user_id", comment.getUser_ID());
        comment.setComment_date(date.format(new Date()));
        values.put("comment_date", comment.getComment_date());
        long id;
        if((id =writeDB().insert("comment", null, values)) != -1){
            comment.set_id(id);
        }
    }

    public void update(int id, String content) {
        ContentValues values = new ContentValues();
        values.put("content", content);
        writeDB().update("comment", values, "_id=?", new String[]{String.valueOf(id)});
    }

    public void delete(long id) {
        writeDB().delete("comment", "_id=?", new String[]{String.valueOf(id)});
    }

    public ArrayList<com.example.cheonyujung.accidentofworld.data.struct.Comment> getCommentByPost_id(long post_id) {
        String[] whereArgs = new String[]{post_id + ""};
        SQLiteDatabase db = readDB();
        Cursor cursor = db.rawQuery("select * from comment where post_id = ?;", whereArgs);
        ArrayList<com.example.cheonyujung.accidentofworld.data.struct.Comment> comments = new ArrayList<>();

        while (cursor.moveToNext()) {
            com.example.cheonyujung.accidentofworld.data.struct.Comment comment = new com.example.cheonyujung.accidentofworld.data.struct.Comment();
            comment.set_id(cursor.getInt(0));
            comment.setPost(post_id);
            comment.setContent(cursor.getString(2));
            comment.setUserID(cursor.getString(3));
            comment.setComment_date(cursor.getString(4));
            comments.add(comment);
        }
        return comments;
    }
}
