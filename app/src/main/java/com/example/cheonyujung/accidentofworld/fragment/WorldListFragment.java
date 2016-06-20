package com.example.cheonyujung.accidentofworld.fragment;

import android.app.Fragment;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.cheonyujung.accidentofworld.CountryListAdapter;
import com.example.cheonyujung.accidentofworld.R;
import com.example.cheonyujung.accidentofworld.data.DBHelper;
import com.example.cheonyujung.accidentofworld.data.downloadInfo.GetDownloadsCountryInfo;

import org.w3c.dom.Document;

import java.util.ArrayList;

/**
 * Created by cheonyujung on 2016. 5. 19..
 */
public class WorldListFragment extends Fragment {

    ListView listView;
    CountryListAdapter countryListAdapter;
    Document doc = null;

    public static WorldListFragment getInstence() {
        return new WorldListFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.country_list, container, false);
        countryListAdapter = new CountryListAdapter();
        listView = (ListView) view.findViewById(R.id.country_list);
        listView.setAdapter(countryListAdapter);

        countryListAdapter.notifyDataSetChanged();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                GetDownloadsCountryInfo countryInfoTask = new GetDownloadsCountryInfo(getActivity(), countryListAdapter.getItem(position));
                countryInfoTask.execute();
            }
        });
        getCountryList();


        return view;
    }

    public void getCountryList() {
        countryListAdapter.removeAll();
        DBHelper dbHelper = new DBHelper(getActivity());
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select name_ko from country order by name_ko;", null);
        while (cursor.moveToNext()) {
            countryListAdapter.addCountry(cursor.getString(0));
        }
        countryListAdapter.notifyDataSetChanged();
        cursor.close();
    }

    public void setNewAdapter(ArrayList<String> filteredlist){
        countryListAdapter.setList(filteredlist);
        countryListAdapter.notifyDataSetChanged();
    }

}

