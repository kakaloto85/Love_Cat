package com.example.myapplication;

import android.graphics.Bitmap;

public class Friend_data {
    private String email;
    private String name;

    public Friend_data(String email,String name){
        this.email = email;
        this.name=name;
    }
    public String getEmail() {
        return this.email;
    }

    public String getName() {
        return this.name;
    }

}
