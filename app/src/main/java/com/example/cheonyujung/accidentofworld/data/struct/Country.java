package com.example.cheonyujung.accidentofworld.data.struct;

/**
 * Created by ohyongtaek on 16. 5. 15..
 */
public class Country {
    private int country_id;
    private String name_ko;
    private String name_en;
    private String continent;
    private String iso_code;

    public Country(int country_id,String name_ko,String name_en,String continent,String iso_code){
        this.country_id = country_id;
        this.name_ko = name_ko;
        this.name_en = name_en;
        this.continent = continent;
        this.iso_code = iso_code;
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
}
