package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.kakao.auth.AuthType;

import org.json.JSONException;
import org.json.JSONObject;

public class signup extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Intent intents = getIntent();
        final EditText editText1 = (EditText)findViewById(R.id.Sample1);
        final EditText editText2 = (EditText)findViewById(R.id.Sample2);
        final EditText editText3 = (EditText)findViewById(R.id.Sample3);
        final EditText editText4 = (EditText)findViewById(R.id.Sample4);
        ImageButton imageButton_dog = (ImageButton) findViewById(R.id.imageButton1);
        ImageButton imageButton_cat = (ImageButton) findViewById(R.id.imageButton2);
        final int[] favorite = {-1};
        editText4.setText(intents.getExtras().getString("email"));
        editText1.setText(intents.getExtras().getString("name"));

        Log.d("ITPANGPANG",intents.getExtras().getString("email"));
        imageButton_dog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                favorite[0] =1;
            }
        });
        imageButton_cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                favorite[0] =0;

            }
        });

        Log.d("result", "성공");
        Button signup_button= (Button) findViewById(R.id.signup_button);
        signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                여기서 디비에 정보 전송
                String name = editText1.getText().toString(); //name
                String number = editText2.getText().toString(); //
                String city = editText3.getText().toString();
                String email = editText4.getText().toString();

                SharedPreferences sf = getSharedPreferences("email",MODE_PRIVATE);
                SharedPreferences.Editor editor = sf.edit();
                editor.putString("email",email);
                editor.putInt("favorite",favorite[0]);
                editor.commit();
                JSONObject jsonObject = new JSONObject();
                try {



                    jsonObject.put("name", name);
                    jsonObject.put("email", email);
                    jsonObject.put("city", city);
                    jsonObject.put("phone", number);
                    jsonObject.put("favorite", favorite[0]);
                    jsonObject.put("SNS", 0);
                    String url = "http://192.249.19.243:0380/api/new-user";
                    PostServer postServer = new PostServer(url, jsonObject);
                    postServer.execute();
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                Intent intent = new Intent(signup.this, main.class);

                startActivity(intent);
                finish();
            }
        });

    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //Context context = getApplicationContext();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        return true;
    }
}
