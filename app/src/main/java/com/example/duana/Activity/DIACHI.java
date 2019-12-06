package com.example.duana.Activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.duana.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.example.duana.UserName.LoginApp.userId;

public class DIACHI extends AppCompatActivity {
    private String url = "http://sanphambanhang.000webhostapp.com/updatediachi.php";
    private String urla = "http://sanphambanhang.000webhostapp.com/GetIdlogin.php";
    @SuppressLint("StaticFieldLeak")
    public static EditText hoten, diachi, tinh, quanhuyen, phuongxa, sdt;
    Button luu,huybo;
    private SharedPreferences preferences;
    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diachi);
        preferences =getSharedPreferences("PrefsFile", Context.MODE_PRIVATE);
        //++++++++++++++++++++++++++++++++++ ánh xạ
        hoten = findViewById(R.id.hoten);
        diachi = findViewById(R.id.dc);
        tinh = findViewById(R.id.tinh);
        quanhuyen = findViewById(R.id.quanhuyen);
        phuongxa = findViewById(R.id.phuongxa);
        sdt = findViewById(R.id.sdt);
        luu = findViewById(R.id.luu);
        huybo=findViewById(R.id.huybo);
        PostData(urla);
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a = hoten.getText().toString().trim();
                String b = diachi.getText().toString().trim();
                String c = tinh.getText().toString().trim();
                String d =quanhuyen.getText().toString().trim();
                String e =  phuongxa.getText().toString().trim();
                String f = sdt.getText().toString().trim();

                if (a.equals("") || b.equals("") || c.equals("") || d.equals("") || e.equals("") || f.equals("")) {
                    Toast.makeText(DIACHI.this, "Vui lòng điền đủ thông tin !", Toast.LENGTH_SHORT).show();
                } else {
                    UpdaterThongtin();
                }

            }
        });
        huybo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void UpdaterThongtin() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.trim().equals("success")) {
                    Toast.makeText(DIACHI.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                    finish();

                } else {
                    Toast.makeText(DIACHI.this, "Lỗi cập nhật", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DIACHI.this, "Lỗi ", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("user_id", preferences.getString("userId",userId));
                params.put("hoten", hoten.getText().toString().trim());
                params.put("diachi", diachi.getText().toString().trim());
                params.put("tinh", tinh.getText().toString().trim());
                params.put("quanhuyen", quanhuyen.getText().toString().trim());
                params.put("phuongxa", phuongxa.getText().toString().trim());

                params.put("sdt", sdt.getText().toString().trim());
                return params;
            }
        };
        requestQueue.add(request);
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
                        hoten.setText(explrObject.getString("hoten"));
                        diachi.setText(explrObject.getString("diachi" ));
                        tinh.setText(explrObject.getString("tinh" ));
                        phuongxa.setText(explrObject.getString("phuongxa" ));
                        quanhuyen.setText(explrObject.getString("quanhuyen" ));
                        sdt.setText(explrObject.getString("sdt" ));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("vvvv", "onErrorResponse: " + error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("user_id",preferences.getString("userId",userId));
                return params;
            }
        };
        requestQueue.add(stringRequest); // cái ni để làm chi qên r


    }
}
