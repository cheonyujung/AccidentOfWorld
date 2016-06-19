package com.example.cheonyujung.accidentofworld.fragment;

import android.app.Fragment;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.cheonyujung.accidentofworld.Base;
import com.example.cheonyujung.accidentofworld.CountryListAdapter;
import com.example.cheonyujung.accidentofworld.R;
import com.example.cheonyujung.accidentofworld.data.DBHelper;
import com.example.cheonyujung.accidentofworld.data.downloadInfo.GetDownloadsCountryInfo;
import com.example.cheonyujung.accidentofworld.data.struct.Country;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

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

                GetDownloadsCountryInfo countryInfoTask = new GetDownloadsCountryInfo(getActivity(), position, countryListAdapter);
                countryInfoTask.execute(countryListAdapter.getItem(position));

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

}

