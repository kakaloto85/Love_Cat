package com.example.myapplication;

import android.content.ContentValues;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class GetRequest {

    public String request(String _url) {
        HttpURLConnection urlConn = null;

        try {
            URL url = new URL(_url);

            urlConn = (HttpURLConnection) url.openConnection();
            urlConn.connect();
            //urlConn.connect();
            Log.d("TAG111", "check3");
            // [2-1]. urlConn 설정.
            urlConn.setReadTimeout(10000);
            urlConn.setConnectTimeout(15000);

            urlConn.setRequestMethod("GET");
//            urlConn.setDoOutput(true);
//            urlConn.setDoInput(true);
//            urlConn.setRequestProperty("Context-Type", "application/x-www-form-urlencoded");
//            urlConn.setRequestProperty("Accept-Charset", "utf-8");
//            urlConn.setRequestProperty("User-Agent", "Mozilla/5.0");


            if (urlConn.getResponseCode() != HttpURLConnection.HTTP_OK){
                Log.d("CONNECT", "XXX");
                return null;
            }
            BufferedReader reader = null;

            InputStream stream = urlConn.getInputStream();
            reader = new BufferedReader(new InputStreamReader(stream));

            //실제 데이터를 받는곳
            StringBuffer buffer = new StringBuffer();
            //line별 스트링을 받기 위한 temp 변수
            String line = "";
            //아래라인은 실제 reader에서 데이터를 가져오는 부분이다. 즉 node.js서버로부터 데이터를 가져온다.
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            //다 가져오면 String 형변환을 수행한다. 이유는 protected String doInBackground(String… urls) 니까
            return buffer.toString();
            //아래는 예외처리 부분이다.


        } catch (MalformedURLException e) { // for URL.
            e.printStackTrace();
        } catch (IOException e) { // for openConnection().
            e.printStackTrace();
        } finally {
            if (urlConn != null)
                urlConn.disconnect();
        }
        return null;
    }
}
