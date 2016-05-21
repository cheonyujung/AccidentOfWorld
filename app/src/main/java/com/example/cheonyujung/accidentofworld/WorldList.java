package com.example.cheonyujung.accidentofworld;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by cheonyujung on 2016. 5. 19..
 */
public class WorldList extends Base {

    ListView listView;
    Adapter adapter;
    String url = "http://apis.data.go.kr/1262000/TravelWarningService/getTravelWarningList?ServiceKey=FSXfACOsUM%2Bf4uNB%2F8TPNQcFFKHzJh91ArpRmP%2BrjGs4LIHDiirHcHpuxKqYmJmEf8Ls5YIa1VezmY41uetJ%2BQ%3D%3D&numOfRows=999&pageSize=999&pageNo=1&startPage=1";
    Document doc = null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.country_list);
        HttpURLConnection conn = null;
        URL reqUrl = null;
        adapter = new Adapter();
        listView = (ListView) findViewById(R.id.country_list);
        listView.setAdapter(adapter);
        BackgroundTask task = new BackgroundTask();
        task.execute();
        setCustomActionbar();
    }

    class BackgroundTask extends AsyncTask<String, Void, Document> {

        @Override
        protected Document doInBackground(String... params) {
            return request(url);
        }

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Document document) {
            NodeList nodeList = doc.getElementsByTagName("body");
            Element itemElement = (Element) nodeList.item(0);
            NodeList items = ((Element)nodeList.item(0)).getElementsByTagName("items");
            NodeList itemList =((Element)items.item(0)).getElementsByTagName("item");
            for(int i = 0; i < itemList.getLength(); i++) {
                Node node = itemList.item(i);
                Node item = ((Element)node).getElementsByTagName("countryName").item(0);
                String tag = item.getChildNodes().item(0).getNodeValue();
//                String name = node.getNodeValue();
                adapter.addCountry(tag);
            }
            adapter.notifyDataSetChanged();
            super.onPostExecute(document);
        }
    }

    private Document request(String url) {
        StringBuilder output = new StringBuilder();
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
