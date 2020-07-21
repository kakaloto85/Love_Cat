package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.json.JSONException;
import org.json.JSONObject;

public class tab1_nosns extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.blank_sns);
        Button sns_create = (Button)findViewById(R.id.sns_create);
        sns_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//sns 만들기 창으로 감
                Intent intent = new Intent(tab1_nosns.this, Activity_make_sns.class);
                startActivity(intent);
                finish();
            }
        });


    }
    }