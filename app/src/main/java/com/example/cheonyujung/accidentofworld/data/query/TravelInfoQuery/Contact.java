package com.example.cheonyujung.accidentofworld.data.query.TravelInfoQuery;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.cheonyujung.accidentofworld.data.DBQuery;
import com.example.cheonyujung.accidentofworld.data.struct.Country;

/**
 * Created by ohyongtaek on 16. 5. 16..
 */
public class Contact extends DBQuery {
    public Contact(Context context) {
        super(context);
    }

    public void insert(com.example.cheonyujung.accidentofworld.data.struct.Country country, String tel){
        ContentValues values = new ContentValues();
        values.put("country_id",country.getCountry_id());
        values.put("tel",tel);
        writeDB().insert("contact", null, values);
    }

    public void update(Country country,String tel){
        ContentValues values = new ContentValues();
        values.put("tel",tel);
        writeDB().update("contact", values, "country_id=?", new String[]{String.valueOf(country.getCountry_id())});
    }

    public void delete(Country country){
        writeDB().delete("contact", "country_id=?", new String[]{String.valueOf(country.getCountry_id())});
    }

    public com.example.cheonyujung.accidentofworld.data.struct.Contact getContact(String countryName) {
        Country country = Country.getCountry(countryName);
        String[] whereArgs = new String[] {String.valueOf(country.getCountry_id())};
        Cursor cursor =readDB().rawQuery("select * from contact where country_id = ?;", whereArgs);
        if(cursor.moveToFirst()) {
            com.example.cheonyujung.accidentofworld.data.struct.Contact contact = new com.example.cheonyujung.accidentofworld.data.struct.Contact();
            contact.setCountry(country);
            contact.setTel(cursor.getString(1));
            return contact;
        }
        return null;
    }
}
