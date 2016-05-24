package com.example.cheonyujung.accidentofworld.data.struct;

import com.example.cheonyujung.accidentofworld.data.Data;

/**
 * Created by ohyongtaek on 16. 5. 15..
 */
public class Country {
    private int country_id;
    private String name_ko;
    private String name_en;
    private String continent;
    private String iso_code;
    private double latitude;
    private double longitude;
    private String capital;
    private String currency;
    private String language;
    public Country() {
    }

    public Country(int country_id,String name_ko,String name_en,String continent,String iso_code,double latitude,double longitude,String capital, String currency, String language ){
        this.country_id = country_id;
        this.name_ko = name_ko;
        this.name_en = name_en;
        this.continent = continent;
        this.iso_code = iso_code;
        this.latitude = latitude;
        this.longitude = longitude;
        this.capital = capital;
        this.currency = currency;
        this.language = language;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public int getCountry_id() {
        return country_id;
    }

    public String getIso_code() {
        return iso_code;
    }

    public void setIso_code(String iso_code) {
        this.iso_code = iso_code;
    }

    public String getName_en() {
        return name_en;
    }

    public void setName_en(String name_en) {
        this.name_en = name_en;
    }

    public String getName_ko() {
        return name_ko;
    }

    public void setName_ko(String name_ko) {
        this.name_ko = name_ko;
    }

    public void setCountry_id(int country_id) {
        this.country_id = country_id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    public void save(){
        Data.dbcountry.insert(this.country_id,this.name_ko,this.name_en,this.continent,this.iso_code,this.latitude,this.longitude,this.capital,this.currency,this.language);
    }

    public Country getCountry(String name){
        return Data.dbcountry.getCountry(name);
    }
}
