package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class community_write_activity extends Activity {
    protected String image;
    protected EditText editTitle;
    protected EditText editContent;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.community_write_xml);
        editTitle= findViewById(R.id.comunity_title_edit_text);
        editContent= findViewById(R.id.comunity_content_edit_text);
        Button comunity_add_photo_btn = findViewById(R.id.comunity_add_photo_btn);
        comunity_add_photo_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();


                //본인 갤러리 접근해서 사진 가져옴.\
                //받아온 사진을 singleradapter에 넣어줌
                Intent intent = new Intent(community_write_activity.this, Community_add_photo.class);
                startActivityForResult(intent, Activity.RESULT_FIRST_USER);
            }
        });



        Button comunity_add_complete_btn = findViewById(R.id.comunity_add_complete_btn);
        comunity_add_complete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                String title = editTitle.getText().toString();
                String content = editContent.getText().toString();
                SharedPreferences sf = getSharedPreferences("email",MODE_PRIVATE);
                String email = sf.getString("email","No email");
                String phone = null;
                String url = "http://192.249.19.243:0380/api/user/phone/" + email;
                GetServer getServer = new GetServer(url);
                getServer.execute();
                try {
                    String callBackValues = getServer.get();
                    JSONObject jsonObject = new JSONObject(callBackValues);
                    phone = jsonObject.getString("phone");
                    JSONObject result = new JSONObject();
                    result.put("email", email);
                    result.put("phone", phone);
                    result.put("title", title);
                    result.put("content", content);
                    result.put("image", image);
                    Log.d("TAG1234", image);
                    String url1 = "http://192.249.19.243:0380/api/user/new-community";
                    PostServer postServer = new PostServer(url1, result);
                    postServer.execute();

                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }



                Intent intent = new Intent(community_write_activity.this, main.class);
                startActivity(intent);
                finish();
            }
        });




    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("TAG123", Integer.toString(resultCode));

        if(resultCode == RESULT_OK){
            Log.d("TAG123", "dsfsdsdfdssdsd");

            if(data.hasExtra("bitmap_image")){
                image = data.getStringExtra("bitmap_image");
                Log.d("TAG123", image);



            }
        }
    }

}