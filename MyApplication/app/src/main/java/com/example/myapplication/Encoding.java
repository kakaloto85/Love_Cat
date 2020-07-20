package com.example.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;

import androidx.annotation.RequiresApi;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Base64;

public class Encoding {
    //    private String encoding;
//    private String decoding;
    Base64.Encoder encoder = null;
    Base64.Decoder decoder = null;
    Bitmap origin;

    public Encoding (Bitmap picture){
        this.origin = picture;
    }
    //type 0 : galllery, type 1: community
    public void EncodingImage (String email, int type, String content) throws JSONException {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            encoder = Base64.getEncoder();
        }
        BitmapFactory.Options bo = new BitmapFactory.Options();

        // Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        Bitmap bitmap = this.origin;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        Bitmap image = Bitmap.createScaledBitmap(bitmap, 100, 100, true);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        String encodedBytes = null;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            encodedBytes = encoder.encodeToString(byteArray);
        }
        String url = null;
        PostServer postServer = null;
        JSONObject jsonObject = new JSONObject();
        if (type == 0) { // gallery
            jsonObject.put("image", encodedBytes);
            jsonObject.put("email", email);
            url = "http://192.249.19.243:0380/api/user/new-image";
            postServer = new PostServer(url, jsonObject);
            postServer.execute();
        }
        else if (type == 1) { // community
            jsonObject.put("image", encodedBytes);
            jsonObject.put("content", content);
            jsonObject.put("email", email);
            url = "http://192.249.19.243:0380/api/user/new-community";
            postServer = new PostServer(url, jsonObject);
            postServer.execute();
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Bitmap DecodingImage(String image) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            decoder = Base64.getDecoder();
        }
        byte[] bytes = decoder.decode(image);
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        Bitmap bitmap = BitmapFactory.decodeStream(bais);
        Bitmap bm2 = Bitmap.createScaledBitmap(bitmap, 800, 800, true);
        return bm2;
    }
}
