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
import android.content.Intent;
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

public class MainActivity extends AppCompatActivity {

    private Button btn_custom_login;
    private Button btn_custom_login_out;
    private SessionCallback sessionCallback = new SessionCallback();
    Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_custom_login = (Button) findViewById(R.id.btn_custom_login);
        btn_custom_login_out = (Button) findViewById(R.id.btn_custom_login_out);

        session = Session.getCurrentSession();
        session.addCallback(sessionCallback);

        btn_custom_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                session.open(AuthType.KAKAO_LOGIN_ALL, MainActivity.this);
            }
        });

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


//                      Intent intent = new Intent(MainActivity.this, signup.class);
////                    intent.putExtra("name", result.getNickname());
////                    intent.putExtra("profile", result.getProfileImagePath());
////                    if(result.getKakaoAccount().hasEmail() == OptionalBoolean.TRUE) {
////
////                        Log.d("??", result.getKakaoAccount().getEmail());
////                        intent.putExtra("email", result.getKakaoAccount().getEmail());
////                    }
////                    else
////                        intent.putExtra("email", "none");
////                    if(result.getKakaoAccount().hasGender() == OptionalBoolean.TRUE)
////                        intent.putExtra("gender", result.getKakaoAccount().getGender().getValue());
////                    else
////                        intent.putExtra("gender", "none");
////                    startActivity(intent);
//


                            Log.i("KAKAO_API", "사용자 아이디: " + result.getId());
                            Log.i("email", "2222222222222222222222222222222");

                            UserAccount kakaoAccount = result.getKakaoAccount();
                            Log.i("email", "2222222222222222222222222222222");

                            if (kakaoAccount != null) {
                                Log.i("email", "3333333333333333333333333333333333333l");

                                // 이메일
                                String email = kakaoAccount.getEmail();

                                if (email != null) {
                                    Log.i("KAKAO_API", "email: " + email);

                                } else if (kakaoAccount.emailNeedsAgreement() == OptionalBoolean.TRUE) {
                                    Log.i("email", "44444444444444444444444444444444444");

                                } else {
                                    Log.i("email", "5555555555555555555555555555555555555");
                                }

                                // 프로필
                                Profile profile = kakaoAccount.getProfile();

                                if (profile != null) {
                                    Log.d("KAKAO_API", "nickname: " + profile.getNickname());
                                    Log.d("KAKAO_API", "profile image: " + profile.getProfileImageUrl());
                                    Log.d("KAKAO_API", "thumbnail image: " + profile.getThumbnailImageUrl());

                                } else if (kakaoAccount.profileNeedsAgreement() == OptionalBoolean.TRUE) {
                                    // 동의 요청 후 프로필 정보 획득 가능

                                } else {
                                    // 프로필 획득 불가
                                }
                            }
                        }
                    });
        }
    }
}