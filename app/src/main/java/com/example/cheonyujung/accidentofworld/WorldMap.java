package com.example.cheonyujung.accidentofworld;

import android.app.FragmentTransaction;
import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.cheonyujung.accidentofworld.data.DBHelper;
import com.example.cheonyujung.accidentofworld.data.DangerType;
import com.example.cheonyujung.accidentofworld.data.query.TravelInfoQuery.Country;
import com.example.cheonyujung.accidentofworld.data.query.TravelInfoQuery.Danger;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;


public class WorldMap extends Base implements SearchView.OnQueryTextListener,OnMapReadyCallback {
    MapFragment mapfm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("World Map");
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


        ArrayList<com.example.cheonyujung.accidentofworld.data.struct.Country> countries =
                new Country(this).getCountryAll();

        for(int i=0;i<countries.size();i++){
            com.example.cheonyujung.accidentofworld.data.struct.Country country = countries.get(i);
            com.example.cheonyujung.accidentofworld.data.struct.Danger danger = new Danger(this).getDanger(country.getName_ko());
            LatLng position = new LatLng(country.getLatitude(),country.getLongitude());
            if(danger == null){
                map.addMarker(new MarkerOptions().title(country.getName_ko()).position(position)
                        .icon(BitmapDescriptorFactory.defaultMarker((float)270.0)));
                continue;
            }
            DangerType dangerType = new Danger(this).getDanger(country).getDanger_type();
            if(dangerType == DangerType.high){
                map.addMarker(new MarkerOptions().title(country.getName_ko()).position(position)
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            }
            else if(dangerType == DangerType.middle){
                map.addMarker(new MarkerOptions().title(country.getName_ko()).position(position)
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));
            }
            else{
                map.addMarker(new MarkerOptions().title(country.getName_ko()).position(position)
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
            }
        }
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_layout, menu);

        MenuItem searchItem = menu.findItem(R.id.search_item);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);
        searchView.setQueryHint("나라를 입력해주세요...");

        SearchManager searchManager = (SearchManager)getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(new ComponentName(this,WorldMap.class)));
        searchView.setIconifiedByDefault(false);

        return true;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            Toast.makeText(this, "Searching by: "+ query, Toast.LENGTH_SHORT).show();

        } else if (Intent.ACTION_VIEW.equals(intent.getAction())) {
            String uri = intent.getDataString();
            Toast.makeText(this, "Suggestion: "+ uri, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
