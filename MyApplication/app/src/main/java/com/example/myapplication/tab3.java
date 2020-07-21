package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.Toast;

public class tab3 extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab3);
//        RecyclerView recyclerView =findViewById(R.id.recyclerView);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//
//        recyclerView.setLayoutManager(linearLayoutManager);
//        RecyclerAdapter adapter = new RecyclerAdapter(this);
//        recyclerView.setAdapter(adapter);
////        for (int i = 0; i< 10; i++) {
////            // 각 List의 값들을 data 객체에 set 해줍니다.
////            Data data = new Data();
////            data.setName("은진");
////            data.setNumber("11");
////
////            // 각 값이 들어간 data를 adapter에 추가합니다.
////            adapter.addItem(data);
////        }
//
//        SearchView searchView;
//        searchView = findViewById(R.id.search_view);
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String s) {
//                Toast.makeText(tab3.this, "검색1", Toast.LENGTH_SHORT).show();
//                return true;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String s) {
//                // 입력란의 문자열이 바뀔 때 처리
//                Toast.makeText(tab3.this, "검색2", Toast.LENGTH_SHORT).show();
//
//                return false;
//            }
//        });
//    }
    }
}