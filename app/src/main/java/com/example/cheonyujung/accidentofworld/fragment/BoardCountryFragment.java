package com.example.cheonyujung.accidentofworld.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.cheonyujung.accidentofworld.BoardCountryAdapter;
import com.example.cheonyujung.accidentofworld.R;
import com.example.cheonyujung.accidentofworld.data.DBHelper;

/**
 * Created by cheonyujung on 2016. 6. 19..
 */
public class BoardCountryFragment extends Fragment {

    BoardCountryAdapter boardCountryAdapter = new BoardCountryAdapter();
    ListView boardCountryList;

    public static BoardCountryFragment getInstence(){
        return new BoardCountryFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.country_list, container, false);

        boardCountryList = (ListView) view.findViewById(R.id.country_list);

        boardCountryList.setAdapter(boardCountryAdapter);

        getCountryList();

        boardCountryList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), BoardActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("CountryName", boardCountryAdapter.getItem(i));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        return view;
    }

    public void getCountryList() {
        boardCountryAdapter.removeAll();
        DBHelper dbHelper = new DBHelper(getActivity());
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select name_ko from country order by name_ko;", null);
        while (cursor.moveToNext()) {
            boardCountryAdapter.addCountry(cursor.getString(0));
        }
        boardCountryAdapter.notifyDataSetChanged();
        cursor.close();
    }
}
