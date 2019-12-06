package com.example.duana.Activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.duana.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.example.duana.UserName.LoginApp.userId;

public class CAPNHATTHONGTIN extends AppCompatActivity {
    private String url = "http://sanphambanhang.000webhostapp.com/GetIdlogin.php";
    private TextView tendn, emaidn, diachigiaohang;
    public static String pass;
    LinearLayout thaydoipass, diachi;
    private SharedPreferences preferences;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cap_nhat_thong_tin);
        preferences =getSharedPreferences("PrefsFile", Context.MODE_PRIVATE);
        // +++++++++++++++++++++++++++++++++++++++++ ánh xạ
        Toolbar toolbar = findViewById(R.id.toolbar);
        tendn = findViewById(R.id.tendangnhap);
        emaidn = findViewById(R.id.emailcapnhap);
        diachigiaohang = findViewById(R.id.diachigiaohang);
        diachi = findViewById(R.id.dcgh);
        thaydoipass = findViewById(R.id.thaydoipass);
        //+++++++++++++++++++++++++++++++++++++++++++++
        diachi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CAPNHATTHONGTIN.this, DIACHI.class));
            }
        });
        thaydoipass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CAPNHATTHONGTIN.this, THAYDOIMK.class));
            }
        });
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        PostData(url);


    }

    private void PostData(final String url) {
        final RequestQueue requestQueue = Volley.newRequestQueue(Objects.requireNonNull(this));
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(final String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("giohang");
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject explrObject = jsonArray.getJSONObject(i);
                        tendn.setText(explrObject.getString("user_name"));
                        emaidn.setText(explrObject.getString("email"));
                        pass = explrObject.getString("password");
                        diachigiaohang.setText(explrObject.getString("diachi" ) + explrObject.getString("tinh" ) + explrObject.getString("phuongxa" ) +
                                explrObject.getString("quanhuyen" ) + explrObject.getString("sdt" ));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) { // lau vc // log zo di t xem thu// chòe xíu, chừ lấy từng cái ra phải hân thì listview á
                Log.d("vvvv", "onErrorResponse: " + error); // mở phai php t xem thửu ok
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("user_id",  preferences.getString("userId",userId));
                return params;
            }
        };
        requestQueue.add(stringRequest); // cái ni để làm chi qên r


    }

}
