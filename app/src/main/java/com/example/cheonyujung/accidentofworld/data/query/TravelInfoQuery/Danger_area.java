package com.example.cheonyujung.accidentofworld.data.query.TravelInfoQuery;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.cheonyujung.accidentofworld.data.DBQuery;
import com.example.cheonyujung.accidentofworld.data.struct.Country;

import java.util.ArrayList;

/**
 * Created by ohyongtaek on 16. 5. 15..
 */
public class Danger_area extends DBQuery {
    public Danger_area(Context context) {
        super(context);
    }

    public void insert(com.example.cheonyujung.accidentofworld.data.struct.Country country,String degree,String contents){
        ContentValues values = new ContentValues();
        values.put("country_id",country.getCountry_id());
        values.put("degree",degree);
        values.put("contents",contents);
        writeDB().insert("dangerArea", null, values);
    }

    public void update(Country country,String degree,String contents){
        ContentValues values = new ContentValues();
        values.put("degree",degree);
        values.put("contents",contents);
        writeDB().update("dangerArea", values, "country_id=?", new String[]{String.valueOf(country.getCountry_id())});
    }
    public void delete(Country country){
        writeDB().delete("dangerArea", "country_id=?", new String[]{String.valueOf(country.getCountry_id())});
    }

    public ArrayList<com.example.cheonyujung.accidentofworld.data.struct.Danger_area> getDanger_areaByCountryName(String countryName) {
        Country country = Country.getCountry(countryName);
        ArrayList<com.example.cheonyujung.accidentofworld.data.struct.Danger_area> danger_areas = new ArrayList<>();
        SQLiteDatabase db = readDB();
        String []whereArgs = new String[]{String.valueOf(country.getCountry_id())};
        Cursor cursor = db.rawQuery("select * from dangerArea where country_id=?", whereArgs);
        while (cursor.moveToNext()) {
            com.example.cheonyujung.accidentofworld.data.struct.Danger_area danger_area = new com.example.cheonyujung.accidentofworld.data.struct.Danger_area();
            danger_area.setCountry(country);
            danger_area.set_id(cursor.getLong(0));
            danger_area.setDegree(cursor.getString(2));
            danger_area.setContents(cursor.getString(3));
            danger_areas.add(danger_area);
        }
        cursor.close();
        db.close();

        return danger_areas;
    }
}
