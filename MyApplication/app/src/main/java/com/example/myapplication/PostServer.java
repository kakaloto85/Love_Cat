package com.example.myapplication;

import android.content.ContentValues;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.loader.content.AsyncTaskLoader;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.StringJoiner;

public class PostServer extends AsyncTask<String, Void, String> {
    String url;
    JSONObject values;

    public PostServer(String url, JSONObject values){
        this.url = url;
        this.values = values;
    }
    @Override
    protected String doInBackground(String... strings) {
        String result;
        com.example.myapplication.PostRequest requestHttpURLConnection = new com.example.myapplication.PostRequest();
        Log.d("TAG111", "1");
        result = requestHttpURLConnection.request(url, values);
        Log.d("TAG111", "123");
        return result;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        //progress bar를 보여주는 등등의 행위
    }

    @Override
    protected void onPostExecute(String result) {
        Log.d("RESULT", "result");
        System.out.println(result);
        // 통신이 완료되면 호출됩니다.
        // 결과에 따른 UI 수정 등은 여기서 합니다.

    }

}