//package com.example.myapplication;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//
//import com.google.android.material.floatingactionbutton.FloatingActionButton;
//import com.google.android.material.snackbar.Snackbar;
//
//public class Community extends Activity {
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_tab2);
//        FloatingActionButton fab = findViewById(R.id.gallery_add_button);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//
//
//                //본인 갤러리 접근해서 사진 가져옴.\
//                //받아온 사진을 singleradapter에 넣어줌
//                Intent intent = new Intent(.this, Add_photo.class);
//
//
//
//
//
//
//
//                추가된 사진 디비에 전달.
//            }
//        });
//
//    }
//}