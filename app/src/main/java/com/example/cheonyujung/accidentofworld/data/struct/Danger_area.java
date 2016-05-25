package com.example.cheonyujung.accidentofworld.data.struct;

/**
 * Created by ohyongtaek on 16. 5. 16..
 */
public class Danger_area {
    private Country country;
    private String degree;
    private String contents;

    public String getContents() {
        return contents;
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

    }
}
