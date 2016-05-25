package com.example.cheonyujung.accidentofworld.data.query.TravelInfoQuery;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.cheonyujung.accidentofworld.data.DBHelper;
import com.example.cheonyujung.accidentofworld.data.DBQuery;
import com.example.cheonyujung.accidentofworld.data.struct.*;
import com.example.cheonyujung.accidentofworld.data.struct.Country;

import java.io.File;

/**
 * Created by ohyongtaek on 16. 5. 30..
 */
public class CountryDangerMap extends DBQuery{

    public CountryDangerMap(Context context) {
        super(context);
    }

    public void insert(com.example.cheonyujung.accidentofworld.data.struct.Country country, String path) {
        ContentValues values = new ContentValues();
        values.put("country_id", country.getCountry_id());
        values.put("path", path);
        SQLiteDatabase db = writeDB();
        db.insert("country_danger_map", null, values);
        db.close();
    }

    public com.example.cheonyujung.accidentofworld.data.struct.CountryDangerMap getDangerMap(Country country) {
        String[] whereArgs = new String[] {String.valueOf(country.getCountry_id())};
        SQLiteDatabase db = readDB();
        Cursor cursor = db.rawQuery("select * from country_danger_map where country_id = ?", whereArgs);
        cursor.moveToFirst();
        com.example.cheonyujung.accidentofworld.data.struct.CountryDangerMap dangerMap = new com.example.cheonyujung.accidentofworld.data.struct.CountryDangerMap(country, createBitMap(cursor.getString(1)),cursor.getString(1));
        cursor.close();
        db.close();
        return dangerMap;
    }

    public Bitmap createBitMap(String path) {
        File file = new File(path);
        Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
        return bitmap;
    }
}
