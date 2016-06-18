package com.example.cheonyujung.accidentofworld.data.struct;

import com.example.cheonyujung.accidentofworld.data.Data;

/**
 * Created by ohyongtaek on 16. 5. 16..
 */
public class Accident {
    private Country country;
    private String disater;

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getDisater() {
        return disater;
    }

    public void setDisater(String disater) {
        this.disater = disater;
    }

    public void save() {
        Data.dbAccident.insert(this.country, this.disater);
    }
}
