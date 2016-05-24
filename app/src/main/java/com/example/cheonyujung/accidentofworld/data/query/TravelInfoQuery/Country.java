package com.example.cheonyujung.accidentofworld.data.query.TravelInfoQuery;

import android.content.ContentValues;
import android.content.Context;

import com.example.cheonyujung.accidentofworld.data.DBQuery;

/**
 * Created by ohyongtaek on 16. 5. 15..
 */
public class Country extends DBQuery {
    public Country(Context context) {
        super(context);
    }

    public void insert(int country_id,String name_ko,String name_en,String continent,String ISO_code,double latitude, double longitude,String capital,String currency, String language){
        ContentValues values = new ContentValues();
        values.put("country_id",country_id);
        values.put("name_ko",name_ko);
        values.put("name_en",name_en);
        values.put("continent",continent);
        values.put("iso_code",ISO_code);
        values.put("latitude", latitude);
        values.put("longitude", longitude);
        values.put("capital", capital);
        values.put("currency",currency);
        values.put("language", language);
        writeDB().insert("country", null, values);
    }

    public void update(int old_country_id,int country_id,String name_ko,String name_en,String continent,String ISO_code){
        ContentValues values = new ContentValues();
        values.put("country_id",country_id);
        values.put("name_ko",name_ko);
        values.put("name_en",name_en);
        values.put("continent",continent);
        values.put("iso_code",ISO_code);
        writeDB().update("country", values, "country_id=?", new String[]{Integer.toString(old_country_id)});
    }
    public void delete(int country_id){
        writeDB().delete("country","country_id=?",new String[]{Integer.toString(country_id)});
    }
}
