package com.example.cheonyujung.accidentofworld.data.struct;

import com.example.cheonyujung.accidentofworld.data.Data;

import java.util.ArrayList;

/**
 * Created by ohyongtaek on 16. 5. 16..
 */
public class Danger_area {
    private Country country;
    private String degree;
    private String contents;
    private long _id;
    public String getContents() {
        return contents;
    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public void save(){
        Data.dbDanger_area.insert(this.country,this.degree,this.contents);
    }

    public static ArrayList<Danger_area> getDanger_areaByCountryName(String countryName) {
        return Data.dbDanger_area.getDanger_areaByCountryName(countryName);
    }
}
