package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import org.json.JSONException;
import org.json.JSONObject;

public class Activity_make_sns extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.make_sns);
        Button sns_create = (Button)findViewById(R.id.sns_signup_btn);


        ImageButton Button_dog = findViewById(R.id.dog_sns);

        ImageButton Button_cat = findViewById(R.id.cat_sns);
        final int[] catordog = {0};

        Button_dog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                catordog[0] =1;
            }
        });
        Button_cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                catordog[0]=0;
            }
        });









        sns_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//
                Intent intent = new Intent(Activity_make_sns.this, main.class);



                EditText name = findViewById(R.id.petname_insert);
                EditText profile = findViewById(R.id.profile_insert);
                JSONObject jsonObject = new JSONObject();
                try {
                    SharedPreferences sf = getSharedPreferences("email",MODE_PRIVATE);
                    String email = sf.getString("email","No email");

                    jsonObject.put("email", email);
                    jsonObject.put("pet_name",name.getText().toString());
                    jsonObject.put("profile",profile.getText().toString());
                    jsonObject.put("pet_type",catordog[0]);
                    jsonObject.put("SNS",1);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String url = "http://192.249.19.243:0380/api/new-sns";
                PostServer postServer = new PostServer(url, jsonObject);
                postServer.execute();




                startActivity(intent);
                finish();
            }
        });


    }


}
