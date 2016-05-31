package com.example.cheonyujung.accidentofworld;

import android.app.FragmentManager;
import android.os.Bundle;

import com.example.cheonyujung.accidentofworld.data.Data;
import com.example.cheonyujung.accidentofworld.data.query.TravelInfoQuery.Country;

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
//        DBHelper dbHelper = new DBHelper(getApplicationContext());
//        dbHelper.onUpgrade(dbHelper.getWritableDatabase(),0,1);
//        setCustomActionbar();
    }
}
