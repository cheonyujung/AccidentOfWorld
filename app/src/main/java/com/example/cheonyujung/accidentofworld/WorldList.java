package com.example.cheonyujung.accidentofworld;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.cheonyujung.accidentofworld.data.DBHelper;
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
public class WorldList extends Base {

    ListView listView;
    Adapter adapter;
    Document doc = null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.country_list);
        adapter = new Adapter();
        listView = (ListView) findViewById(R.id.country_list);
        listView.setAdapter(adapter);

        adapter.addCountry("test");
        adapter.notifyDataSetChanged();
        setCustomActionbar();
        Button insertAll = (Button) findViewById(R.id.insert);
        insertAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BackgroundTask task = new BackgroundTask();
                task.execute();
            }
        });
        Button reset = (Button) findViewById(R.id.reset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbHelper = new DBHelper(WorldList.this);
                dbHelper.onUpgrade(dbHelper.getReadableDatabase(), 0, 1);
                getCountryList();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //GetDangerAboutCountry task = new GetDangerAboutCountry();
                //task.execute();
                Toast.makeText(getApplicationContext(), adapter.getItem(position), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(WorldList.this, Country_info.class);
                Bundle bundle = new Bundle();
                bundle.putString("CountryName", adapter.getItem(position));
                System.out.println(bundle.getString("CountryName")+"!");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        getCountryList();
    }

    public void getCountryList() {
        adapter.removeAll();
        DBHelper dbHelper = new DBHelper(getApplication());
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select name_ko from country order by name_ko;", null);
        while (cursor.moveToNext()) {
            adapter.addCountry(cursor.getString(0));
        }
        adapter.notifyDataSetChanged();
        cursor.close();
    }

    class BackgroundTask extends AsyncTask<Void, Void, Void> {
        int count = 0;

        @Override
        protected Void doInBackground(Void... params) {


            String url = "http://apis.data.go.kr/1262000/TravelWarningService/getTravelWarningList?ServiceKey=FSXfACOsUM%2Bf4uNB%2F8TPNQcFFKHzJh91ArpRmP%2BrjGs4LIHDiirHcHpuxKqYmJmEf8Ls5YIa1VezmY41uetJ%2BQ%3D%3D&numOfRows=999&pageSize=999&pageNo=1&startPage=1";
            request(url);
            Geocoder gc = new Geocoder(getApplicationContext());
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


    class GetDangerAboutCountry extends AsyncTask<String, Void, Document> {


        String url = "http://apis.data.go.kr/1262000/TravelWarningService/getTravelWarningInfo?ServiceKey=FSXfACOsUM%2Bf4uNB%2F8TPNQcFFKHzJh91ArpRmP%2BrjGs4LIHDiirHcHpuxKqYmJmEf8Ls5YIa1VezmY41uetJ%2BQ%3D%3D";
        @Override
        protected void onPostExecute(Document document) {

            super.onPostExecute(document);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Document doInBackground(String... params) {

            DBHelper dbHelper = new DBHelper(WorldList.this);
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            Cursor cursor = db.rawQuery("select country_id from country", null);
            while(cursor.moveToNext()) {
                String reqeust = url + "?" +cursor.getInt(0);
                try {
                    URL reqUrl = new URL(reqeust);
                    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                    DocumentBuilder builder = dbf.newDocumentBuilder();
                    InputStream inputStream = reqUrl.openStream();
                    Document document = builder.parse(new InputSource(inputStream));
                    document.getDocumentElement().normalize();
                    NodeList nodeList = doc.getElementsByTagName("body");
                    NodeList items = ((Element) nodeList.item(0)).getElementsByTagName("items");
                    NodeList itemList = ((Element) items.item(0)).getElementsByTagName("item");
                    for (int i = 0; i < itemList.getLength(); i++) {
                        Node node = itemList.item(i);
                        Node attentionPartial = ((Element) node).getElementsByTagName("attentionPartial").item(0);
                        if(attentionPartial != null) {
                            Node attentionNote = ((Element) node).getElementsByTagName("attentionNote").item(0);
                            Log.d(((Element) node).getElementsByTagName("countryName").item(0).getNodeValue(),attentionPartial.getNodeValue()+" : "+attentionNote.getNodeValue());
                        }
                        Node controlPartial = ((Element) node).getElementsByTagName("controlPartial").item(0);
                        if(controlPartial != null) {
                            Node controlNote = ((Element) node).getElementsByTagName("controlNote").item(0);
                            Log.d(((Element) node).getElementsByTagName("countryName").item(0).getNodeValue(),controlPartial.getNodeValue()+" : "+controlNote.getNodeValue());
                        }
                        Node limitaPartial = ((Element) node).getElementsByTagName("limitaPartial").item(0);
                        if(limitaPartial != null) {
                            Node limitaNote = ((Element) node).getElementsByTagName("limitaNote").item(0);
                            Log.d(((Element) node).getElementsByTagName("countryName").item(0).getNodeValue(),limitaPartial.getNodeValue()+" : "+limitaNote.getNodeValue());
                        }

                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (ParserConfigurationException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (SAXException e) {
                    e.printStackTrace();
                }
            }
            return null;
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

