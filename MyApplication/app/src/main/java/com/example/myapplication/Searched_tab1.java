package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class Searched_tab1 extends AppCompatActivity {

    GridView gridView;
    EditText editText;
    EditText editText2;
    Button button;
    SingerAdapter singerAdapter;
    String email;
    String pet_name;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab1_searched);
        Intent intent =getIntent();
        gridView = (GridView) findViewById(R.id.Searched_gridView);
        TextView name = findViewById(R.id.petname);
        TextView profile = findViewById(R.id.profile_content);
        ImageView profile_image = findViewById(R.id.profile_image);
        email=intent.getExtras().getString("user_email");
        TextView emailtext = findViewById(R.id.emailtext);
        emailtext.setText(email);
        //디비에서 정보 가져오기\
        try {
            // Get SNS information
            String url = "http://192.249.19.243:0380/api/get-sns/" + email;
            Log.d("email",email);
            GetServer getServer = new GetServer(url);
            getServer.execute();

            String callBackValues = getServer.get();
            Log.d("callback",callBackValues);
            // Get images from gallery
            Encoding encoding = new Encoding();
            ArrayList<Bitmap> bitmapArrayList = encoding.DecodingImage(email, 0);

            JSONObject jsonObject = new JSONObject(callBackValues);
            Log.d("ddssaf",callBackValues);
             pet_name = jsonObject.getString("pet_name");
            int pet_type = jsonObject.getInt("pet_type");
            String profile_content = jsonObject.getString("profile");
            name.setText(pet_name);
            profile.setText(profile_content);
            if (pet_type==1) {
                profile_image.setImageDrawable(getResources().getDrawable(R.drawable.cats));
            }
            else {
                profile_image.setImageDrawable(getResources().getDrawable(R.drawable.dogs));
            }
            singerAdapter = new SingerAdapter();

            //디비에서 갤러리 사진을 다 가져옴
            //디코딩함
            //비트맵 이미지를 사용

            int gallery_length = bitmapArrayList.size(); //갤러리 길이
            Log.d("bitmap",Integer.toString(gallery_length));
            for(int i=0; i<gallery_length;i++){
                //각각의 비트맵

                singerAdapter.addItem(new SingerItem("dd",bitmapArrayList.get(i)));

            }

            singerAdapter.notifyDataSetChanged();


            gridView.setAdapter(singerAdapter);


        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //디비에서 고양이/개 선호도 가져옴 \





        Button friend_add_btn = findViewById(R.id.friend_add_button);
        friend_add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                //여기서 친구요청 보냄


                try {
                    String url = "http://192.249.19.243:0380/api/new-friend";
                    SharedPreferences sf = getSharedPreferences("email",MODE_PRIVATE);
                    String myemail = sf.getString("email","No email");
                    JSONObject tmp = new JSONObject();
                    tmp.put("email", email);
                    tmp.put("name", pet_name);
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("email", myemail);
                    jsonObject.put("friends", tmp);
                    PostServer postServer = new PostServer(url, jsonObject);
                    postServer.execute();
                    Intent go = new Intent(Searched_tab1.this, main.class);
                    startActivity(go);

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });



    }



}
