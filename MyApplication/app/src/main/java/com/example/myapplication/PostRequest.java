package com.example.myapplication;

import android.content.ContentValues;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class PostRequest {

    public String request(String _url, JSONObject _params) {

        HttpURLConnection urlConn = null;

        try {
            URL url = new URL(_url);

            urlConn = (HttpURLConnection) url.openConnection();

            urlConn.setReadTimeout(10000);
            urlConn.setConnectTimeout(15000);

            urlConn.setRequestMethod("POST"); // URL 요청에 대한 메소드 설정 : GET/POST.
            urlConn.setRequestProperty("Cache-Control", "no-cache");//캐시 설정
            urlConn.setRequestProperty("Content-Type", "application/json");//application JSON 형식으로 전송
            urlConn.setDoOutput(true);
            urlConn.setDoInput(true);


            OutputStream outputStream = urlConn.getOutputStream();
            //OutputStreamWriter outputStream = new OutputStreamWriter(connection.getOutputStream());
            Log.d("TAG", _params.toString());
            outputStream.write(_params.toString().getBytes("UTF-8"));
            outputStream.flush();

            String result;

            InputStreamReader tmp = new InputStreamReader(urlConn.getInputStream(), "UTF-8");
            BufferedReader reader = new BufferedReader(tmp);
            StringBuilder builder = new StringBuilder();
            String str;
            while ((str = reader.readLine()) != null) {
                builder.append(str + "\n");
            }
            result = builder.toString();

            //다 가져오면 String 형변환을 수행한다. 이유는 protected String doInBackground(String… urls) 니까
            return builder.toString();
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
