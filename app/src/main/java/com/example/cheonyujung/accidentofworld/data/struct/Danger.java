package com.example.cheonyujung.accidentofworld.data.struct;

import com.example.cheonyujung.accidentofworld.data.DangerType;

/**
 * Created by ohyongtaek on 16. 5. 15..
 */
public class Danger {
    private Country country;
    private DangerType danger_type;

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public DangerType getDanger_type() {
        return danger_type;
    }

    public void setDanger_type(DangerType danger_type) {
        this.danger_type = danger_type;
    }
}
