package com.example.cheonyujung.accidentofworld.data.struct;

import java.util.ArrayList;

/**
 * Created by ohyongtaek on 16. 5. 16..
 */
public class Board {

    private Country country;
    private int _id;
    private ArrayList<Post> posts;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
