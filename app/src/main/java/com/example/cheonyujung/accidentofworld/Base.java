package com.example.cheonyujung.accidentofworld;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.cheonyujung.accidentofworld.fragment.ListActivity;
import com.example.cheonyujung.accidentofworld.fragment.WorldMapFragment;

import org.w3c.dom.Text;

/**
 * Created by cheonyujung on 2016. 5. 19..
 */


public class Base extends AppCompatActivity{

    public DrawerLayout drawer;
    public Button drawerWorldMap_btn;
    public Button drawerWorldList_btn;
    public Button drawerBoard_btn;
    public Button drawerBookmark_btn;
    public RelativeLayout actionbar;
    public SearchView searchview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar)findViewById(R.id.main_toolbar);


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        drawer = (DrawerLayout) findViewById(R.id.main_activity);
        drawerWorldMap_btn = (Button) findViewById(R.id.worldMapButton);
        drawerWorldList_btn = (Button) findViewById(R.id.CountryListButton);
        drawerBoard_btn = (Button) findViewById(R.id.BoardButton);
        drawerBookmark_btn = (Button)findViewById(R.id.BoardButton);

        BtnListener listener = new BtnListener();

        drawerWorldMap_btn.setOnClickListener(listener);
        drawerWorldList_btn.setOnClickListener(listener);
        drawerBoard_btn.setOnClickListener(listener);
        drawerBookmark_btn.setOnClickListener(listener);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.drawer_btn:
                drawer.openDrawer(Gravity.RIGHT);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    private class BtnListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            finish();
            drawer.closeDrawer(Gravity.RIGHT);
            switch(view.getId()) {
                case R.id.worldMapButton:
                    startActivity(new Intent(Base.this, WorldMap.class));
                    break;
                case R.id.CountryListButton:
                    startActivity(new Intent(Base.this, ListActivity.class));
                    break;
                case R.id.BoardButton:
                    startActivity(new Intent(Base.this, Board.class));
                    break;
                case R.id.BookmarkButton:
                    startActivity(new Intent(Base.this, BookMark.class));
                    break;
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_layout,menu);
        return true;
    }
    void setTitle(String title){
        TextView Title = (TextView)findViewById(R.id.toolbar_title);
        Title.setText(title);
    }
    void hiddenItem(){
        MenuItem search = (MenuItem)findViewById(R.id.search_btn);
        Log.d(search+"","is null?");
//        search.setVisible(false);
    }
}