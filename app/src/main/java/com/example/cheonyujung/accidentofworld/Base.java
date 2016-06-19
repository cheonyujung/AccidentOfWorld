package com.example.cheonyujung.accidentofworld;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.cheonyujung.accidentofworld.fragment.ListActivity;

import java.lang.reflect.Field;

/**
 * Created by cheonyujung on 2016. 5. 19..
 */


public class Base extends AppCompatActivity implements SearchView.OnQueryTextListener{

    public DrawerLayout drawer;
    public Button drawerWorldMap_btn;
    public Button drawerWorldList_btn;
    public Button drawerBoard_btn;
    public Button drawerBookmark_btn;
    public RelativeLayout actionbar;
    public Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        if(Intent.ACTION_SEARCH.equals(intent.getAction())){
            String query = intent.getStringExtra(SearchManager.QUERY);
        }

        toolbar = (Toolbar)findViewById(R.id.main_toolbar);


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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_layout, menu);

        MenuItem searchItem = menu.findItem(R.id.search_item);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);
        searchView.setQueryHint("나라를 입력해주세요...");

        SearchManager searchManager = (SearchManager)getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(new ComponentName(this,MainActivity.class)));
        searchView.setIconifiedByDefault(false);

        return true;
    }

    public void setTitle(String title){
        TextView Title = (TextView)findViewById(R.id.toolbar_title);
        Title.setText(title);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
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
                    startActivity(new Intent(Base.this, MainActivity.class));
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
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}