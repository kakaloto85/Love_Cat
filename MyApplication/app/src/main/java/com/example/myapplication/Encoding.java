package com.example.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.concurrent.ExecutionException;

public class Encoding {
    //    private String encoding;
//    private String decoding;
    Base64.Encoder encoder = null;
    Base64.Decoder decoder = null;
    //Bitmap origin;

    //type 0 : galllery, type 1: community
    public void EncodingImage (Bitmap origin, String email, int type, String content) throws JSONException {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            encoder = Base64.getEncoder();
        }
        BitmapFactory.Options bo = new BitmapFactory.Options();

        // Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        Bitmap bitmap = origin;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        Bitmap image = Bitmap.createScaledBitmap(bitmap, 300, 300, true);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG, 50, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        String encodedBytes = null;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            encodedBytes = encoder.encodeToString(byteArray);
        }
        String url = null;
        PostServer postServer = null;
        JSONObject jsonObject = new JSONObject();
        JSONObject tmp = new JSONObject();
        tmp.put("image", encodedBytes);
        if (type == 0) { // gallery
            jsonObject.put("gallery", tmp);
            jsonObject.put("email", email);
            Log.d("TAG", encodedBytes);
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

    public String EncodingOneImage(Bitmap origin) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            encoder = Base64.getEncoder();
        }
        BitmapFactory.Options bo = new BitmapFactory.Options();

        // Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        Bitmap bitmap = origin;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        Bitmap image = Bitmap.createScaledBitmap(bitmap, 300, 300, true);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG, 50, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        String encodedBytes = null;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            encodedBytes = encoder.encodeToString(byteArray);
        }
        return  encodedBytes;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public ArrayList<Bitmap> DecodingImage(String email, int type) throws ExecutionException, InterruptedException, JSONException {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            decoder = Base64.getDecoder();
        }
        ArrayList<Bitmap> bitmapArrayList = new ArrayList<Bitmap>();
        // 0: gallery, 1: community
        if (type == 0){
            String url = "http://192.249.19.243:0380/api/user/gallery/" + email;
            GetServer getServer = new GetServer(url);
            getServer.execute();
            String callBackValues = getServer.get();
            Log.d("call",callBackValues);
            JSONObject tmp = new JSONObject(callBackValues);
            Log.d("call", tmp.getString("gallery"));
            //if (! tmp.getString("gallery").equals("[]")) {
                JSONArray jsonArray = new JSONArray(tmp.getString("gallery"));
                //Log.d("call", Integer.toString(jsonArray.length()));
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Log.d("PHOTO", jsonObject.toString());
                    String image = jsonObject.getString("image");
                    byte[] bytes = decoder.decode(image);
                    ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
                    Bitmap bitmap = BitmapFactory.decodeStream(bais);
                    Bitmap bm2 = Bitmap.createScaledBitmap(bitmap, 800, 800, true);
                    bitmapArrayList.add(bm2);
                }
                return bitmapArrayList;
            //}
        }

        return bitmapArrayList;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Bitmap DecodingCommunityImage (String image) {
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