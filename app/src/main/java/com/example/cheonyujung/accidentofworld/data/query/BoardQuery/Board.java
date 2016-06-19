package com.example.cheonyujung.accidentofworld.data.query.BoardQuery;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.cheonyujung.accidentofworld.data.DBQuery;
import com.example.cheonyujung.accidentofworld.data.struct.*;
import com.example.cheonyujung.accidentofworld.data.struct.Post;

import java.util.ArrayList;

/**
 * Created by ohyongtaek on 16. 5. 16..
 */
public class Board extends DBQuery{
    public Board(Context context) {
        super(context);
    }

    public void insert(int country_id){
        ContentValues values = new ContentValues();
        values.put("_id", country_id);
        writeDB().insert("board", null, values);
    }

    public void delete(int board_id) {
        ArrayList<Post> posts = Post.getPostAllByBoard_id(board_id);
        for(Post post : posts) {
            post.delete();
        }
        ContentValues values = new ContentValues();
        values.put("_id",board_id);
        writeDB().delete("board", "country_id=?", new String[]{String.valueOf(board_id)});
    }
    public com.example.cheonyujung.accidentofworld.data.struct.Board getBoard(String countryName) {
        Country country = Country.getCountry(countryName);

        String[] whereArgs = new String[] {String.valueOf(country.getCountry_id())};
        Cursor cursor =readDB().rawQuery("select * from board where _id = ?;", whereArgs);
        if(cursor.moveToFirst()) {
            com.example.cheonyujung.accidentofworld.data.struct.Board board = new com.example.cheonyujung.accidentofworld.data.struct.Board();
            board.set_id(cursor.getInt(0));
            return board;
        }
        return null;
    }
}
