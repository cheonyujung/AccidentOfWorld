package com.example.cheonyujung.accidentofworld.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cheonyujung.accidentofworld.Base;
import com.example.cheonyujung.accidentofworld.R;
import com.example.cheonyujung.accidentofworld.WorldMap;
import com.example.cheonyujung.accidentofworld.data.DBHelper;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class WorldMapFragment extends SupportMapFragment implements OnMapReadyCallback {

    GoogleMap map;

    public static WorldMapFragment getInstence(){return new WorldMapFragment();}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.map,null,false);
        map = ((SupportMapFragment)getFragmentManager().findFragmentById(R.id.google_map)).getMap();

        return view;
    }

    //    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        setContentView(R.layout.map);
//
//        FragmentManager fm = getFragmentManager();
//        FragmentTransaction ft = fm.beginTransaction();
//
//        MapFragment mapFragment = (MapFragment) fm.findFragmentById(R.id.google_map);
//
////        ft.replace(R.id.body,com.google.android.gms.maps.MapFragment.newInstance());
//        ft.addToBackStack(null);
//        ft.commit();
//
////        mapFragment.getMapAsync(this);
//
//    }

    @Override
    public void onMapReady(GoogleMap map) {

        addMarker(map);

    }

    public void addMarker(GoogleMap map){
        DBHelper dbHelper = new DBHelper(getActivity());
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select name_ko,longitude,latitude from country;", null);
        while (cursor.moveToNext()) {
            LatLng position = new LatLng(cursor.getFloat(2),cursor.getFloat(1));
            map.addMarker(new MarkerOptions().title(cursor.getString(0)).position(position));
        }
    }
}