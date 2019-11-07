package com.example.duana;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.duana.Adapter.GioHangAdapter;
import com.example.duana.Fragment.FragmentGiohang;
import com.example.duana.mode.ModelGioHang;
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
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginApp extends AppCompatActivity {
    ImageView imgLogin, imgRegister;
    SignInButton imgWithGoogle;
    LinearLayout linearLayout1, linearLayout2;
    EditText edtUserLogin, edtPassLogin;
    private FirebaseAuth auth;
    ProgressDialog dialog;
    Animation uptodown, downtoup;
    LoginButton imgWithFacebook;
    private Class<FragmentGiohang> activity=FragmentGiohang.class;
    private CallbackManager callbackManager;
    public  static  String  userId  = null;
    private static final int RC_SIGN_IN = 9001;
    private GoogleApiClient googleApiClient;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    String TAG = "LoginApp";
    String url = "http://sanphambanhang.000webhostapp.com/Logina.php";
String getUrl="http://sanphambanhang.000webhostapp.com/Logina.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

        setContentView(R.layout.activity_login_app);
        callbackManager = CallbackManager.Factory.create();
        linearLayout1 = findViewById(R.id.linearLayout1);
        linearLayout2 = findViewById(R.id.linearLayout2);
        uptodown = AnimationUtils.loadAnimation(this, R.anim.utogo);
        downtoup = AnimationUtils.loadAnimation(this, R.anim.dowtogo);
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
        imgLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login(url);

            }
        });
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
//
    }

    private void Login(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                userId = response;
                Toast.makeText(LoginApp.this, response, Toast.LENGTH_SHORT).show();
                if (!response.equals("null")) {
                    Toast.makeText(LoginApp.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginApp.this, MainActivity.class));
                } else {
                    Toast.makeText(LoginApp.this, "Đăng nhập  thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoginApp.this, "error:" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("user_name", edtUserLogin.getText().toString().trim());
                params.put("password", edtPassLogin.getText().toString().trim());
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
    private void GetData(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            String  string=response.getString(0);
                            Toast.makeText(LoginApp.this, string.toString(), Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
//                        for (int i = 0; i < response.length(); i++) {
//
//                            try {
//                                JSONObject object = response.getJSONObject(i);
//                                object.getString("user_id");
//
//                            } catch (JSONException e) {
//                                Log.e("abc", "onResponse: ",e );
//                            }
//                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginApp.this, "Bạn bị mất kết nối mạng !", Toast.LENGTH_SHORT).show();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
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
