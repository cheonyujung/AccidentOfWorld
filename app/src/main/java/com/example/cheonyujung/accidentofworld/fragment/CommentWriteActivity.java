package com.example.cheonyujung.accidentofworld.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.cheonyujung.accidentofworld.Base;
import com.example.cheonyujung.accidentofworld.R;

/**
 * Created by cheonyujung on 2016. 6. 19..
 */
public class CommentWriteActivity extends Base {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        super.setTitle("댓글쓰기");
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Log.d("test",bundle.getString("postTitle"));
        FragmentManager fm = getFragmentManager();

        Fragment fragment = CommentWriteFragment.getInstence();
        fragment.setArguments(bundle);

        fm.beginTransaction()
                .replace(R.id.body, fragment)
                .commit();

    }
}
