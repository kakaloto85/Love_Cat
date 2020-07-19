package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class signup extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


//        Intent intents = getIntent();
//        Log.d("ITPANGPANG",intents.getExtras().getString("email"));

        Log.d("result", "성공");
        Button signup_button= (Button) findViewById(R.id.signup_button);
        signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(signup.this, main.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
