package com.example.cheonyujung.accidentofworld;

import android.app.SearchManager;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.cheonyujung.accidentofworld.data.DBHelper;

/**
* Created by vmffkxlgnqh1 on 2016. 6. 19..
*/
public class CountryNameProvider extends ContentProvider {

    @Override
    public boolean onCreate() {
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        DBHelper dbHelper = new DBHelper(getContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cur = db.rawQuery("select name_ko from country", null);

        MatrixCursor cursor = new MatrixCursor(
                new String[] {
                        BaseColumns._ID,
                        SearchManager.SUGGEST_COLUMN_TEXT_1,
                        SearchManager.SUGGEST_COLUMN_INTENT_DATA_ID
                }
        );
        String query = uri.getLastPathSegment().toUpperCase();
        int limit = Integer.parseInt(uri.getQueryParameter(SearchManager.SUGGEST_PARAMETER_LIMIT));
        int i = 0;

        while(cur.moveToNext() && (cursor.getCount()<limit)){
            String name = cur.getString(0);
            if(name.toUpperCase().contains(query)) {
                cursor.addRow(new Object[]{i, name, i});
            }
            i++;
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}

