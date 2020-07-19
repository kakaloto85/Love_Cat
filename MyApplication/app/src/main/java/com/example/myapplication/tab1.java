package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class tab1 extends AppCompatActivity {

    GridView gridView;
    EditText editText;
    EditText editText2;
    Button button;
    SingerAdapter singerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab1);

        gridView = (GridView) findViewById(R.id.gridView);

        singerAdapter = new SingerAdapter();
        singerAdapter.addItem(new SingerItem("소녀시대", "010-1000-1000", R.drawable.ang));
        singerAdapter.addItem(new SingerItem("아이콘", "010-4000-4000", R.drawable.cat));
        singerAdapter.addItem(new SingerItem("빅뱅", "010-5000-5000", R.drawable.dog));


        gridView.setAdapter(singerAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), "이름 : " + singerAdapter.getItem(i).getName().toString() + " , Tel : " + singerAdapter.getItem(i).getTel().toString(), Toast.LENGTH_LONG).show();

            }
        });
        FloatingActionButton fab = findViewById(R.id.button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                singerAdapter.addItem(new SingerItem("", "", R.drawable.ang));
                singerAdapter.notifyDataSetChanged();
            //추가된 사진 디비에 전달.
            }
        });

//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String name = editText.getText().toString().trim();
//                String tel = editText2.getText().toString().trim();
//                singerAdapter.addItem(new SingerItem(name, tel, R.drawable.ang));
//
//            }
//        });


    }
}