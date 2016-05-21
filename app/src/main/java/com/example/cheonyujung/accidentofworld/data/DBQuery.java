package com.example.cheonyujung.accidentofworld.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by ohyongtaek on 16. 5. 15..
 */
public class DBQuery extends DBHelper {

    private DBHelper dbHelper;
    protected Context context;
    public DBQuery(Context context) {
        super(context);
        dbHelper = new DBHelper(context);
        this.context = context;
    }

    public SQLiteDatabase readDB(){
        return dbHelper.getReadableDatabase();
    }

    public SQLiteDatabase writeDB(){
        return dbHelper.getWritableDatabase();
    }

}
