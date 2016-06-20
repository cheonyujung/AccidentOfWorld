package com.example.cheonyujung.accidentofworld.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

import com.example.cheonyujung.accidentofworld.Base;
import com.example.cheonyujung.accidentofworld.R;

/**
 * Created by cheonyujung on 2016. 6. 19..
 */
public class PostActivity extends Base {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        super.setTitle("게시글");

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        FragmentManager fm = getFragmentManager();
        Fragment fragment = PostFragment.getInstence();
        fragment.setArguments(bundle);
        fm.beginTransaction()
                .replace(R.id.body, fragment)
                .commit();

    }
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.search_item).setVisible(false);
        return super.onPrepareOptionsMenu(menu);
    }
}
