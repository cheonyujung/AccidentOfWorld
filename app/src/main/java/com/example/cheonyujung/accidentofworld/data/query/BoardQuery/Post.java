package com.example.cheonyujung.accidentofworld.data.query.BoardQuery;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.cheonyujung.accidentofworld.data.DBQuery;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by ohyongtaek on 16. 5. 16..
 */
public class Post extends DBQuery {
    public Post(Context context) {
        super(context);
    }
    public void insert(com.example.cheonyujung.accidentofworld.data.struct.Board board,String title, String content, com.example.cheonyujung.accidentofworld.data.struct.User user){
        SimpleDateFormat date = new SimpleDateFormat("yyyy/mm/dd");
        ContentValues values = new ContentValues();
        values.put("board",board.get_id());
        values.put("title",title);
        values.put("content",content);
        values.put("user_id",user.getId());
        values.put("num_like",0);
        values.put("num_dislike",0);
        values.put("post_date", date+"");
        values.put("post_user", "chyjis");
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

    public ArrayList<com.example.cheonyujung.accidentofworld.data.struct.Post> getPost(int board_id){
        String[] whereArgs = new String[] {board_id+""};
        SQLiteDatabase db = readDB();
        ArrayList<com.example.cheonyujung.accidentofworld.data.struct.Post> posts = new ArrayList<com.example.cheonyujung.accidentofworld.data.struct.Post>();
        Cursor cursor =db.rawQuery("select _id, board_id, title, content,user_id, post_date, count_num, num_like, num_dislike from post where board_id = ?;", whereArgs);
        cursor.moveToFirst();
        while(cursor.moveToNext()){
            int boardID = cursor.getInt(0);
            String title = cursor.getString(2);
            String content = cursor.getString(3);
            String user_id = cursor.getString(4);
            String post_date = cursor.getString(5);
            int comment_num = cursor.getInt(7);
            int likeCount = cursor.getInt(8);
            int dislikeCount = cursor.getInt(9);


        }
        cursor.close();
        db.close();
        return posts;
    }
}
