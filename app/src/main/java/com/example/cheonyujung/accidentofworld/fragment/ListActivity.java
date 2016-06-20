package com.example.cheonyujung.accidentofworld.fragment;

import android.app.FragmentManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AutoCompleteTextView;
import android.widget.CursorAdapter;

import com.example.cheonyujung.accidentofworld.Base;
import com.example.cheonyujung.accidentofworld.R;
import com.example.cheonyujung.accidentofworld.data.DBHelper;
import com.example.cheonyujung.accidentofworld.data.downloadInfo.GetDownloadsCountryInfo;

import java.util.ArrayList;

/**
 * Created by vmffkxlgnqh1 on 2016. 5. 28..
 */
public class ListActivity extends Base implements SearchView.OnQueryTextListener {
    com.example.cheonyujung.accidentofworld.fragment.WorldListFragment worldListFragment;
    Cursor cur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        super.setTitle("Country List");

        worldListFragment = com.example.cheonyujung.accidentofworld.fragment.WorldListFragment.getInstence();
        FragmentManager fm = getFragmentManager();
        fm.beginTransaction()
                .replace(R.id.body, worldListFragment)
                .commit();


    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        MenuItem searchItem = menu.findItem(R.id.search_item);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);
        searchView.setQueryHint("나라를 입력해주세요...");
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        cur.moveToFirst();
        while (cur.moveToNext()){
            if(query.matches(cur.getString(0))){

            }
        }

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        worldListFragment.setNewAdapter(listFilter(newText));

        return false;
    }

    public ArrayList<String> listFilter(String query){

        ArrayList<String> filteredList = new ArrayList<String>();

        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cur = db.rawQuery("select name_ko from country", null);

        int limit = 50;
        while(cur.moveToNext() && (filteredList.size()<limit)){
            String name = cur.getString(0);
            if(name.toUpperCase().startsWith(query)) {
                filteredList.add(name);
            }
        }
        return filteredList;
    }
}
