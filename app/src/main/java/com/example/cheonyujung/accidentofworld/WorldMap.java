package com.example.cheonyujung.accidentofworld;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.example.cheonyujung.accidentofworld.data.DBHelper;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class WorldMap extends Base implements OnMapReadyCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_activity);

        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap map) {

        addMarker(map);

    }

    public void addMarker(GoogleMap map){
        DBHelper dbHelper = new DBHelper(getApplicationContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select name_ko,longitude,latitude from country;", null);
        while (cursor.moveToNext()) {
            LatLng position = new LatLng(cursor.getFloat(2),cursor.getFloat(1));
            map.addMarker(new MarkerOptions().title(cursor.getString(0)).position(position));
        }
    }
}