package com.example.cheonyujung.accidentofworld.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.cheonyujung.accidentofworld.Base;
import com.example.cheonyujung.accidentofworld.R;
import com.example.cheonyujung.accidentofworld.data.struct.Post;
import com.example.cheonyujung.accidentofworld.data.struct.User;

/**
 * Created by cheonyujung on 2016. 6. 19..
 */
public class PostWriteFragment extends Fragment {

    public static PostWriteFragment getInstence() {
        return new PostWriteFragment();
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.post_write, container, false);

        Bundle bundle = getArguments();
        final int country_id = bundle.getInt("country_id");

        final EditText postContents = (EditText) view.findViewById(R.id.postContents_edit);
        final EditText postTitle = (EditText) view.findViewById(R.id.postTitle_edit);
        Button cancleBtn = (Button) view.findViewById(R.id.postCancel);
        Button okBtn = (Button) view.findViewById(R.id.postOK);

        cancleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });

        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getActivity().getIntent();
                Bundle bundle1 = intent.getExtras();
                bundle1.putInt("post_board_id",country_id);
                bundle1.putString("post_content", postContents.getText().toString());
                bundle1.putString("post_title", postTitle.getText().toString());
                bundle1.putString("post_user_id", Base.user.getId());
                intent.putExtras(bundle1);
                getActivity().setResult(Activity.RESULT_OK,intent);
                getActivity().finish();

            }
        });
        return view;
    }
}