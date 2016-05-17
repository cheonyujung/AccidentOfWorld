package com.example.cheonyujung.accidentofworld;

/**
 * Created by cheonyujung on 2016. 5. 17..
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "accidentOfWorld.db";
    private static final String DANGER_TABLE_NAME = "danger";
    private static final String COUNTRY_TABLE_NAME = "country";
    private static final String POSITION_TABLE_NAME = "position";
    private static final String ACCIDENT_TABLE_NAME = "accident";
    private static final String CONTRACT_TABLE_NAME = "contract";
    private static final String DANGER_AREA_TABLE_NAME = "dangerArea";
    private static final String BOARD_TABLE_NAME = "board";
    private static final String POST_TABLE_NAME = "post";
    private static final String COMMENT_TABLE_NAME = "comment";

    private static final String CREATE_DANGER_TABLE =
            "CREATE TABLE `" + DANGER_TABLE_NAME + "`(" +
                    "country_id INTEGER PRIMARY KEY NOT NULL, " +
                    "danger_type TEXT," +
                    "FOREIGN KEY(country_id) REFERENCES "+
                    "'"+COUNTRY_TABLE_NAME+"'(country_id)"+
                    ");";

    private static final String CREATE_COUNTRY_TABLE =
            "CREATE TABLE `" + COUNTRY_TABLE_NAME + "`(" +
                    "country_id INTEGER PRIMARY KEY NOT NULL, " +
                    "name_kor TEXT NOT NULL," +
                    "name_eng TEXT NOT NULL," +
                    "continent TEXT NOT NULL" +
                    ");";

    private static final String CREATE_DANGER_AREA_TABLE =
            "CREATE TABLE `" + DANGER_AREA_TABLE_NAME + "`(" +
                    "country_id INTEGER PRIMARY KEY NOT NULL, " +
                    "degree TEXT," +
                    "contents TEXT," +
                    "FOREIGN KEY(country_id) REFERENCES "+
                    "'"+COUNTRY_TABLE_NAME+"'(country_id)"+
                    ");";

    private static final String CREATE_POSITION_TABLE =
            "CREATE TABLE `" + POSITION_TABLE_NAME + "`(" +
                    "country_id INTEGER PRIMARY KEY NOT NULL, " +
                    "position_x NUMBERIC(3,2), " +
                    "position y NUMBERIC(3,2), " +
                    "FOREIGN KEY(country_id) REFERENCES "+
                    "'"+COUNTRY_TABLE_NAME+"'(country_id)"+
                    ");";

    private static final String CREATE_ACCIDENT_TABLE =
            "CREATE TABLE `" + ACCIDENT_TABLE_NAME + "`(" +
                    "country_id INTEGER PRIMARY KEY NOT NULL, " +
                    "natural_disater TEXT, " +
                    "MAN_disater TEXT, " +
                    "FOREIGN KEY(country_id) REFERENCES "+
                    "'"+COUNTRY_TABLE_NAME+"'(country_id)"+
                    ");";

    private static final String CREATE_CONTRACT_TABLE =
            "CREATE TABLE `" + CONTRACT_TABLE_NAME + "`(" +
                    "country_id INTEGER PRIMARY KEY NOT NULL, " +
                    "tel TEXT, " +
                    "FOREIGN KEY(country_id) REFERENCES "+
                    "'"+COUNTRY_TABLE_NAME+"'(country_id)"+
                    ");";

    private static final String CREATE_BOARD_TABLE =
            "CREATE TABLE `" + BOARD_TABLE_NAME + "`(" +
                    "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "country_id INTEGER NOT NULL, "+
                    "FOREIGN KEY(country_id) REFERENCES "+
                    "'"+COUNTRY_TABLE_NAME+"'(country_id)"+
                    ");";

    private static final String CREATE_POST_TABLE =
            "CREATE TABLE `" + POST_TABLE_NAME + "`(" +
                    "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "board_id INTEGER NOT NULL, "+
                    "title TEXT NOT NULL, " +
                    "contents TEXT NOT NULL, " +
                    "user_id TEXT NOT NULL, " +
                    "num_like INTEGER NOT NULL, " +
                    "count_num INTEGER NOT NULL, "+
                    "num_dislike INTEGER NOT NULL, " +
                    "FOREIGN KEY(board_id) REFERENCES "+
                    "'"+BOARD_TABLE_NAME+"'(_id)"+
                    ");";

    private static final String CREATE_COMMENT_TABLE =
            "CREATE TABLE `" + COMMENT_TABLE_NAME + "`(" +
                    "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "post_id INTEGER NOT NULL, "+
                    "contents TEXT NOT NULL, " +
                    "user_id TEXT NOT NULL, " +
                    "num_like INTEGER NOT NULL, " +
                    "num_dislike INTEGER NOT NULL, " +
                    "FOREIGN KEY(post_id) REFERENCES "+
                    "'"+POST_TABLE_NAME+"'(board_id)"+
                    ");";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DANGER_TABLE);
        db.execSQL(CREATE_COUNTRY_TABLE);
        db.execSQL(CREATE_DANGER_AREA_TABLE);
        db.execSQL(CREATE_POSITION_TABLE);
        db.execSQL(CREATE_ACCIDENT_TABLE);
        db.execSQL(CREATE_CONTRACT_TABLE);
        db.execSQL(CREATE_BOARD_TABLE);
        db.execSQL(CREATE_POST_TABLE);
        db.execSQL(CREATE_COMMENT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}