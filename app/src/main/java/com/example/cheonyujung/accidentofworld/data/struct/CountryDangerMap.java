package com.example.cheonyujung.accidentofworld.data.struct;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.cheonyujung.accidentofworld.data.Data;

import java.io.File;

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
        if(image == null) {
            image = createBitMap(path);
        }
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
    public Bitmap createBitMap(String path) {
        File file = new File(path);
        Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
        return bitmap;
    }
}
