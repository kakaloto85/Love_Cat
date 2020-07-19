package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;


import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.kakao.auth.ApiErrorCode;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeV2ResponseCallback;
import com.kakao.usermgmt.response.MeV2Response;
import com.kakao.util.OptionalBoolean;
import com.kakao.util.exception.KakaoException;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private LoginButton btn_facebook_login;
    private SessionCallback sessionCallback;
    private CallbackManager mCallbackManager;
//    private void getAppKeyHash() {
//        try {
//            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
//            for (Signature signature : info.signatures) {
//                MessageDigest md;
//                md = MessageDigest.getInstance("SHA");
//                md.update(signature.toByteArray());
//                String something = new String(Base64.encode(md.digest(), 0));
//                Log.e("Hash key", something);
//            }
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            Log.e("name not found", e.toString());
//        }
//    }
    private class SessionCallback implements ISessionCallback {
        @Override
        public void onSessionOpened() {
            UserManagement.getInstance().me(new MeV2ResponseCallback() {
                @Override
                public void onFailure(ErrorResult errorResult) {
                    int result = errorResult.getErrorCode();

                    if(result == ApiErrorCode.CLIENT_ERROR_CODE) {
                        Toast.makeText(getApplicationContext(), "네트워크 연결이 불안정합니다. 다시 시도해 주세요.", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(),"로그인 도중 오류가 발생했습니다: "+errorResult.getErrorMessage(),Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onSessionClosed(ErrorResult errorResult) {
                    Toast.makeText(getApplicationContext(),"세션이 닫혔습니다. 다시 시도해 주세요: "+errorResult.getErrorMessage(),Toast.LENGTH_SHORT).show();
                }




                @Override
                public void onSuccess(MeV2Response result) {
                    Intent intent = new Intent(MainActivity.this, signup.class);
                    intent.putExtra("name", result.getNickname());
                    intent.putExtra("profile", result.getProfileImagePath());
                    if(result.getKakaoAccount().hasEmail() == OptionalBoolean.TRUE) {
                        Log.d("??", result.getKakaoAccount().getEmail());
                        intent.putExtra("email", result.getKakaoAccount().getEmail());
                    }
                    else
                        intent.putExtra("email", "none");
                    if(result.getKakaoAccount().hasGender() == OptionalBoolean.TRUE)
                        intent.putExtra("gender", result.getKakaoAccount().getGender().getValue());
                    else
                        intent.putExtra("gender", "none");
                    startActivity(intent);
                }
            });
        }

        @Override
        public void onSessionOpenFailed(KakaoException e) {
            Toast.makeText(getApplicationContext(), "로그인 도중 오류가 발생했습니다. 인터넷 연결을 확인해주세요: "+e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sessionCallback = new SessionCallback();
        Session.getCurrentSession().addCallback(sessionCallback);
        Session.getCurrentSession().checkAndImplicitOpen();

        mCallbackManager = CallbackManager.Factory.create();

        btn_facebook_login = (LoginButton) findViewById(R.id.btn_facebook_login);
        btn_facebook_login.setReadPermissions(Arrays.asList("public_profile", "email"));
        btn_facebook_login.registerCallback(mCallbackManager,
                new FacebookCallback<LoginResult>() {

            // 로그인 성공 시 호출 됩니다. Access Token 발급 성공.
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.e("Callback :: ", "onSuccess");
                requestMe(loginResult.getAccessToken());
            }

            // 로그인 창을 닫을 경우, 호출됩니다.
            @Override
            public void onCancel() {
                Log.e("Callback :: ", "onCancel");
            }

            // 로그인 실패 시에 호출됩니다.
            @Override
            public void onError(FacebookException error) {
                Log.e("Callback :: ", "onError : " + error.getMessage());
            }

            // 사용자 정보 요청
            public void requestMe(AccessToken token) {
                final String[] email = new String[1];
                GraphRequest graphRequest = GraphRequest.newMeRequest(token,
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                Log.e("result",object.toString());
                                try {
                                    if (object.getString("email")=="kakaloto85@nate.com"){

                                    }
                                    else{
                                        Intent intent = new Intent(getBaseContext(), signup.class);
                                        startActivity(intent);
                                        finish();

                                    }
                                } catch (JSONException e) {

                                    e.printStackTrace();

                                }

                            }
                        });
                //로그인 정보 서버 데이터베이스로보내는 코드
                //
                //
                // email[0]
//                Log.e("result",email[0]);
//                //데이터 베이스에서 이메일 확인하는 코드
//                int isuser=0;
//                if (isuser==0){
//                    Intent intent = new Intent(getBaseContext(), MainActivity.class);
//                    startActivity(intent);
//                    finish();
//
//                }
//                else{
//                    Intent intent = new Intent(getBaseContext(), MainActivity.class);
//                    startActivity(intent);
//                    finish();
//                }
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,gender,birthday");
                graphRequest.setParameters(parameters);
                graphRequest.executeAsync();
                }});

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);

    }



}
