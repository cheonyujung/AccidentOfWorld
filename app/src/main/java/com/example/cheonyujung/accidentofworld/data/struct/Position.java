package com.example.cheonyujung.accidentofworld.data.struct;

/**
 * Created by ohyongtaek on 16. 5. 16..
 */
public class Position {
    private Country country;
    private double position_x;
    private double position_y;

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public double getPosition_x() {
        return position_x;
    }

    public void setPosition_x(double position_x) {
        this.position_x = position_x;
    }

    public double getPosition_y() {
        return position_y;
    }

    public void setPosition_y(double position_y) {
        this.position_y = position_y;
    }
}
