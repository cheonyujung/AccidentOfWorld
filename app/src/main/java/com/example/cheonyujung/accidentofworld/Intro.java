package com.example.cheonyujung.accidentofworld;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cheonyujung.accidentofworld.data.DBHelper;
import com.example.cheonyujung.accidentofworld.data.Data;
import com.example.cheonyujung.accidentofworld.data.query.BoardQuery.*;
import com.example.cheonyujung.accidentofworld.data.query.BoardQuery.Board;
import com.example.cheonyujung.accidentofworld.data.query.TravelInfoQuery.Accident;
import com.example.cheonyujung.accidentofworld.data.query.TravelInfoQuery.Contact;
import com.example.cheonyujung.accidentofworld.data.query.TravelInfoQuery.CountryDangerMap;
import com.example.cheonyujung.accidentofworld.data.query.TravelInfoQuery.Danger;
import com.example.cheonyujung.accidentofworld.data.query.TravelInfoQuery.Danger_area;
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
 * Created by cheonyujung on 2016. 6. 19..
 */
public class Intro extends Activity{
    Document doc;
    TextView textView;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro);
        Data.dbcountry = new com.example.cheonyujung.accidentofworld.data.query.TravelInfoQuery.Country(Intro.this);
        Data.dbdanger = new Danger(Intro.this);
        Data.dbCountryDangerMap = new CountryDangerMap(Intro.this);
        Data.dbDanger_area = new Danger_area(Intro.this);
        Data.dbContact = new Contact(Intro.this);
        Data.dbAccident = new Accident(Intro.this);
        Data.dbBoard = new Board(Intro.this);
        Data.dbComment = new Comment(Intro.this);
        Data.dbPost = new Post(Intro.this);
        Data.dbUser = new User(Intro.this);

        textView = (TextView) findViewById(R.id.text);
        textView.setGravity(Gravity.CENTER_HORIZONTAL);
        findViewById(R.id.layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textView.getText().equals("    환영합니다\n 탭을 하면 시작됩니다")) {
                    Intent intent = new Intent(Intro.this, WorldMap.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
        if(checkReady()) {
            endReady();
        } else {
            BackgroundTask backgroundTask = new BackgroundTask();
            backgroundTask.execute();
        }
    }

    boolean checkReady() {
        SharedPreferences preferences = getSharedPreferences("pref",MODE_PRIVATE);
        int version = preferences.getInt("app_version",0);
        return (version == 0 ) ? false : true;
    }
    void endReady(){
        textView.setText("    환영합니다\n 탭을 하면 시작됩니다");
        ((ProgressBar)findViewById(R.id.progressBar)).setVisibility(View.INVISIBLE);
    }

    class BackgroundTask extends AsyncTask<Void, Void, Void> {
        int count = 0;

        @Override
        protected Void doInBackground(Void... params) {


            String url = "http://apis.data.go.kr/1262000/TravelWarningService/getTravelWarningList?ServiceKey=FSXfACOsUM%2Bf4uNB%2F8TPNQcFFKHzJh91ArpRmP%2BrjGs4LIHDiirHcHpuxKqYmJmEf8Ls5YIa1VezmY41uetJ%2BQ%3D%3D&numOfRows=999&pageSize=999&pageNo=1&startPage=1";
            request(url);
            Geocoder gc = new Geocoder(Intro.this);
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
                    if (obj.getDisplayName(Locale.KOREA).equals(name)) {
                        Log.d("testest", obj.getDisplayCountry());
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
                        } else if (name.equals("콩고")) {
                            document = Jsoup.connect("http://ko.wikipedia.org/wiki/" + "콩고 민주 공화국").get();
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
                        Log.d("test", country.getName_ko());
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
            DBHelper dbHelper = new DBHelper(Intro.this);
            dbHelper.onUpgrade(dbHelper.getReadableDatabase(), 0, 1);
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            SharedPreferences preferences = getSharedPreferences("pref",MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("app_version",1);
            editor.commit();
            Log.d("test", count + "");
            editor.commit();
            endReady();
            super.onPostExecute(null);
        }

        @Override
        protected void onProgressUpdate(Void... values) {

            super.onProgressUpdate(values);

        }

        @Override
        protected void onCancelled(Void aVoid) {
            Toast.makeText(Intro.this, "실패하였습니다", Toast.LENGTH_SHORT).show();
            Log.d("fail","fail");
            super.onCancelled(aVoid);
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
