package com.example.duana;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginApp extends AppCompatActivity {
    Button imgLogin, imgWithGoogle, imgRegister;
    EditText edtUserLogin, edtPassLogin;
    private FirebaseAuth auth;
    ProgressDialog dialog;
    LoginButton imgWithFacebook;
    private CallbackManager callbackManager;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    String TAG = "LoginApp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

        setContentView(R.layout.activity_login_app);
        callbackManager = CallbackManager.Factory.create();


        auth = FirebaseAuth.getInstance();
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Log.d(TAG, "onAuthStateChanged:sig_in" + user.getUid());
                } else {
                    Log.d(TAG, "onAuthStateChanged:sig_out ");
                }
            }
        };
        imgLogin = findViewById(R.id.btnLogin);

        imgWithFacebook = findViewById(R.id.btnWithFacebook);
        imgWithFacebook.setReadPermissions("email", "public_profile");
        imgWithFacebook.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG, "Facebook:onSuccess: " + loginResult);
                hanleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Log.d(TAG, "Facebook:onCancel: ");
            }

            @Override
            public void onError(FacebookException error) {
                Log.d(TAG, "onError: " + error);
            }
        });
        Animation animFade = AnimationUtils.loadAnimation(this, R.anim.bouncein);
        Animation animFade1 = AnimationUtils.loadAnimation(this, R.anim.bounceinn);
        Animation animFade2 = AnimationUtils.loadAnimation(this, R.anim.float_in);
        Animation animFade3 = AnimationUtils.loadAnimation(this, R.anim.fromtop);
        imgWithFacebook.startAnimation(animFade);
        imgWithGoogle.setAnimation(animFade1);
        imgLogin.setAnimation(animFade2);
        imgRegister.setAnimation(animFade3);
        imgWithGoogle = findViewById(R.id.btnWithGoogle);

        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
        if (isLoggedIn) {
            startActivity(new Intent(this, MainActivity.class));
        }


        imgRegister = findViewById(R.id.btnRegister);
        imgRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegisterUser.class));
            }
        });
        edtUserLogin = findViewById(R.id.edtEmailLogin);
        edtPassLogin = findViewById(R.id.edtPasswordLogin);
//        imgLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog = new ProgressDialog(LoginApp.this);
//                dialog.setMessage("Xin vui lòng chờ đăng nhập");
//                dialog.setCanceledOnTouchOutside(false);
//
//                String email = edtUserLogin.getText().toString();
//                final String password = edtPassLogin.getText().toString();
//                if (TextUtils.isEmpty(email)){
//                    Toast.makeText(LoginApp.this, "Yêu cầu nhập account email đã đăng ký ...!!", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                else if (TextUtils.isEmpty(password)){
//                    Toast.makeText(LoginApp.this, "Yêu cầu nhập password đã đăng ký...!!!", Toast.LENGTH_SHORT).show();
//                    return;
//                }else {
//                    Toast.makeText(LoginApp.this, "Dang nhap thanh cong...!!!", Toast.LENGTH_SHORT).show();
//                    dialog.cancel();
//                }
//                auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(LoginApp.this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (!task.isSuccessful()){
//                            if (password.length() < 6){
//                                edtPassLogin.setError("Mật khẩu không quá ít 6 ký tự...!!!");
//                            }else {
//                                Toast.makeText(LoginApp.this, "Lỗi đăng nhập,yêu cầu kiểm tra lại email và mật khẩu khi đăng ký...!!!", Toast.LENGTH_SHORT).show();
//                            dialog.cancel();
//                            }
//                        }else {
//                            startActivity(new Intent(getApplication(),MainActivity.class));
//                            finish();
//                        }
//                        edtUserLogin.getText().clear();
//                        edtPassLogin.getText().clear();
//                    }
//                });
//                dialog.show();
//            }
//        });
    }

    private void hanleFacebookAccessToken(AccessToken accessToken) {
        Log.d(TAG, "hanleFacebookAccessToken: " + accessToken);
        AuthCredential authCredential = FacebookAuthProvider.getCredential(accessToken.getToken());
        auth.signInWithCredential(authCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Log.d(TAG, "Success:onComplete: " + task.isSuccessful());
                if (!task.isSuccessful()) {
                    Log.w(TAG, "SignInWithCredential: " + task.getException());
                    Toast.makeText(LoginApp.this, "Authentication failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        auth.addAuthStateListener(mAuthStateListener);

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mAuthStateListener != null) {
            auth.removeAuthStateListener(mAuthStateListener);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);

        super.onActivityResult(requestCode, resultCode, data);
    }
}
