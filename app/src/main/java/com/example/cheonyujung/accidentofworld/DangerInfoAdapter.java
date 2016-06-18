package com.example.cheonyujung.accidentofworld;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.cheonyujung.accidentofworld.data.struct.Danger_area;

import java.util.ArrayList;

/**
 * Created by ohyongtaek on 16. 6. 19..
 */
public class DangerInfoAdapter extends BaseAdapter {

    String countryName;
    ArrayList<Danger_area> danger_areaArrayList;
    public DangerInfoAdapter(String countryName) {
        this.countryName = countryName;
        danger_areaArrayList = new ArrayList<>();
    }
    @Override
    public int getCount() {
        return danger_areaArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return danger_areaArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return danger_areaArrayList.get(position).get_id();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Context context = parent.getContext();
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item_danger_info, parent, false);
        }
        TextView dangerType = (TextView) convertView.findViewById(R.id.dangerType);
        TextView dangerContents = (TextView) convertView.findViewById(R.id.dangerContents);
        dangerType.setText(danger_areaArrayList.get(position).getDegree());
        dangerContents.setText(danger_areaArrayList.get(position).getContents());
        return convertView;
    }

    public void setDataSet(ArrayList<Danger_area> dataSet) {
        this.danger_areaArrayList = dataSet;
    }
}
