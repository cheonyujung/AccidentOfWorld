package com.example.cheonyujung.accidentofworld.data.query.TravelInfoQuery;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.cheonyujung.accidentofworld.data.DBQuery;

import java.util.ArrayList;

/**
 * Created by ohyongtaek on 16. 5. 15..
 */
public class Country extends DBQuery {
    public Country(Context context) {
        super(context);
    }

    public void insert(int country_id,String name_ko,String name_en,String continent,String ISO_code,double latitude, double longitude,String capital,String currency, String language,String path){
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
        values.put("map_path",path);
        SQLiteDatabase db = writeDB();
        db.insert("country", null, values);
        db.close();
    }

    public void update(int old_country_id,int country_id,String name_ko,String name_en,String continent,String ISO_code){
        ContentValues values = new ContentValues();
        values.put("country_id",country_id);
        values.put("name_ko",name_ko);
        values.put("name_en",name_en);
        values.put("continent",continent);
        values.put("iso_code", ISO_code);
        SQLiteDatabase db = writeDB();
        db.update("country", values, "country_id=?", new String[]{Integer.toString(old_country_id)});
        db.close();
    }
    public void delete(int country_id){
        SQLiteDatabase db = writeDB();
        db.delete("country", "country_id=?", new String[]{Integer.toString(country_id)});
        db.close();
    }

    public com.example.cheonyujung.accidentofworld.data.struct.Country getCountry(String name) {
        String[] whereArgs = new String[] {name};
        SQLiteDatabase db = readDB();
        Cursor cursor =db.rawQuery("select * from country where name_ko = ?;", whereArgs);
        cursor.moveToFirst();
        com.example.cheonyujung.accidentofworld.data.struct.Country country = new com.example.cheonyujung.accidentofworld.data.struct.Country(cursor.getInt(0),cursor.getString(1)
        ,cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getDouble(5),cursor.getDouble(6),cursor.getString(7),cursor.getString(8),cursor.getString(9),cursor.getString(10));
        cursor.close();
        db.close();
        return country;
    }

    public ArrayList<String> getCountryNameAll() {
        ArrayList<String> countries = new ArrayList<>();
        SQLiteDatabase db = readDB();
        Cursor cursor = db.rawQuery("select * from country", null);
        while (cursor.moveToNext()) {
            countries.add(cursor.getString(1));
        }
        cursor.close();
        db.close();
        return countries;
    }

    public ArrayList<com.example.cheonyujung.accidentofworld.data.struct.Country> getCountryAll() {
        ArrayList<com.example.cheonyujung.accidentofworld.data.struct.Country> countries = new ArrayList<>();
        SQLiteDatabase db = readDB();
        Cursor cursor = db.rawQuery("select * from country", null);
        while (cursor.moveToNext()) {
            com.example.cheonyujung.accidentofworld.data.struct.Country country = new com.example.cheonyujung.accidentofworld.data.struct.Country(cursor.getInt(0),cursor.getString(1)
                    ,cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getDouble(5),cursor.getDouble(6),cursor.getString(7),cursor.getString(8),cursor.getString(9),cursor.getString(10));
            countries.add(country);
        }
        cursor.close();
        db.close();

        return countries;
    }

    public ArrayList<String> search(String name_ko) {
        ArrayList<String> countries = new ArrayList<>();
        SQLiteDatabase db = readDB();
        Cursor cursor = db.rawQuery("select * from country where name_ko=?",new String[]{"*" + name_ko + "*" });
        while (cursor.moveToNext()) {
            countries.add(cursor.getString(1));
        }
        cursor.close();
        db.close();
        return countries;
    }
}
