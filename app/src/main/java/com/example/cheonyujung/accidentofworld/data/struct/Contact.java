package com.example.cheonyujung.accidentofworld.data.struct;

import com.example.cheonyujung.accidentofworld.data.Data;

/**
 * Created by ohyongtaek on 16. 5. 16..
 */
public class Contact {
    private Country country;
    private String tel;

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void save() {
        Data.dbContact.insert(this.country,tel);
    }

    public static Contact getContact(String countryName) {
        return Data.dbContact.getContact(countryName);
    }

}
