package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class GetServer extends AsyncTask<String, Void, String> {
    String url;
    public Context mContext;
    public GetServer(String url){
        this.url = url;
    }
//    public GetServer(Context mContext) {
//        super();
//
//        this.mContext = mContext;
//    }
    @Override
    protected String doInBackground(String... strings) {
        String result;
        Log.d("CHECK", "TT");
        com.example.myapplication.GetRequest requestHttpURLConnection = new com.example.myapplication.GetRequest();
        Log.d("CHECK", url);

        result = requestHttpURLConnection.request(url);
        //Log.d("CHECK", result);
        return result;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        //progress bar를 보여주는 등등의 행위
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);


        Log.d("RESULT", "result");
    }

}