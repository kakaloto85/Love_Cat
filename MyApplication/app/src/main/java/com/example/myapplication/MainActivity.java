package com.example.myapplication;
//
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.content.pm.PackageInfo;
//import android.content.pm.PackageManager;
//import android.content.pm.Signature;
//import android.os.Bundle;
//import android.util.Base64;
//import android.util.Log;
//import android.widget.Toast;
//
//
//import com.facebook.AccessToken;
//import com.facebook.CallbackManager;
//import com.facebook.FacebookCallback;
//import com.facebook.FacebookException;
//import com.facebook.GraphRequest;
//import com.facebook.GraphResponse;
//import com.facebook.Profile;
//import com.facebook.login.LoginManager;
//import com.facebook.login.LoginResult;
//import com.facebook.login.widget.LoginButton;
//import com.kakao.auth.ApiErrorCode;
//import com.kakao.auth.ISessionCallback;
//import com.kakao.auth.Session;
//import com.kakao.network.ErrorResult;
//import com.kakao.usermgmt.UserManagement;
//import com.kakao.usermgmt.callback.MeV2ResponseCallback;
//import com.kakao.usermgmt.response.MeV2Response;
//import com.kakao.util.OptionalBoolean;
//import com.kakao.util.exception.KakaoException;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.security.MessageDigest;
//import java.util.Arrays;
//
//public class MainActivity extends AppCompatActivity {
//
//    private LoginButton btn_facebook_login;
//    private SessionCallback sessionCallback;
//    private CallbackManager mCallbackManager;
////    private void getAppKeyHash() {
////        try {
////            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
////            for (Signature signature : info.signatures) {
////                MessageDigest md;
////                md = MessageDigest.getInstance("SHA");
////                md.update(signature.toByteArray());
////                String something = new String(Base64.encode(md.digest(), 0));
////                Log.e("Hash key", something);
////            }
////        } catch (Exception e) {
////            // TODO Auto-generated catch block
////            Log.e("name not found", e.toString());
////        }
////    }
//    private class SessionCallback implements ISessionCallback {
//        @Override
//        public void onSessionOpened() {
//            UserManagement.getInstance().me(new MeV2ResponseCallback() {
//                @Override
//                public void onFailure(ErrorResult errorResult) {
//                    int result = errorResult.getErrorCode();
//
//                    if(result == ApiErrorCode.CLIENT_ERROR_CODE) {
//                        Toast.makeText(getApplicationContext(), "네트워크 연결이 불안정합니다. 다시 시도해 주세요.", Toast.LENGTH_SHORT).show();
//                        finish();
//                    } else {
//                        Toast.makeText(getApplicationContext(),"로그인 도중 오류가 발생했습니다: "+errorResult.getErrorMessage(),Toast.LENGTH_SHORT).show();
//                    }
//                }
//
//                @Override
//                public void onSessionClosed(ErrorResult errorResult) {
//                    Toast.makeText(getApplicationContext(),"세션이 닫혔습니다. 다시 시도해 주세요: "+errorResult.getErrorMessage(),Toast.LENGTH_SHORT).show();
//                }
//
//
//
//
//                @Override
//                public void onSuccess(MeV2Response result) {
//                    Intent intent = new Intent(MainActivity.this, signup.class);
//                    intent.putExtra("name", result.getNickname());
//                    intent.putExtra("profile", result.getProfileImagePath());
//                    if(result.getKakaoAccount().hasEmail() == OptionalBoolean.TRUE) {
//
//                        Log.d("??", result.getKakaoAccount().getEmail());
//                        intent.putExtra("email", result.getKakaoAccount().getEmail());
//                    }
//                    else
//                        intent.putExtra("email", "none");
//                    if(result.getKakaoAccount().hasGender() == OptionalBoolean.TRUE)
//                        intent.putExtra("gender", result.getKakaoAccount().getGender().getValue());
//                    else
//                        intent.putExtra("gender", "none");
//                    startActivity(intent);
//                }
//            });
//        }
//
//        @Override
//        public void onSessionOpenFailed(KakaoException e) {
//            Toast.makeText(getApplicationContext(), "로그인 도중 오류가 발생했습니다. 인터넷 연결을 확인해주세요: "+e.toString(), Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        //카톡
//        sessionCallback = new SessionCallback();
//        Session.getCurrentSession().addCallback(sessionCallback);
//        Session.getCurrentSession().checkAndImplicitOpen();
//
//        //페북
//        mCallbackManager = CallbackManager.Factory.create();
//
//        btn_facebook_login = (LoginButton) findViewById(R.id.btn_facebook_login);
//        btn_facebook_login.setReadPermissions(Arrays.asList("public_profile", "email"));
//        btn_facebook_login.registerCallback(mCallbackManager,
//                new FacebookCallback<LoginResult>() {
//
//            // 로그인 성공 시 호출 됩니다. Access Token 발급 성공.
//            @Override
//            public void onSuccess(LoginResult loginResult) {
//                Log.e("Callback :: ", "onSuccess");
//                requestMe(loginResult.getAccessToken());
//            }
//
//            // 로그인 창을 닫을 경우, 호출됩니다.
//            @Override
//            public void onCancel() {
//                Log.e("Callback :: ", "onCancel");
//            }
//
//            // 로그인 실패 시에 호출됩니다.
//            @Override
//            public void onError(FacebookException error) {
//                Log.e("Callback :: ", "onError : " + error.getMessage());
//            }
//
//            // 사용자 정보 요청
//            public void requestMe(AccessToken token) {
//                final String[] email = new String[1];
//                GraphRequest graphRequest = GraphRequest.newMeRequest(token,
//                        new GraphRequest.GraphJSONObjectCallback() {
//                            @Override
//                            public void onCompleted(JSONObject object, GraphResponse response) {
//                                Log.e("result",object.toString());
//                                try {
//                                    if (object.getString("email")=="kakaloto85@nate.com"){
//
//                                    }
//                                    else{
//                                        Intent intent = new Intent(getBaseContext(), signup.class);
//                                        startActivity(intent);
//                                        finish();
//
//                                    }
//                                } catch (JSONException e) {
//
//                                    e.printStackTrace();
//
//                                }
//
//                            }
//                        });
//                //로그인 정보 서버 데이터베이스로보내는 코드
//                //
//                //
//                // email[0]
////                Log.e("result",email[0]);
////                //데이터 베이스에서 이메일 확인하는 코드
////                int isuser=0;
////                if (isuser==0){
////                    Intent intent = new Intent(getBaseContext(), MainActivity.class);
////                    startActivity(intent);
////                    finish();
////
////                }
////                else{
////                    Intent intent = new Intent(getBaseContext(), MainActivity.class);
////                    startActivity(intent);
////                    finish();
////                }
//                Bundle parameters = new Bundle();
//                parameters.putString("fields", "id,name,email,gender,birthday");
//                graphRequest.setParameters(parameters);
//                graphRequest.executeAsync();
//                }});
//
//    }
//
//    @Override
////    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
////        mCallbackManager.onActivityResult(requestCode, resultCode, data);
////        super.onActivityResult(requestCode, resultCode, data);
////
////    }
//
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//
//        // 카카오톡|스토리 간편로그인 실행 결과를 받아서 SDK로 전달
//        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
//            return;
//        }
//
//        super.onActivityResult(requestCode, resultCode, data);
//    }
//
//
//}
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.kakao.auth.AuthType;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;
import com.kakao.usermgmt.callback.MeV2ResponseCallback;
import com.kakao.usermgmt.response.MeV2Response;
import com.kakao.usermgmt.response.model.Profile;
import com.kakao.usermgmt.response.model.UserAccount;
import com.kakao.util.OptionalBoolean;
import com.kakao.util.exception.KakaoException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private Button btn_custom_login;
    private Button btn_custom_login_out;
    private Button btn_out;

    private SessionCallback sessionCallback = new SessionCallback();
    Session session;
    public Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext=this;
        btn_custom_login_out = (Button) findViewById(R.id.btn_custom_login_out);

        session = Session.getCurrentSession();
        session.addCallback(sessionCallback);


        btn_custom_login_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserManagement.getInstance()
                        .requestLogout(new LogoutResponseCallback() {
                            @Override
                            public void onCompleteLogout() {
                                Toast.makeText(MainActivity.this, "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // 세션 콜백 삭제
        Session.getCurrentSession().removeCallback(sessionCallback);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        // 카카오톡|스토리 간편로그인 실행 결과를 받아서 SDK로 전달
        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            return;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    class SessionCallback implements ISessionCallback {

        // 로그인에 성공한 상태
        @Override
        public void onSessionOpened() {
            Log.i("email", "11111111111111111111111111");

            requestMe();
        }

        // 로그인에 실패한 상태
        @Override
        public void onSessionOpenFailed(KakaoException exception) {
            Log.e("SessionCallback :: ", "onSessionOpenFailed : " + exception.getMessage());
        }

        // 사용자 정보 요청
        public void requestMe() {
            UserManagement.getInstance()
                    .me(new MeV2ResponseCallback() {
                        @Override
                        public void onSessionClosed(ErrorResult errorResult) {
                            Log.e("KAKAO_API", "세션이 닫혀 있음: " + errorResult);
                        }

                        @Override
                        public void onFailure(ErrorResult errorResult) {
                            Log.e("KAKAO_API", "사용자 정보 요청 실패: " + errorResult);
                        }

                        @Override
                        public void onSuccess(MeV2Response result) {


                      Intent intent = new Intent(MainActivity.this, signup.class);
                            String callBackValue = null;


                            Log.i("KAKAO_API", "사용자 아이디: " + result.getId());
                            Log.i("email", "2222222222222222222222222222222");

                            UserAccount kakaoAccount = result.getKakaoAccount();
                            Log.i("email", "2222222222222222222222222222222");

                            if (kakaoAccount != null) {
                                Log.i("email", "3333333333333333333333333333333333333l");

                                // 이메일
                                String email = kakaoAccount.getEmail();
                                intent.putExtra("email", email);


                                if (email != null) {
                                    Log.i("KAKAO_API", "email: " + email);
                                    String url = "http://192.249.19.243:0380/api/user/" + email;
                                    GetServer getServer = new GetServer(url);

                                    Log.d("RESULT","1");
                                    try {
                                        Log.d("RESULT","2");
                                        getServer.execute();
                                        callBackValue = getServer.get();
                                        Log.d("callBack", callBackValue);

                                        Log.d("RESULT", callBackValue);
                                    } catch (ExecutionException e) {
                                        e.printStackTrace();
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }

                                } else if (kakaoAccount.emailNeedsAgreement() == OptionalBoolean.TRUE) {
                                    email="";
                                    Log.i("email", "44444444444444444444444444444444444");

                                } else {
                                    email="";
                                    Log.i("email", "5555555555555555555555555555555555555");
                                }

                                // 프로필
                                Profile profile = kakaoAccount.getProfile();

                                if (profile != null) {
                                    Log.d("KAKAO_API", "nickname: " + profile.getNickname());
                                    Log.d("KAKAO_API", "profile image: " + profile.getProfileImageUrl());
                                    Log.d("KAKAO_API", "thumbnail image: " + profile.getThumbnailImageUrl());
                                    intent.putExtra("name", profile.getNickname());
                                    intent.putExtra("profile", profile.getProfileImageUrl());

                                } else if (kakaoAccount.profileNeedsAgreement() == OptionalBoolean.TRUE) {
                                    // 동의 요청 후 프로필 정보 획득 가능

                                } else {
                                    // 프로필 획득 불가
                                }





                                if(callBackValue.equals("[]")){
                                    Log.d("RESULT", "1211111111111111111");

                                    startActivity(intent);
                                }
                                else{
                                    SharedPreferences sf = getSharedPreferences("email",MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sf.edit();
                                    editor.putString("email",email);
                                    editor.commit();

                                    Intent intent2 = new Intent(MainActivity.this, main.class);
                                    Log.d("RESULT", "22222222222222222222222222");

                                    //디비에서 sns, favorite 가져오기
                                    String url = "http://192.249.19.243:0380/api/user/sns/" + email;
                                    GetServer getServer = new GetServer(url);
                                    getServer.execute();
                                    try {
                                        callBackValue = getServer.get();
                                        Log.d("RESULT", callBackValue);
                                        JSONObject jsonObject = new JSONObject(callBackValue);

                                        Log.d("RESULT", "12323123213213213213");

                                        int sns = jsonObject.getInt("SNS");
                                        intent2.putExtra("sns",sns);
                                        int favorite = jsonObject.getInt("favorite");

                                        intent2.putExtra("favorite",favorite);
                                        startActivity(intent2);

                                    } catch (ExecutionException e) {
                                        e.printStackTrace();
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }


                                }


                            }
                        }
                    });
        }
    }
}