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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginApp extends AppCompatActivity {
    ImageView imgLogin, imgRegister;
    SignInButton imgWithGoogle;
   LinearLayout linearLayout1,linearLayout2;
    EditText edtUserLogin, edtPassLogin;
    private FirebaseAuth auth;
    ProgressDialog dialog;Animation uptodown,downtoup;
    LoginButton imgWithFacebook;
    private CallbackManager callbackManager;
    private static final int RC_SIGN_IN = 9001;
    private GoogleApiClient googleApiClient;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    String TAG = "LoginApp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

        setContentView(R.layout.activity_login_app);
        callbackManager = CallbackManager.Factory.create();
        linearLayout1=findViewById(R.id.linearLayout1);
        linearLayout2=findViewById(R.id.linearLayout2);
        uptodown = AnimationUtils.loadAnimation(this,R.anim.utogo);
        downtoup = AnimationUtils.loadAnimation(this,R.anim.dowtogo);
        linearLayout1.setAnimation(uptodown);
        linearLayout2.setAnimation(downtoup);

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
        imgWithGoogle = findViewById(R.id.btnWithGoogle);
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
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        googleApiClient = new GoogleApiClient.Builder(getApplicationContext())
                .enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                        Toast.makeText(LoginApp.this, "You got an Error !", Toast.LENGTH_SHORT).show();
                    }
                }).addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        imgWithGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });
//        Animation animFade = AnimationUtils.loadAnimation(this, R.anim.bouncein);
//        Animation animFade1 = AnimationUtils.loadAnimation(this, R.anim.bounceinn);
//        Animation animFade2 = AnimationUtils.loadAnimation(this, R.anim.float_in);
//        Animation animFade3 = AnimationUtils.loadAnimation(this, R.anim.fromtop);
//        imgWithFacebook.startAnimation(animFade);
//        imgWithGoogle.setAnimation(animFade);
//        imgLogin.setAnimation(animFade2);
        imgWithGoogle = findViewById(R.id.btnWithGoogle);

//        AccessToken accessToken = AccessToken.getCurrentAccessToken();
//        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
//        if (isLoggedIn) {
//            startActivity(new Intent(this, MainActivity.class));
//        }


        imgRegister = findViewById(R.id.btnRegister);
        imgRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegisterUser.class));
            }
        });
        edtUserLogin = findViewById(R.id.edtEmailLogin);
        edtPassLogin = findViewById(R.id.edtPasswordLogin);
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
        if (isLoggedIn) {
            startActivity(new Intent(LoginApp.this, MainActivity.class));
            finish();
        }
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

    private void updateUI() {
        Toast.makeText(this, "ĐĂNG NHẬP THÀNH CÔNG", Toast.LENGTH_SHORT).show();
        imgWithFacebook.setVisibility(View.INVISIBLE);
        Intent intent = new Intent(LoginApp.this, MainActivity.class);
        startActivity(intent);

        finish();

    }

    private void hanleFacebookAccessToken(AccessToken accessToken) {
        Log.d(TAG, "hanleFacebookAccessToken: " + accessToken);
        AuthCredential authCredential = FacebookAuthProvider.getCredential(accessToken.getToken());
        auth.signInWithCredential(authCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Log.d(TAG, "Success:onComplete: " + task.isSuccessful());
                updateUI();
                if (!task.isSuccessful()) {
                    Log.w(TAG, "SignInWithCredential: " + task.getException());
                    Toast.makeText(LoginApp.this, "Authentication failed", Toast.LENGTH_SHORT).show();
                    updateUI();
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
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
                // [START_EXCLUDE]

            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
         auth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = auth.getCurrentUser();

                        }

                        // ...
                    }
                });
    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
}
