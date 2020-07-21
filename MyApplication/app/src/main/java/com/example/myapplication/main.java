package com.example.myapplication;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TabHost;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class main extends TabActivity {
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        int sns = 0;//=intents.getExtras().getInt("sns");
        Log.d("why","?????????????????????????");
        Resources res = getResources();
        TabHost tabHost = getTabHost();
        TabHost.TabSpec spec;
        Intent intent;
        SharedPreferences sf = getSharedPreferences("email",MODE_PRIVATE);
        String email = sf.getString("email","No email");
        int favorite = sf.getInt("favorite",-1);
        String url = "http://192.249.19.243:0380/api/check-sns/" + email; 
        GetServer getServer = new GetServer(url);
        getServer.execute();
        try {
            String result = getServer.get();
            JSONObject jsonObject = null;
            jsonObject = new JSONObject(result);
            sns = jsonObject.getInt("SNS");

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
// 첫번째 탭(0)
        //sns 있을때
        if(sns==1) {
            //디비에서 정보 받아오기
            intent = new Intent(this, tab1.class);


        }
        else{
            intent = new Intent(this, tab1_nosns.class);

        }
        spec = tabHost.newTabSpec("artists").setIndicator("tab1")
                .setContent(intent);
        tabHost.addTab(spec);
// 두번째 탭(1)
        intent = new Intent(this, tab2.class);
        spec = tabHost.newTabSpec("albums").setIndicator("tab2")
                .setContent(intent);
        tabHost.addTab(spec);

// 세번째 탭(2)
        intent = new Intent(this, tab3.class);
        spec = tabHost.newTabSpec("songs").setIndicator("tab3")
                .setContent(intent);
        tabHost.addTab(spec);

// 세번째 탭 선택
        tabHost.setCurrentTab(1);
    }

}
