package com.example.cheonyujung.accidentofworld;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ohyongtaek on 16. 5. 21..
 */
public class CountryListAdapter extends BaseAdapter {
    private ArrayList<String> list = new ArrayList<>();
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public String getItem(int position) {
        Log.d("testest", list.get(position));
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Context context = parent.getContext();
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_item, parent, false);
        }
        TextView name = (TextView) convertView.findViewById(R.id.list_item);

        String listViewItem = list.get(position);
        name.setText(listViewItem);

        return convertView;
    }
    public void removeAll() {
        list.clear();
    }

    public void addCountry(String name){
        list.add(name);
    }

}
