package com.example.cheonyujung.accidentofworld.data.query.TravelInfoQuery;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.cheonyujung.accidentofworld.data.DBQuery;
import com.example.cheonyujung.accidentofworld.data.struct.Country;

/**
 * Created by ohyongtaek on 16. 5. 16..
 */
public class Accident extends DBQuery {
    public Accident(Context context) {
        super(context);
    }

    public void insert(com.example.cheonyujung.accidentofworld.data.struct.Country country, String disater) {
        ContentValues values = new ContentValues();
        values.put("country_id", country.getCountry_id());
        values.put("disater", disater);
        writeDB().insert("accident", null, values);
    }

    public void update(Country country, String natural_disater, String man_disater) {
        ContentValues values = new ContentValues();
        values.put("natural_disater", natural_disater);
        values.put("man_disater", man_disater);
        writeDB().update("accident", values, "country_id=?", new String[]{String.valueOf(country.getCountry_id())});
    }

    public void delete(Country country) {
        writeDB().delete("accident", "country_id=?", new String[]{String.valueOf(country.getCountry_id())});
    }

    public com.example.cheonyujung.accidentofworld.data.struct.Accident getAccident(String countryName) {
        Country country = Country.getCountry(countryName);
        String[] whereArgs = new String[]{String.valueOf(country.getCountry_id())};
        Cursor cursor = readDB().rawQuery("select * from accident where country_id = ?;", whereArgs);
        if (cursor.moveToFirst()) {
            com.example.cheonyujung.accidentofworld.data.struct.Accident accident = new com.example.cheonyujung.accidentofworld.data.struct.Accident();
            accident.setCountry(country);
            accident.setDisater(cursor.getString(1));
            cursor.close();
            return accident;
        }
        return null;
    }
}
