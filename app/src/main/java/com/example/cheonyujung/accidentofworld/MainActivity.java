package com.example.cheonyujung.accidentofworld;

import android.app.FragmentManager;
import android.os.Bundle;

import com.example.cheonyujung.accidentofworld.data.DBHelper;
import com.example.cheonyujung.accidentofworld.data.Data;
import com.example.cheonyujung.accidentofworld.data.query.TravelInfoQuery.Country;
import com.example.cheonyujung.accidentofworld.data.query.TravelInfoQuery.CountryDangerMap;
import com.example.cheonyujung.accidentofworld.data.query.TravelInfoQuery.Danger;
import com.example.cheonyujung.accidentofworld.data.query.TravelInfoQuery.Danger_area;

public class MainActivity extends Base {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        com.example.cheonyujung.accidentofworld.fragment.helloWorld helloworld = new com.example.cheonyujung.accidentofworld.fragment.helloWorld();
        FragmentManager fm = getFragmentManager();
        fm.beginTransaction()
                .replace(R.id.body, com.example.cheonyujung.accidentofworld.fragment.helloWorld.getInstence())
                .commit();
        setTitle("Main");
//        setContentView(R.layout.activity_main);
        Data.dbcountry = new Country(MainActivity.this);
        Data.dbdanger = new Danger(MainActivity.this);
        Data.dbCountryDangerMap = new CountryDangerMap(MainActivity.this);
        Data.dbDanger_area = new Danger_area(MainActivity.this);
//        DBHelper dbHelper = new DBHelper(getApplicationContext());
//        dbHelper.onUpgrade(dbHelper.getWritableDatabase(),0,1);
//        setCustomActionbar();
    }
}
