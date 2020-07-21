package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

public class Add_photo extends Activity {
    private GridView mgridView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_photo);

        mgridView = (GridView) findViewById(R.id.grid_view);

        final Image_Adapter ia = new Image_Adapter(this);
        mgridView.setAdapter(ia);
        mgridView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView parent, View v, int position, long id){
                ia.callImageViewer(position);
                finish();
            }
        });
    }




}























