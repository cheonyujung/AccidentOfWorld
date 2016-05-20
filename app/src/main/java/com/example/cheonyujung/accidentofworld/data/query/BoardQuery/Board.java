package com.example.cheonyujung.accidentofworld.data.query.BoardQuery;

import android.content.ContentValues;
import android.content.Context;

import com.example.cheonyujung.accidentofworld.data.DBQuery;

/**
 * Created by ohyongtaek on 16. 5. 16..
 */
public class Board extends DBQuery{
    public Board(Context context) {
        super(context);
    }

    public void insert(com.example.cheonyujung.accidentofworld.data.struct.Country country){
        ContentValues values = new ContentValues();
        values.put("country_id",country.getCountry_id());
        writeDB().insert("board",null,values);
    }
}
