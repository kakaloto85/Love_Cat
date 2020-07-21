package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

public class Community_add_photo extends Activity {
    private GridView mgridView;
    protected String imgpath;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_photo);
        mgridView = (GridView) findViewById(R.id.grid_view);

        final community_to_gallery ia = new community_to_gallery(this);
        mgridView.setAdapter(ia);
        mgridView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView parent, View v, int position, long id){
                imgpath = ia.callImageViewer(position);
                Log.d("what",imgpath);
                finish();
            }
        });
    }



    public void finish(){
        Intent intent = new Intent(this, community_write_activity.class);
        Bundle bundle = new Bundle();
        Encoding encoding = new Encoding();
        BitmapFactory.Options bfo = new BitmapFactory.Options();

        Bitmap bmp = BitmapFactory.decodeFile(imgpath, bfo);

        String image = encoding.EncodingOneImage(bmp);
        if(!(image.isEmpty())){
            Log.d("image_popup",image);
            bundle.putString("bitmap_image", image);
            intent.putExtras(bundle);
            setResult(RESULT_OK, intent);
        }
        else{
            setResult(RESULT_CANCELED, intent);
        }
        super.finish();
    }


}
