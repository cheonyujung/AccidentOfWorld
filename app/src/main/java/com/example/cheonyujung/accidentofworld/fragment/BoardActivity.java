package com.example.cheonyujung.accidentofworld.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

import com.example.cheonyujung.accidentofworld.Base;
import com.example.cheonyujung.accidentofworld.R;
import com.example.cheonyujung.accidentofworld.data.struct.Country;

/**
 * Created by cheonyujung on 2016. 6. 19..
 */
public class BoardActivity extends Base {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        super.setTitle(bundle.getString("CountryName"));

        int country_id = Country.getCountry(bundle.getString("CountryName")).getCountry_id();

        Bundle bundle1 = new Bundle();
        bundle1.putInt("Country_id", country_id);
        bundle1.putString("CountryName", bundle.getString("CountryName"));

        FragmentManager fm = getFragmentManager();
        Fragment fragment = BoardFragment.getInstence();
        fragment.setArguments(bundle1);

        fm.beginTransaction()
                .replace(R.id.body, fragment)
                .commit();

    }
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.search_item).setVisible(false);
        return super.onPrepareOptionsMenu(menu);
    }
}
