package com.example.cheonyujung.accidentofworld.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.cheonyujung.accidentofworld.Base;
import com.example.cheonyujung.accidentofworld.R;
import com.example.cheonyujung.accidentofworld.data.struct.Comment;
import com.example.cheonyujung.accidentofworld.data.struct.User;

/**
 * Created by cheonyujung on 2016. 6. 19..
 */
public class CommentWriteFragment extends Fragment {

    EditText commentWrite;
    Bundle bundle;
    TextView postTitle;
    TextView postWriter;
    TextView postDate;


    public static CommentWriteFragment getInstence(){ return new CommentWriteFragment();}

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.comment_write, container, false);

        bundle = getArguments();

        Button CommentOk = (Button) view.findViewById(R.id.commentOK);
        Button commentCancel = (Button) view.findViewById(R.id.commentCancel);

        postDate = (TextView) view.findViewById(R.id.postDate_write);
        postTitle = (TextView) view.findViewById(R.id.postTitle_write);
        postWriter = (TextView) view.findViewById(R.id.postWriter_write);

        postDate.setText(bundle.getString("postDate"));
        postTitle.setText(bundle.getString("postTitle"));
        postWriter.setText(bundle.getString("postWriter"));

        commentWrite = (EditText) view.findViewById(R.id.commentWrite);
        CommentOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = String.valueOf(commentWrite.getText());
                long post_id = bundle.getLong("post_id");
                Intent intent = getActivity().getIntent();
                Bundle bundle = intent.getExtras();
                bundle.putString("comment_content",content);
                bundle.putLong("comment_post_id", post_id);
                intent.putExtras(bundle);
                getActivity().setResult(Activity.RESULT_OK,intent);
                getActivity().finish();
            }
        });

        commentCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });
        return view;
    }
}
