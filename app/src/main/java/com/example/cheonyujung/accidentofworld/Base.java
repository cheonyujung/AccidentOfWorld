package com.example.cheonyujung.accidentofworld;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

/**
 * Created by cheonyujung on 2016. 5. 19..
 */
public class Base extends AppCompatActivity {


    public DrawerLayout drawer;
    public Button drawerWorldMap_btn;
    public Button drawerWorldList_btn;
    public Button drawerBoard_btn;
    public Button drawerBookmark_btn;

    public RelativeLayout actionbar = (RelativeLayout) findViewById(R.id.actionbar);

    protected void addViewAtActionBar(View view, ViewGroup.LayoutParams params) {
        view.setLayoutParams(params);
        actionbar.addView(view);
    }

//    public View setEditText(){
//        //EditText editText = new EditText();
//    }

    public void setCustomActionbar() {
        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);

        View myactionbar = LayoutInflater.from(this).inflate(R.layout.action_bar, null);
        actionBar.setCustomView(myactionbar);

        drawer = (DrawerLayout) findViewById(R.id.main_activity);
        ImageButton drawerButton = (ImageButton) myactionbar.findViewById(R.id.open_rightDrawerBtn);
        drawerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(drawer.isDrawerOpen(Gravity.RIGHT)) {
                    drawer.closeDrawer(Gravity.RIGHT);
                }else{
                    drawer.openDrawer(Gravity.RIGHT);
                }
            }
        });

        drawerWorldMap_btn = (Button) findViewById(R.id.worldMapButton);
        drawerWorldList_btn = (Button) findViewById(R.id.CountryListButton);
        drawerBoard_btn = (Button) findViewById(R.id.BoardButton);
        drawerBookmark_btn = (Button)findViewById(R.id.BoardButton);

        BtnListener listener = new BtnListener();

        drawerWorldMap_btn.setOnClickListener(listener);
        drawerWorldList_btn.setOnClickListener(listener);
        drawerBoard_btn.setOnClickListener(listener);
        drawerBookmark_btn.setOnClickListener(listener);

        Toolbar parent = (Toolbar) myactionbar.getParent();
        parent.setContentInsetsAbsolute(0, 0);

        ActionBar.LayoutParams params = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT);
        actionBar.setCustomView(myactionbar, params);

    }

    private class BtnListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            drawer.closeDrawer(Gravity.RIGHT);
            switch(view.getId()) {
                case R.id.worldMapButton:
                    startActivity(new Intent(Base.this, WorldMap.class));
                    break;
                case R.id.CountryListButton:
                    startActivity(new Intent(Base.this, WorldList.class));
                    finish();
                    break;
                case R.id.BoardButton:
                    startActivity(new Intent(Base.this, Board.class));
                    finish();
                    break;
                case R.id.BookmarkButton:
                    startActivity(new Intent(Base.this, BookMark.class));
                    finish();
                    break;
            }
        }
    }
}
