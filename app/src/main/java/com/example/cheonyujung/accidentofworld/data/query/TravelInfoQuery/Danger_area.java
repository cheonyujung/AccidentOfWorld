package com.example.cheonyujung.accidentofworld.data.query.TravelInfoQuery;

import android.content.ContentValues;
import android.content.Context;

import com.example.cheonyujung.accidentofworld.data.DBQuery;
import com.example.cheonyujung.accidentofworld.data.struct.Country;

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
        writeDB().insert("danger_area", null, values);
    }

    public void update(Country country,String degree,String contents){
        ContentValues values = new ContentValues();
        values.put("degree",degree);
        values.put("contents",contents);
        writeDB().update("danger_area", values, "country_id=?", new String[]{String.valueOf(country.getCountry_id())});
    }
    public void delete(Country country){
        writeDB().delete("danger_area","country_id=?",new String[]{String.valueOf(country.getCountry_id())});
    }
}
