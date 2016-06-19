package com.example.cheonyujung.accidentofworld;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by cheonyujung on 2016. 6. 19..
 */
public class Intro extends Activity{

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro);
        endReady();
    }

    void endReady(){
        Intent intent = new Intent(Intro.this, MainActivity.class);
        startActivity(intent);
    }
}
