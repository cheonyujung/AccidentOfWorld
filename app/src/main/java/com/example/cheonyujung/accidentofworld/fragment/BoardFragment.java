package com.example.cheonyujung.accidentofworld.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.cheonyujung.accidentofworld.PostAdapter;
import com.example.cheonyujung.accidentofworld.R;

/**
 * Created by cheonyujung on 2016. 6. 19..
 */
public class BoardFragment extends Fragment{

    ListView postList;
    PostAdapter postAdapter = new PostAdapter();
    Bundle bundle;

    public static BoardFragment getInstence(){
        return new BoardFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.post_list, container, false);
        postList = (ListView) view.findViewById(R.id.postList);

        bundle = getArguments();
        final int country_id = bundle.getInt("Country_id");
        postAdapter.setPostItems(bundle.getString("CountryName"));
        postList.setAdapter(postAdapter);
        Button button = new Button(getActivity());
        button.setText("+");
        button.setTextSize(20);
        button.setGravity(Gravity.CENTER);
        button.setBackgroundColor(Color.LTGRAY);
        postList.addHeaderView(button);

        if(postAdapter.getCount() == 0){
            TextView textView = (TextView) view.findViewById(R.id.noPost);
            textView.setVisibility(View.VISIBLE);
            textView.setGravity(Gravity.CENTER_HORIZONTAL);
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PostWriteActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("country_id", country_id);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        postList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(getActivity(), PostActivity.class);
                Bundle bundle1 = new Bundle();
                bundle1.putInt("post_id", postAdapter.getPost_id(i-1));
                Log.d("test",postAdapter.getPost_id(i-1) +"");
                bundle1.putString("country_name", bundle.getString("CountryName"));
                intent.putExtras(bundle1);
                startActivity(intent);
            }
        });
        return view;
    }
}
