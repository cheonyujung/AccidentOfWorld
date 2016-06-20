package com.example.cheonyujung.accidentofworld.data.query.TravelInfoQuery;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.cheonyujung.accidentofworld.data.DBQuery;
import com.example.cheonyujung.accidentofworld.data.DangerType;
import com.example.cheonyujung.accidentofworld.data.struct.Country;

/**
 * Created by ohyongtaek on 16. 5. 15..
 */
public class Danger extends DBQuery{

    public Danger(Context context) {
        super(context);
    }

    public void insert(com.example.cheonyujung.accidentofworld.data.struct.Country country,DangerType danger_type){
        ContentValues values = new ContentValues();
        values.put("country_id",country.getCountry_id());
        values.put("danger_type", String.valueOf(danger_type));
        writeDB().insert("danger", null, values);
    }
    public void update(com.example.cheonyujung.accidentofworld.data.struct.Country country,DangerType danger_type){
        ContentValues values = new ContentValues();
        values.put("danger_type", String.valueOf(danger_type));
        writeDB().update("danger", values, "country_id=?", new String[]{String.valueOf(country.getCountry_id())});
    }
    public void delete(com.example.cheonyujung.accidentofworld.data.struct.Country country){
        writeDB().delete("danger", "country_id=?", new String[]{String.valueOf(country.getCountry_id())});
    }

    public com.example.cheonyujung.accidentofworld.data.struct.Danger getDanger(Country country) {

        String[] whereArgs = new String[] {String.valueOf(country.getCountry_id())};
        Cursor cursor =readDB().rawQuery("select * from danger where country_id = ?;", whereArgs);
        if(cursor.moveToFirst()) {
            com.example.cheonyujung.accidentofworld.data.struct.Danger danger = new com.example.cheonyujung.accidentofworld.data.struct.Danger(country, cursor.getString(2));
            danger.set_id(cursor.getLong(0));
            cursor.close();
            return danger;
        }
        return null;
    }

    public com.example.cheonyujung.accidentofworld.data.struct.Danger getDanger(String countryName){
        Country country = Country.getCountry(countryName);
        return getDanger(country);
    }
}
