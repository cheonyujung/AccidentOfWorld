package com.example.cheonyujung.accidentofworld.data.struct;

/**
 * Created by ohyongtaek on 16. 5. 16..
 */
public class Accident {
    private Country country;
    private String natural_disater;
    private String man_disater;

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getMan_disater() {
        return man_disater;
    }

    public void setMan_disater(String man_disater) {
        this.man_disater = man_disater;
    }

    public String getNatural_disater() {
        return natural_disater;
    }

    public void setNatural_disater(String natural_disater) {
        this.natural_disater = natural_disater;
    }
}
