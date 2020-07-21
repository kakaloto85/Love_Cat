package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.SearchView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class tab2 extends Activity {
    @RequiresApi(api = Build.VERSION_CODES.O)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab2);
        FloatingActionButton gallery_add_btn = findViewById(R.id.community_add_button);
        SharedPreferences sf = getSharedPreferences("email",MODE_PRIVATE);
        String myemail = sf.getString("email","No email");
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(linearLayoutManager);
        RecyclerAdapter adapter = new RecyclerAdapter(this);
        recyclerView.setAdapter(adapter);


        try {
            // Get coummunity image
            Encoding encoding = new Encoding();
            //ArrayList<Bitmap> bitmapArrayList = encoding.DecodingImage("", 1);

            // Get community title, content
            String url = "http://192.249.19.243:0380/api/community";
            GetServer getServer = new GetServer(url);
            getServer.execute();
            String callBackValues = getServer.get();
            JSONArray jsonArray = new JSONArray(callBackValues);
            Log.d("length",Integer.toString(jsonArray.length()));
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String title = jsonObject.getString("title");
                String content = jsonObject.getString("content");
                String phone = jsonObject.getString("phone");
                String email = jsonObject.getString("email");
                Bitmap image = encoding.DecodingCommunityImage(jsonObject.getString("image"));
                Data data = new Data(email, phone, image, title, content);
                adapter.addItem(data);

            }
            adapter.notifyDataSetChanged();







            // Get SNS information


        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        gallery_add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();


                //본인 갤러리 접근해서 사진 가져옴.\
                //받아온 사진을 singleradapter에 넣어줌
                Intent intent = new Intent(tab2.this, community_write_activity.class);
                startActivity(intent);
                finish();
            }
        });



    }


}
