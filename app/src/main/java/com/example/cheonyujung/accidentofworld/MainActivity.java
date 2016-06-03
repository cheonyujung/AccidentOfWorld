package com.example.cheonyujung.accidentofworld;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.example.cheonyujung.accidentofworld.data.DBHelper;
import com.example.cheonyujung.accidentofworld.data.Data;
import com.example.cheonyujung.accidentofworld.data.query.TravelInfoQuery.Country;
import com.example.cheonyujung.accidentofworld.data.query.TravelInfoQuery.CountryDangerMap;
import com.example.cheonyujung.accidentofworld.data.query.TravelInfoQuery.Danger;
import com.example.cheonyujung.accidentofworld.data.query.TravelInfoQuery.Danger_area;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends Base implements OnMapReadyCallback {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Data.dbcountry = new Country(MainActivity.this);
        Data.dbdanger = new Danger(MainActivity.this);
        Data.dbCountryDangerMap = new CountryDangerMap(MainActivity.this);
        Data.dbDanger_area = new Danger_area(MainActivity.this);
        DBHelper dbHelper = new DBHelper(getApplicationContext());
        dbHelper.onUpgrade(dbHelper.getWritableDatabase(),0,1);
        setTitle("World Map");

        MapFragment mapfm = MapFragment.newInstance();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.body,mapfm).commit();

        mapfm.getMapAsync(this);
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        addMarker(googleMap);
    }
    public void addMarker(GoogleMap map){
        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select name_ko,longitude,latitude from country;", null);
        while (cursor.moveToNext()) {
            LatLng position = new LatLng(cursor.getFloat(2),cursor.getFloat(1));
            map.addMarker(new MarkerOptions().title(cursor.getString(0)).position(position));
        }
    }
}
