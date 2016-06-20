package com.example.cheonyujung.accidentofworld.data.struct;

import com.example.cheonyujung.accidentofworld.data.Data;

import java.util.ArrayList;

/**
 * Created by ohyongtaek on 16. 5. 16..
 */
public class Board {

    private long _id;

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public static Board getBoard(String countryName) {
        return Data.dbBoard.getBoard(countryName);
    }

    public void save(){
        Data.dbBoard.insert(this._id);
    }

    public void delete() {
        Data.dbBoard.delete(this._id);
    }
}
