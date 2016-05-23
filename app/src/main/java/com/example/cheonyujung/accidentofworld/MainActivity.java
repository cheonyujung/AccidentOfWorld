package com.example.cheonyujung.accidentofworld;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;

import com.example.cheonyujung.accidentofworld.data.DBHelper;
import com.example.cheonyujung.accidentofworld.data.Data;
import com.example.cheonyujung.accidentofworld.data.query.TravelInfoQuery.Country;

public class MainActivity extends Base {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Data.dbcountry = new Country(MainActivity.this);
//        DBHelper dbHelper = new DBHelper(getApplicationContext());
//        dbHelper.onUpgrade(dbHelper.getWritableDatabase(),0,1);
        setCustomActionbar();
    }
}
