package com.example.myapplication;

import android.content.ContentValues;
import android.os.AsyncTask;
import android.util.Log;

public class GetServer extends AsyncTask<String, Void, String> {
    String url;

    public GetServer(String url){
        this.url = url;
    }
    @Override
    protected String doInBackground(String... strings) {
        String result;
        com.example.myapplication.GetRequest requestHttpURLConnection = new com.example.myapplication.GetRequest();
        Log.d("TAG111", "1");
        result = requestHttpURLConnection.request(url);
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