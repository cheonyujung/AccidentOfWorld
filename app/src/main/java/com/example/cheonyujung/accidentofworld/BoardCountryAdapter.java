package com.example.cheonyujung.accidentofworld;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by cheonyujung on 2016. 6. 19..
 */
public class BoardCountryAdapter extends BaseAdapter{

    private ArrayList<String> list = new ArrayList<>();
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public String getItem(int position) {
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

    public void setList(ArrayList<String> filteredcountry){
        list.clear();
        list = filteredcountry;
    }

}
