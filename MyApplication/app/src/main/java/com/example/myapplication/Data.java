package com.example.myapplication;

import android.graphics.Bitmap;

public class Data {

    private String email;
    private String phone;
    private Bitmap image;
    private String title;
    private String content;

    public Data(String email, String phone, Bitmap image, String title, String content){
        this.email = email;
        this.phone = phone;
        this.image = image;
        this.title = title;
        this.content = content;

    }
    public String getEmail() {
        return this.email;
    }

    public String getPhone() {
        return this.phone;
    }

    public String getTitle() {
        return this.title;
    }

    public String getContent() {
        return this.content;
    }

    public Bitmap getImage() {
        return this.image;
    }

}
