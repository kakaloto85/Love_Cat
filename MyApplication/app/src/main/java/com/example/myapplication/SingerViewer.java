package com.example.myapplication;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class SingerViewer extends LinearLayout {

    TextView textView;
    TextView textView2;
    ImageView imageView;
    public SingerViewer(Context context) {
        super(context);

        init(context);
    }

    public SingerViewer(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    public void init(Context context){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.singeritem,this,true);

        imageView = (ImageView) findViewById(R.id.imageView);
    }

    public void setItem(SingerItem singerItem){
        imageView.setImageBitmap(singerItem.getImage());
    }
}
