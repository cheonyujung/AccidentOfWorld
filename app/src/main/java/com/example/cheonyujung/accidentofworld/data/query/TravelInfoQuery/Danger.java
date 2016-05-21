package com.example.cheonyujung.accidentofworld.data.query.TravelInfoQuery;

import android.content.ContentValues;
import android.content.Context;

import com.example.cheonyujung.accidentofworld.data.DBQuery;
import com.example.cheonyujung.accidentofworld.data.DangerType;
import com.example.cheonyujung.accidentofworld.data.struct.*;

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
        writeDB().delete("danger","country_id=?",new String[]{String.valueOf(country.getCountry_id())});
    }
}
