package com.example.cheonyujung.accidentofworld.data.struct;

import android.graphics.Bitmap;

import com.example.cheonyujung.accidentofworld.data.Data;

/**
 * Created by ohyongtaek on 16. 5. 30..
 */
public class CountryDangerMap {
    private Country country;
    private Bitmap image;
    private String path;

    public CountryDangerMap(Country country, Bitmap image,String path) {
        this.country = country;
        this.image = image;
        this.path = path;
    }
    public CountryDangerMap() {

    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public void save() {
        Data.dbCountryDangerMap.insert(country, path);
    }

    public static CountryDangerMap getDangerMap(Country country) {
        return Data.dbCountryDangerMap.getDangerMap(country);
    }

}
