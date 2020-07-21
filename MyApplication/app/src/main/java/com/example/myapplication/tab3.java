package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class tab3 extends Activity {
    protected  SearchView searchView;
    protected         Friend_data friend_data;
    protected ArrayList<String> All_email_list=new ArrayList<String>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab3);

        RecyclerView friend_recyler_view =findViewById(R.id.friend_recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        friend_recyler_view.setLayoutManager(linearLayoutManager);
        Friend_RecyclerAdapter adapter = new Friend_RecyclerAdapter(this);
        friend_recyler_view.setAdapter(adapter);
        SharedPreferences sf = getSharedPreferences("email",MODE_PRIVATE);
        String email = sf.getString("email","No email");
        try {
            //여기서 친구 데이터 가져옴
            String url = "http://192.249.19.243:0380/api/friend-list/" + email;
            GetServer getServer = new GetServer(url);
            getServer.execute();
            String callBackValues = getServer.get();
            Log.d("callback2",callBackValues);
            JSONArray jsonArray1 = new JSONArray(callBackValues);
            JSONObject jsonObject1 = jsonArray1.getJSONObject(0);
            JSONArray jsonArray  = new JSONArray(jsonObject1.getString("friends"));

            for (int i =0; i < jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String friend_email = jsonObject.getString("email");
                String friend_name = jsonObject.getString("name");
                Log.d("name","???????????????????????????????");
                friend_data=new Friend_data(friend_email,friend_name);
                adapter.addItem(friend_data);
                adapter.notifyDataSetChanged();

            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }




        //유저 데이터 가져오는 법 ;

        String url  = "http://192.249.19.243:0380/api/all-user";
        GetServer getServer = new GetServer(url);
        getServer.execute();
        String callBackValues = null;
        try {
            callBackValues = getServer.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d("callback",callBackValues);
        JSONArray jsonArray;
        try {

            jsonArray = new JSONArray(callBackValues);
            for (int i =0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String emaildata=jsonObject.getString("email");
                Log.d("email",emaildata);
                All_email_list.add(emaildata);
            }
            } catch (JSONException e) {
            e.printStackTrace();
        }


//
        searchView = findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                String query = searchView.getQuery().toString();
                Log.d("query",query);
                Log.d("query",All_email_list.get(0));
                boolean tmp=false;
                for(int i=0;i<All_email_list.size();i++){
                    if(All_email_list.get(i).equals(query)) {
                        tmp = true;
                        break;
                    }
                    tmp=false;
                }

                    if (tmp){
                        Intent intent = new Intent(tab3.this, Searched_tab1.class);
                        intent.putExtra("user_email",query);
                        startActivity(intent);
                        finish();




                    }
                else
                    Toast.makeText(tab3.this, "존재하지 않는 이메일입니다!", Toast.LENGTH_SHORT).show();

                return true;
            }
//
            @Override
            public boolean onQueryTextChange(String s) {
                // 입력란의 문자열이 바뀔 때 처리

                return false;
            }
        });
    }
}
