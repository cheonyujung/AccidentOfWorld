package com.example.cheonyujung.accidentofworld.data.query.BoardQuery;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.cheonyujung.accidentofworld.data.DBQuery;
import com.example.cheonyujung.accidentofworld.data.struct.Comment;
import com.example.cheonyujung.accidentofworld.data.struct.Country;
import com.example.cheonyujung.accidentofworld.data.struct.PostItem;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by ohyongtaek on 16. 5. 16..
 */
public class Post extends DBQuery {

    public Post(Context context) {
        super(context);
    }
    public void insert(int board,String title, String content, String user){
        SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd");
        ContentValues values = new ContentValues();
        values.put("board_id",board);
        values.put("title",title);
        values.put("contents",content);
        values.put("user_id",user);
        values.put("num_like",0);
        values.put("num_dislike",0);
        values.put("post_date", date.format(new Date())+"");
        writeDB().insert("post", null, values);
    }
    public void update(int id,String title,String content){
        SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd");
        ContentValues values = new ContentValues();
        values.put("title",title);
        values.put("content",content);
        values.put("post_date",date.format(new Date()));
        writeDB().update("post", values, "_id=?", new String[]{String.valueOf(id)});
    }

    public void update(int id,int like, int dislike) {
        ContentValues values = new ContentValues();
        values.put("num_like",like);
        values.put("num_dislike",dislike);
        writeDB().update("post", values, "_id=?", new String[]{String.valueOf(id)});
    }

    public void delete(int id){
        ArrayList<Comment> comments = Comment.getCommentsByPost_id(id);
        for(Comment comment : comments) {
            comment.delete();
        }
        writeDB().delete("post", "_id=?", new String[]{String.valueOf(id)});
    }

    public ArrayList<com.example.cheonyujung.accidentofworld.data.struct.Post> getPostAllByBoard_id(int board_id) {
        String[] whereArgs = new String[] {String.valueOf(board_id)};
        SQLiteDatabase db = readDB();
        Cursor cursor = db.rawQuery("select * from post where board_id = ?;", whereArgs);
        ArrayList<com.example.cheonyujung.accidentofworld.data.struct.Post> posts = new ArrayList<>();
        while(cursor.moveToNext()){
            com.example.cheonyujung.accidentofworld.data.struct.Post post = new com.example.cheonyujung.accidentofworld.data.struct.Post();
            post.set_id(cursor.getInt(0));
            post.setBoard(cursor.getInt(1));
            post.setTitle(cursor.getString(2));
            post.setContent(cursor.getString(3));
            post.setWrite_user(cursor.getString(4));
            post.setLike_count(cursor.getInt(5));
            post.setDislike_count(cursor.getInt(6));
            post.setPost_date(cursor.getString(7));
            posts.add(post);
        }
        return posts;
    }
    public com.example.cheonyujung.accidentofworld.data.struct.Post getPost(String countryName){

        Country country = Country.getCountry(countryName);
        String[] whereArgs = new String[] {country.getCountry_id()+""};
        SQLiteDatabase db = readDB();
        Cursor cursor =db.rawQuery("select * from post where board_id = ?;", whereArgs);

        if(cursor.moveToFirst()){
            com.example.cheonyujung.accidentofworld.data.struct.Post post = new com.example.cheonyujung.accidentofworld.data.struct.Post();
            post.set_id(cursor.getInt(0));
            post.setBoard(cursor.getInt(1));
            post.setTitle(cursor.getString(2));
            post.setContent(cursor.getString(3));
            post.setWrite_user(cursor.getString(4));
            post.setLike_count(cursor.getInt(5));
            post.setDislike_count(cursor.getInt(6));
            post.setPost_date(cursor.getString(7));
            cursor.close();
            db.close();
            return post;
        }
        return null;
    }

    public com.example.cheonyujung.accidentofworld.data.struct.Post getPost(int post_id) {
        String[] whereArgs = new String[] {String.valueOf(post_id)};
        SQLiteDatabase db = readDB();
        Cursor cursor =db.rawQuery("select * from post where _id = ?;", whereArgs);

        if(cursor.moveToFirst()){
            com.example.cheonyujung.accidentofworld.data.struct.Post post = new com.example.cheonyujung.accidentofworld.data.struct.Post();
            post.set_id(cursor.getInt(0));
            post.setBoard(cursor.getInt(1));
            post.setTitle(cursor.getString(2));
            post.setContent(cursor.getString(3));
            post.setWrite_user(cursor.getString(4));
            post.setLike_count(cursor.getInt(5));
            post.setDislike_count(cursor.getInt(6));
            post.setPost_date(cursor.getString(7));
            post.setComments(Comment.getCommentsByPost_id(cursor.getInt(0)));
            cursor.close();
            db.close();
            return post;
        }
        return null;
    }

    public ArrayList<PostItem> getPostItemByCountryName(String countryName) {
        Country country = Country.getCountry(countryName);
        String[] whereArgs = new String[] {country.getCountry_id() +""};
        ArrayList<PostItem> postItems = new ArrayList<>();
        SQLiteDatabase db = readDB();
        Cursor cursor =db.rawQuery("select title, user_id, post_date,_id from post where board_id = ?;", whereArgs);
        while(cursor.moveToNext()) {
            PostItem postItem = new PostItem();
            postItem.setTitle(cursor.getString(0));
            postItem.setUserName(cursor.getString(1));
            postItem.setDate(cursor.getString(2));
            postItem.setCommentCount(Comment.getCommentsByPost_id(cursor.getInt(3)).size());
            postItem.setPost_id(cursor.getInt(3));
            Log.d("getPostItem",postItem.getPost_id()+"");
            postItems.add(postItem);
        }
        return postItems;
    }
}
