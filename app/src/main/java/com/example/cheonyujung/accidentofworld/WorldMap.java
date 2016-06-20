package com.example.cheonyujung.accidentofworld;

import android.app.FragmentTransaction;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AutoCompleteTextView;
import android.widget.CursorAdapter;
import android.widget.Toast;

import com.example.cheonyujung.accidentofworld.data.DBHelper;
import com.example.cheonyujung.accidentofworld.data.DangerType;
import com.example.cheonyujung.accidentofworld.data.downloadInfo.GetDownloadsCountryInfo;
import com.example.cheonyujung.accidentofworld.data.query.TravelInfoQuery.Country;
import com.example.cheonyujung.accidentofworld.data.query.TravelInfoQuery.Danger;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.HashMap;


public class WorldMap extends Base implements SearchView.OnQueryTextListener,OnMapReadyCallback {

    MapFragment mapfm;
    public MenuItem searchItem;
    public SimpleCursorAdapter simpleCursorAdapter;
    public SearchView searchView;
    com.example.cheonyujung.accidentofworld.data.struct.Country country;
    HashMap<String, Marker> markerHashMap = new HashMap<>();
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

        for(int i=0;i<countries.size();i++) {
            country = countries.get(i);
            com.example.cheonyujung.accidentofworld.data.struct.Danger danger = new Danger(this).getDanger(country.getName_ko());
            LatLng position = new LatLng(country.getLatitude(), country.getLongitude());
            if (danger == null) {
                markerHashMap.put(country.getName_ko(), map.addMarker(new MarkerOptions().title(country.getName_ko()).position(position)
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.placeholder_green))));
                continue;
            }
            DangerType dangerType = new Danger(this).getDanger(country).getDanger_type();
            if (dangerType == DangerType.high) {
                markerHashMap.put(country.getName_ko(), map.addMarker(new MarkerOptions().title(country.getName_ko()).position(position)
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.placeholder_red))));

            } else if (dangerType == DangerType.middle) {
                markerHashMap.put(country.getName_ko(), map.addMarker(new MarkerOptions().title(country.getName_ko()).position(position)
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.placeholder_orange))));
            } else {
                markerHashMap.put(country.getName_ko(), map.addMarker(new MarkerOptions().title(country.getName_ko()).position(position)
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.placeholder_yellow))));
            }
        }

        map.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {

                GetDownloadsCountryInfo countryInfoTask = new GetDownloadsCountryInfo(WorldMap.this, marker.getTitle());
                countryInfoTask.execute();

            }
        });
    }

    public void moveCamera(String country_name){
        DBHelper dbHelper = new DBHelper(getApplicationContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select name_ko,longitude,latitude from country where name_ko=?", new String[]{country_name});
        if(cursor.getCount() ==  0 ){
            Toast.makeText(getApplicationContext(), "검색 결과가 없습니다.", Toast.LENGTH_SHORT).show();
        }
        while (cursor.moveToNext()){
            LatLng position = new LatLng(cursor.getFloat(2),cursor.getFloat(1));
            GoogleMap map = mapfm.getMap();
            String name = cursor.getString(0);

            map.animateCamera(CameraUpdateFactory.newLatLngZoom(position, (float) 5.0));
            markerHashMap.get(name).showInfoWindow();
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        searchItem = menu.findItem(R.id.search_item);
        searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);
        searchView.setQueryHint("나라를 입력해주세요...");

        Cursor cursor = getCountryNames("");
        simpleCursorAdapter = new SimpleCursorAdapter(this,R.layout.search_view,cursor,new String[]{"CountryName"},
                new int[]{R.id.texview},CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);

        AutoCompleteTextView searchAutoCompleteTextView = (AutoCompleteTextView) searchView.findViewById(R.id.search_src_text);
        searchAutoCompleteTextView.setThreshold(1);

        searchView.setSuggestionsAdapter(simpleCursorAdapter);
        searchView.setOnSuggestionListener(new SearchView.OnSuggestionListener() {
            @Override
            public boolean onSuggestionSelect(int position) {
                Cursor cursor = simpleCursorAdapter.getCursor();
                if (cursor.moveToPosition(position)) {
                    String selectedItem = cursor.getString(1);
                    moveCamera(selectedItem);
                    if (searchItem != null) {
                        searchItem.collapseActionView();
                    }
                }
                return false;
            }

            @Override
            public boolean onSuggestionClick(int position) {
                Cursor cursor = simpleCursorAdapter.getCursor();
                if (cursor.moveToPosition(position)) {
                    String selectedItem = cursor.getString(1);
                    moveCamera(selectedItem);
                    if (searchItem != null) {
                        searchItem.collapseActionView();
                    }
                }
                return false;
            }
        });
        return true;
    }



    @Override
    public boolean onQueryTextSubmit(String query) {
        if(searchItem != null){
            searchItem.collapseActionView();
        }
        moveCamera(query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        Cursor cursor = getCountryNames(newText);
        simpleCursorAdapter.changeCursor(cursor);
        return false;
    }


    public Cursor getCountryNames(String query){

        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cur = db.rawQuery("select name_ko from country", null);

        MatrixCursor cursor = new MatrixCursor(
                new String[] {
                        BaseColumns._ID,
                        "CountryName"
                }
        );
        int limit = 50;
        int i = 0;
        while(cur.moveToNext() && (cursor.getCount()<limit)){
            String name = cur.getString(0);
            if(name.toUpperCase().startsWith(query)) {
                cursor.addRow(new Object[]{i, name});
            }
            i++;
        }
        return cursor;
    }

}
