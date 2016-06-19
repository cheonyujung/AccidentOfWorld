package com.example.cheonyujung.accidentofworld.data.downloadInfo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.example.cheonyujung.accidentofworld.CountryListAdapter;
import com.example.cheonyujung.accidentofworld.data.DangerType;
import com.example.cheonyujung.accidentofworld.data.Data;
import com.example.cheonyujung.accidentofworld.data.struct.Accident;
import com.example.cheonyujung.accidentofworld.data.struct.Contact;
import com.example.cheonyujung.accidentofworld.data.struct.Country;
import com.example.cheonyujung.accidentofworld.data.struct.CountryDangerMap;
import com.example.cheonyujung.accidentofworld.data.struct.Danger;
import com.example.cheonyujung.accidentofworld.data.struct.Danger_area;
import com.example.cheonyujung.accidentofworld.fragment.CountryInfo;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by ohyongtaek on 16. 6. 18..
 */
public class GetDownloadsCountryInfo extends AsyncTask<String, Integer, Void> {

    String contactUrl;
    String dangerInfoUrl;
    String accidentUrl;
    int listview_position = 0;
    CountryListAdapter adapter;
    Context context;

    public GetDownloadsCountryInfo(Context context, int position, CountryListAdapter adapter) {
        contactUrl = Data.contactUrl;
        dangerInfoUrl = Data.countryDangerInfoURL;
        this.accidentUrl = Data.accidentUrl;
        this.adapter = adapter;
        this.context = context;
        this.listview_position = position;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        Bundle bundle = new Bundle();
        bundle.putString("CountryName", adapter.getItem(listview_position));
        Intent intent = new Intent(context, CountryInfo.class);

        intent.putExtras(bundle);
        Log.d("test", "complete");
        context.startActivity(intent);
        super.onPostExecute(aVoid);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected Void doInBackground(String... params) {
        if(Data.dbContact.getContact(params[0])==null) {
            getContactInfo(params[0]);
        }
        if(Data.dbDanger_area.getDanger_areaByCountryName(params[0]).size() == 0) {
            getDangerInfo(params[0]);
        }
        if(Data.dbAccident.getAccident(params[0]) == null) {
            getAccidentInfo(params[0]);
        }
        return null;
    }

    public void getContactInfo(String countryName) {

        Country country = Country.getCountry(countryName);
        try {
            String reqeust = contactUrl + "&" + "id=" + country.getCountry_id();
            URL reqUrl = new URL(reqeust);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dbf.newDocumentBuilder();
            InputStream inputStream = reqUrl.openStream();
            Document document = builder.parse(new InputSource(inputStream));
            document.getDocumentElement().normalize();
            NodeList body = document.getElementsByTagName("body");
            NodeList itemList = ((Element) body.item(0)).getElementsByTagName("item");
            Node contactNode = ((Element) itemList.item(0)).getElementsByTagName("contact").item(0);
            Contact contact = new Contact();
            contact.setCountry(country);
            contact.setTel(contactNode.getTextContent());
            contact.save();
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

    public boolean getDangerInfo(String countryName) {
        Country country = Country.getCountry(countryName);
        String reqeust = dangerInfoUrl + "&" + "id=" + country.getCountry_id();
        Log.d("testRequest",reqeust);
        try {
            URL reqUrl = new URL(reqeust);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dbf.newDocumentBuilder();
            InputStream inputStream = reqUrl.openStream();
            Document document = builder.parse(new InputSource(inputStream));
            document.getDocumentElement().normalize();
            NodeList body = document.getElementsByTagName("body");
            NodeList itemList = ((Element) body.item(0)).getElementsByTagName("item");
            Node dangerNode = itemList.item(0);
            Node attentionPartial = ((Element) dangerNode).getElementsByTagName("attentionPartial").item(0);
            if (attentionPartial != null) {
                Node attentionNote = ((Element) dangerNode).getElementsByTagName("attentionNote").item(0);
                Danger danger = new Danger();
                danger.setCountry(country);
                danger.setDanger_type(DangerType.low);
                danger.save();
                Danger_area danger_area = new Danger_area();
                danger_area.setCountry(country);
                danger_area.setContents(attentionNote.getTextContent());
                danger_area.setDegree(attentionPartial.getTextContent());
                danger_area.save();
                Log.d(attentionPartial.getTextContent(), attentionNote.getTextContent());
            }
            Node controlPartial = ((Element) dangerNode).getElementsByTagName("controlPartial").item(0);
            if (controlPartial != null) {
                Node controlNote = ((Element) dangerNode).getElementsByTagName("controlNote").item(0);
                Danger danger = new Danger();
                danger.setCountry(country);
                danger.setDanger_type(DangerType.middle);
                danger.save();
                Danger_area danger_area = new Danger_area();
                danger_area.setCountry(country);
                danger_area.setContents(controlNote.getTextContent());
                danger_area.setDegree(controlPartial.getTextContent());
                danger_area.save();
                Log.d(controlPartial.getTextContent(), controlNote.getTextContent());
            }
            Node limitaPartial = ((Element) dangerNode).getElementsByTagName("limitaPartial").item(0);
            if (limitaPartial != null) {
                Node limitaNote = ((Element) dangerNode).getElementsByTagName("limitaNote").item(0);
                Danger danger = new Danger();
                danger.setCountry(country);
                danger.setDanger_type(DangerType.high);
                danger.save();
                Danger_area danger_area = new Danger_area();
                danger_area.setCountry(country);
                danger_area.setContents(limitaNote.getTextContent());
                danger_area.setDegree(limitaPartial.getTextContent());
                danger_area.save();
                Log.d(limitaPartial.getTextContent(), limitaNote.getTextContent());
            }
            Node danger_image = ((Element) dangerNode).getElementsByTagName("imgUrl2").item(0);
            if (danger_image != null) {
                Log.d("test",danger_image.getTextContent());
                CountryDangerMap dangerMap = new CountryDangerMap();
                dangerMap.setCountry(country);
                getDangerMapImage(danger_image.getTextContent(), dangerMap);
            }

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void getDangerMapImage(String url, CountryDangerMap dangerMap) {
        String filepath = "";
        try {
            URL reqUrl = new URL(url);
            HttpURLConnection urlConnection = (HttpURLConnection) reqUrl.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setDoOutput(true);
            urlConnection.connect();
            String filename = dangerMap.getCountry().getName_ko() + "_danger.png";
            File file = new File(context.getFilesDir(), filename);
            if (!file.createNewFile()) {
                file.delete();
                file.createNewFile();
            }
            FileOutputStream fileOutput = new FileOutputStream(file);
            InputStream inputStream = urlConnection.getInputStream();
            int totalSize = urlConnection.getContentLength();
            int downloadedSize = 0;
            byte[] buffer = new byte[1024];
            int bufferLength = 0;
            while ((bufferLength = inputStream.read(buffer)) > 0) {
                fileOutput.write(buffer, 0, bufferLength);
                downloadedSize += bufferLength;
            }
            fileOutput.close();
            filepath = file.getPath();
            Log.d("test",filepath);
            dangerMap.setPath(filepath);
            dangerMap.setImage(createBitMap(filepath));
            dangerMap.save();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Bitmap createBitMap(String path) {
        File file = new File(path);
        Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
        return bitmap;
    }

    public boolean getAccidentInfo(String countryName) {
        Country country = Country.getCountry(countryName);
        String reqeust = accidentUrl + "&" + "id=" + country.getCountry_id();
        try {
            URL reqUrl = new URL(reqeust);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dbf.newDocumentBuilder();
            InputStream inputStream = reqUrl.openStream();
            Document document = builder.parse(new InputSource(inputStream));
            document.getDocumentElement().normalize();
            NodeList body = document.getElementsByTagName("body");
            NodeList itemList = ((Element) body.item(0)).getElementsByTagName("item");
            Node newsNode = ((Element) itemList.item(0)).getElementsByTagName("news").item(0);
            Accident accident = new Accident();
            accident.setCountry(country);
            accident.setDisater(newsNode.getTextContent());
            accident.save();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
