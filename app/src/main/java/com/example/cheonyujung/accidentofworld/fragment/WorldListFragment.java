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

        Button insertAll = (Button) view.findViewById(R.id.insert);
        insertAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BackgroundTask task = new BackgroundTask();
                task.execute();
            }
        });
        Button reset = (Button) view.findViewById(R.id.reset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbHelper = new DBHelper(getActivity());
                dbHelper.onUpgrade(dbHelper.getReadableDatabase(), 0, 1);
                getCountryList();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Log.d("testest", "success");
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

    class BackgroundTask extends AsyncTask<Void, Void, Void> {
        int count = 0;

        @Override
        protected Void doInBackground(Void... params) {


            String url = "http://apis.data.go.kr/1262000/TravelWarningService/getTravelWarningList?ServiceKey=FSXfACOsUM%2Bf4uNB%2F8TPNQcFFKHzJh91ArpRmP%2BrjGs4LIHDiirHcHpuxKqYmJmEf8Ls5YIa1VezmY41uetJ%2BQ%3D%3D&numOfRows=999&pageSize=999&pageNo=1&startPage=1";
            request(url);
            Geocoder gc = new Geocoder(getActivity());
            NodeList nodeList = doc.getElementsByTagName("body");
            NodeList items = ((Element) nodeList.item(0)).getElementsByTagName("items");
            NodeList itemList = ((Element) items.item(0)).getElementsByTagName("item");
            String[] locales = Locale.getISOCountries();
            ArrayList<String> countryList = new ArrayList<>();
            ArrayList<String> countryKorList = new ArrayList<>();
            ArrayList<Integer> ids = new ArrayList<>();
            for (int i = 0; i < itemList.getLength(); i++) {
                Node node = itemList.item(i);
                Node enName = ((Element) node).getElementsByTagName("countryEnName").item(0);
                Node korName = ((Element) node).getElementsByTagName("countryName").item(0);
                Node countryId = ((Element) node).getElementsByTagName("id").item(0);
                Node continent = ((Element) node).getElementsByTagName("continent").item(0);
                String tag = enName.getChildNodes().item(0).getNodeValue();
                String name = korName.getChildNodes().item(0).getNodeValue();
                String continentName = continent.getChildNodes().item(0).getNodeValue();
                int id = Integer.parseInt(countryId.getChildNodes().item(0).getNodeValue());
                Country country = new Country();
                country.setCountry_id(id);
                country.setName_en(tag);
                country.setName_ko(name);
                country.setContinent(continentName);
                Locale obj = null;
                boolean check = false;
                for (String countryCode : locales) {
                    obj = new Locale("", countryCode);
                    if (obj.getDisplayCountry().equals(tag)) {
                        country.setIso_code(obj.getCountry());
                        check = true;
                        break;
                    }
                }
                if (check) {
                    List<Address> addresses = null; // get the found Address Objects
                    org.jsoup.nodes.Document document = null;
                    try {
                        addresses = gc.getFromLocationName(obj.getDisplayCountry(), 1);
                        if (name.equals("중국")) {
                            document = Jsoup.connect("http://ko.wikipedia.org/wiki/" + "중화인민공화국").get();
                        } else if (name.equals("조지아")) {
                            document = Jsoup.connect("http://ko.wikipedia.org/wiki/" + "조지아_(국가)").get();
                        } else {
                            document = Jsoup.connect("http://ko.wikipedia.org/wiki/" + name).get();
                        }
                        Elements tags = document.select("table.infobox tbody tr");

                        // A list to save the coordinates if they are available
                        for (Address a : addresses) {
                            if (a.hasLatitude() && a.hasLongitude()) {
                                country.setLatitude(a.getLatitude());
                                country.setLongitude(a.getLongitude());
                            }
                        }
                        for (int j = 0; j < tags.size(); j++) {
                            org.jsoup.nodes.Element a = tags.get(j);
                            if (a.child(0).text().equals("수도")) {
                                country.setCapital(a.child(1).text().split(" ")[0]);
                                Log.d("test", a.child(1).text().split(" ")[0]);
                            } else if (a.child(0).text().equals("공용어")) {
                                country.setLanguage(a.child(1).text());
                            } else if (a.child(0).text().equals("통화")) {
                                country.setCurrency(a.child(1).text());
                            }
                            ids.add(id);
                            countryKorList.add(name);
                            countryList.add(tag);
                        }
                        country.save();
                        count++;


                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }

            return null;
        }

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            getCountryList();
            Log.d("count", count + "");
            super.onPostExecute(null);
        }
    }


    private Document request(String url) {
        try {
            URL reqUrl = new URL(url);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder(); //XML문서 빌더 객체를 생성
            InputStream inputStream = reqUrl.openStream();
            doc = db.parse(new InputSource(inputStream)); //XML문서를 파싱한다.
            doc.getDocumentElement().normalize();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return doc;
    }
}

