package com.example.cheonyujung.accidentofworld.data.query.BoardQuery;

import android.content.ContentValues;
import android.content.Context;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.cheonyujung.accidentofworld.data.DBQuery;
import com.example.cheonyujung.accidentofworld.data.UserAuthority;

/**
 * Created by ohyongtaek on 16. 5. 16..
 */
public class User extends DBQuery {
    public User(Context context) {
        super(context);
    }

    public void insert(String user_id,String email, UserAuthority authority){
        ContentValues values = new ContentValues();
        values.put("user_id",user_id);
        values.put("email",email);
        values.put("authority", String.valueOf(authority));
        writeDB().insert("user", null, values);

    }

    public void update(String old_user_id, String user_id, UserAuthority authority) {
        ContentValues values = new ContentValues();
        values.put("user_id",user_id);
        values.put("authority", String.valueOf(authority));
        writeDB().update("user", values, "id=?", new String[]{old_user_id});
    }
    public void delete(String user_id){
        writeDB().delete("user", "id=?", new String[]{user_id});
    }

    public com.example.cheonyujung.accidentofworld.data.struct.User getUser(String email) {
        String[] whereArgs = new String[] {email};
        SQLiteDatabase db = readDB();
        Cursor cursor =db.rawQuery("select * from user where email = ?;", whereArgs);
        if(cursor.moveToFirst()) {
            com.example.cheonyujung.accidentofworld.data.struct.User user = new com.example.cheonyujung.accidentofworld.data.struct.User();
            user.set_id(cursor.getInt(0));
            user.setId(cursor.getString(1));
            user.setEmail(cursor.getString(2));
            user.setAuthority(UserAuthority.valueOf(cursor.getString(3)));
            return user;
        }
        return null;
    }
}
