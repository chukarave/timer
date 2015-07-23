package com.example.noa.timer;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.TextUtils;

import android.widget.TextView;

import java.text.DateFormat;
import java.util.ArrayList;


public class ListActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Intent intent = getIntent();
        ArrayList<Pair> list = intent.getParcelableArrayListExtra("LIST"); // get the ArrayList

        TextView textView = new TextView(this);
        setContentView(textView);

        //Iterate over the list
        // calculate the diff between the start time and the previous start time
        for (int i = 0; i < list.size(); i++) {
            long diff = 0;
            if (i > 0)  {
                diff = list.get(i).start - list.get(i-1).start;
            }

            //System.out.println(String.format("%s: %s", list.get(i), diff/1000));
            textView.append(String.format("%s: %s", list.get(i), diff / 1000));
        }


/*
        String joined = TextUtils.join(", ", list);
        System.out.println(joined);
        TextView textView = new TextView(this);
        textView.setText(joined);
        setContentView(textView);
*/

        }


    }
