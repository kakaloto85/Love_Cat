package com.example.myapplication;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.widget.TabHost;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class main extends TabActivity {
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Resources res = getResources();
        TabHost tabHost = getTabHost();
        TabHost.TabSpec spec;
        Intent intent;

// 첫번째 탭(0)
        intent = new Intent(this, tab1.class);
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
        tabHost.setCurrentTab(2);
    }

}
