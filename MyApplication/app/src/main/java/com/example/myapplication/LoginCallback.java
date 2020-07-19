//package com.example.myapplication;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.util.Log;
//
//import com.facebook.AccessToken;
//import com.facebook.FacebookCallback;
//import com.facebook.FacebookException;
//import com.facebook.GraphRequest;
//import com.facebook.GraphResponse;
//import com.facebook.login.LoginResult;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//public class LoginCallback implements FacebookCallback<LoginResult> {
//
//    // 로그인 성공 시 호출 됩니다. Access Token 발급 성공.
//    @Override
//    public void onSuccess(LoginResult loginResult) {
//        Log.e("Callback :: ", "onSuccess");
//        requestMe(loginResult.getAccessToken());
//    }
//
//    // 로그인 창을 닫을 경우, 호출됩니다.
//    @Override
//    public void onCancel() {
//        Log.e("Callback :: ", "onCancel");
//    }
//
//    // 로그인 실패 시에 호출됩니다.
//    @Override
//    public void onError(FacebookException error) {
//        Log.e("Callback :: ", "onError : " + error.getMessage());
//    }
//
//    // 사용자 정보 요청
//    public void requestMe(AccessToken token) {
//        final String[] email = new String[1];
//        GraphRequest graphRequest = GraphRequest.newMeRequest(token,
//                new GraphRequest.GraphJSONObjectCallback() {
//                    @Override
//                    public void onCompleted(JSONObject object, GraphResponse response) {
//                        Log.e("result",object.toString());
//                        try {
//                            email[0] =object.getString("email");
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                });
//        //로그인 정보 서버 데이터베이스로보내는 코드
//        //
//        //
//        // email[0]
//        Log.e("result",email[0]);
//
//        //데이터 베이스에서 이메일 확인하는 코드
//        int isuser=0;
//        if (isuser==0){
//            Intent intent = new Intent(getBaseContext(), MainActivity.class);
//            startActivity(intent);
//            finish();
//
//        }
//        else{
//            Intent intent = new Intent(getBaseContext(), MainActivity.class);
//            startActivity(intent);
//            finish();
//        }
//
//
//
//
//
//        Bundle parameters = new Bundle();
//        parameters.putString("fields", "id,name,email,gender,birthday");
//        graphRequest.setParameters(parameters);
//        graphRequest.executeAsync();
//    }
//}
