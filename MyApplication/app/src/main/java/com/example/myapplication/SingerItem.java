package com.example.myapplication;

import android.graphics.Bitmap;

public class SingerItem {

    private String date;
    private Bitmap image;

    public SingerItem(String date, Bitmap image) {
        this.date = date;
        this.image = image;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
