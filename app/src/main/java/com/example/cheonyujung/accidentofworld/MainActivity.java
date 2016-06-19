package com.example.cheonyujung.accidentofworld;

import android.app.FragmentTransaction;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;
import android.util.Log;

import com.example.cheonyujung.accidentofworld.data.DBHelper;
import com.example.cheonyujung.accidentofworld.data.Data;
import com.example.cheonyujung.accidentofworld.data.query.TravelInfoQuery.Accident;
import com.example.cheonyujung.accidentofworld.data.query.TravelInfoQuery.Contact;
import com.example.cheonyujung.accidentofworld.data.query.TravelInfoQuery.Country;
import com.example.cheonyujung.accidentofworld.data.query.TravelInfoQuery.CountryDangerMap;
import com.example.cheonyujung.accidentofworld.data.query.TravelInfoQuery.Danger;
import com.example.cheonyujung.accidentofworld.data.query.TravelInfoQuery.Danger_area;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MainActivity extends Base implements OnMapReadyCallback {
    MapFragment mapfm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Data.dbcountry = new Country(MainActivity.this);
        Data.dbdanger = new Danger(MainActivity.this);
        Data.dbCountryDangerMap = new CountryDangerMap(MainActivity.this);
        Data.dbDanger_area = new Danger_area(MainActivity.this);
        setTitle("World Map");
        mapfm = MapFragment.newInstance();
        DBHelper dbHelper = new DBHelper(getApplicationContext());
        //dbHelper.onUpgrade(dbHelper.getWritableDatabase(),0,1);


        mapfm = MapFragment.newInstance();
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

//        DBHelper dbHelper = new DBHelper(getApplicationContext());
//        dbHelper.onUpgrade(dbHelper.getWritableDatabase(),0,1);
//        setCustomActionbar();
        moveCamera("일본");
    }

    public void moveCamera(String country_name){
        DBHelper dbHelper = new DBHelper(getApplicationContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Toast.makeText(getApplicationContext(), "move camera", Toast.LENGTH_SHORT).show();
        Cursor cursor = db.rawQuery("select name_ko,longitude,latitude from country where name_ko=?",new String[]{country_name});
        while (cursor.moveToNext()){
            LatLng position = new LatLng(cursor.getFloat(2),cursor.getFloat(1));
            mapfm.getMap().moveCamera(CameraUpdateFactory.newLatLngZoom(position, (float) 2.0));
            System.out.println(cursor.getString(0)+cursor.getFloat(1)+cursor.getFloat(2));
        }
    }
}
