package com.example.cheonyujung.accidentofworld.data;

import com.example.cheonyujung.accidentofworld.data.query.BoardQuery.Board;
import com.example.cheonyujung.accidentofworld.data.query.BoardQuery.Comment;
import com.example.cheonyujung.accidentofworld.data.query.BoardQuery.Post;
import com.example.cheonyujung.accidentofworld.data.query.BoardQuery.User;
import com.example.cheonyujung.accidentofworld.data.query.TravelInfoQuery.Accident;
import com.example.cheonyujung.accidentofworld.data.query.TravelInfoQuery.Contact;
import com.example.cheonyujung.accidentofworld.data.query.TravelInfoQuery.Country;
import com.example.cheonyujung.accidentofworld.data.query.TravelInfoQuery.CountryDangerMap;
import com.example.cheonyujung.accidentofworld.data.query.TravelInfoQuery.Danger;
import com.example.cheonyujung.accidentofworld.data.query.TravelInfoQuery.Danger_area;


/**
 * Created by ohyongtaek on 16. 5. 23..
 */
public class Data {
    public static Country dbcountry;
    public static Danger dbdanger;
    public static CountryDangerMap dbCountryDangerMap;
    public static Danger_area dbDanger_area;
    public static Contact dbContact;
    public static Accident dbAccident;
    public static Board dbBoard;
    public static Comment dbComment;
    public static Post dbPost;
    public static User dbUser;
    private static String serviceKey = "FSXfACOsUM%2Bf4uNB%2F8TPNQcFFKHzJh91ArpRmP%2BrjGs4LIHDiirHcHpuxKqYmJmEf8Ls5YIa1VezmY41uetJ%2BQ%3D%3D";
    public static String contactUrl = "http://apis.data.go.kr/1262000/ContactService/getContactInfo?ServiceKey=" + serviceKey;
    public static String countryDangerInfoURL = "http://apis.data.go.kr/1262000/TravelWarningService/getTravelWarningInfo?ServiceKey=" + serviceKey;
    public static String accidentUrl = "http://apis.data.go.kr/1262000/AccidentService/getAccidentInfo?ServiceKey=" + serviceKey;

}
