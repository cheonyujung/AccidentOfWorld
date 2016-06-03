package com.example.cheonyujung.accidentofworld.data.struct;

import com.example.cheonyujung.accidentofworld.data.UserAuthority;

/**
 * Created by ohyongtaek on 16. 5. 16..
 */
public class User {

    private int _id;
    private String id;
    private UserAuthority authority;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public UserAuthority getAuthority() {
        return authority;
    }

    public void setAuthority(UserAuthority authority) {
        this.authority = authority;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
