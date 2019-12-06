package com.example.duana.UserName;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.duana.MainChinh.MainActivity;
import com.example.duana.R;
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

import java.util.HashMap;
import java.util.Map;

public class LoginApp extends AppCompatActivity {
    ImageView imgRegister;
    Button imgLogin;
    LinearLayout linearLayout1, linearLayout2;
    EditText edtUserLogin;
    EditText edtPassLogin;
    ProgressDialog dialog;
    private String u, p, c, d;
    Animation uptodown, downtoup;
    public static String userId = null;
    String TAG = "LoginApp";
    String url = "https://sanphambanhang.000webhostapp.com/Logina.php";
    public static String edtUserLogin1, edtPassLogin2, email;
    String tringa, tringb;
    private CheckBox mCheckRemember;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private static final String PREFS_NAME = "PrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

        setContentView(R.layout.activity_login_app);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        // +++++++++++++++++++++++++++++++++++++++++anh xa
        linearLayout1 = findViewById(R.id.linearLayout1);
        linearLayout2 = findViewById(R.id.linearLayout2);
        edtUserLogin = findViewById(R.id.edtEmailLogin);
        edtPassLogin = findViewById(R.id.edtPasswordLogin);
        mCheckRemember = findViewById(R.id.checkBox);
        tringa = edtUserLogin.getText().toString().trim();
        tringb = edtPassLogin.getText().toString().trim();
        uptodown = AnimationUtils.loadAnimation(this, R.anim.utogo);
        downtoup = AnimationUtils.loadAnimation(this, R.anim.dowtogo);
        linearLayout1.setAnimation(uptodown);
        linearLayout2.setAnimation(downtoup);
        sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        // +++++++++++++++++++++++++++++++++++++++++++++ Check box

        String mUserId = sharedPreferences.getString("userId","");

        if (mUserId != ""){
            startActivity(new Intent(LoginApp.this, MainActivity.class));
            finish();
        }

        imgLogin = findViewById(R.id.btnLogin);
        imgLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (checkEditText(edtUserLogin) && checkEditText(edtPassLogin)) {
                    dialog = new ProgressDialog(LoginApp.this);
                    dialog.setMessage("Đang đăng nhập...");
                    dialog.setCanceledOnTouchOutside(false);
                    dialog.show();
                    Login(url);
                }

                boolean aBoolean = mCheckRemember.isChecked();
                editor.putString("pref_name", edtUserLogin.getText().toString());
                editor.putString("pref_pass", edtPassLogin.getText().toString());
                editor.putBoolean("pref_check", aBoolean);
                editor.apply();


            }
        });


        imgRegister = findViewById(R.id.btnRegister);
        imgRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegisterUser.class));
            }
        });


        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
        if (isLoggedIn) {
            startActivity(new Intent(LoginApp.this, MainActivity.class));
            finish();
        }
        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        if (sharedPreferences.contains("pref_name")) {
            u = sharedPreferences.getString("pref_name", "not_found");
            edtUserLogin.setText(u);
        }
        if (sharedPreferences.contains("pref_pass")) {
            p = sharedPreferences.getString("pref_pass", "not_found");
            edtPassLogin.setText(p);
        }
        if (sharedPreferences.contains("id_user")) {
            c = sharedPreferences.getString("id_user", "not_found");
        }
        if (sharedPreferences.contains("pref_check")) {
            boolean b = sharedPreferences.getBoolean("pref_check", false);
            mCheckRemember.setChecked(b);
        }
        Toast.makeText(this, p, Toast.LENGTH_SHORT).show();

    }


    private void Login(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                userId = response;

                Toast.makeText(LoginApp.this, response, Toast.LENGTH_SHORT).show();
                if (!response.isEmpty()) {
                    Toast.makeText(LoginApp.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    editor.putString("userId", userId);
                    editor.apply();
                    edtUserLogin1 = edtUserLogin.getText().toString().trim();
                    edtPassLogin2 = edtPassLogin.getText().toString().trim();
                    startActivity(new Intent(LoginApp.this, MainActivity.class));
                    finish();
                } else {
                    Toast.makeText(LoginApp.this, "Đăng nhập  thất bại", Toast.LENGTH_SHORT).show();

                }
                dialog.dismiss();
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoginApp.this, "Vui lòng kiểm tra lại mạng !!", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("email", edtUserLogin.getText().toString().trim());
                params.put("password", edtPassLogin.getText().toString().trim());
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    private boolean checkEditText(EditText editText) {
        if (editText.getText().toString().trim().length() > 0)
            return true;
        else {
            editText.setError("Vui lòng nhập dữ liệu!");
        }
        return false;
    }


}
