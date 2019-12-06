package com.example.duana.ThuTucThanhToan;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.duana.MainChinh.MainActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duana.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import static com.example.duana.Fragment.TrangChinhFragment.FragmentGiohang.idcart;
import static com.example.duana.ThuTucThanhToan.KiemTra2.idicart;
import static com.example.duana.UserName.LoginApp.userId;

public class DonHangDaDuotDat extends AppCompatActivity implements View.OnClickListener {
 TextView donhangdadat;
 SharedPreferences preferences;
    String xoa = "http://sanphambanhang.000webhostapp.com/xoakiemtra.php";
TextView xemdonhang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_don_hang_da_duot_dat);
        preferences =getSharedPreferences("PrefsFile", Context.MODE_PRIVATE);
        Toolbar toolbar = findViewById(R.id.toolbar);
        donhangdadat=findViewById(R.id.donhangdadat);
        xemdonhang=findViewById(R.id.xemdonhang);
        toolbar.setOnClickListener(this);
        xemdonhang.setOnClickListener(this);

        String url = "http://sanphambanhang.000webhostapp.com/giohang.php";
        PostData(url);
    }
    @Override
    public void onClick(View v) {
switch (v.getId()){
    case R.id.toolbar:
        Xoa(idicart);
        startActivity(new Intent(DonHangDaDuotDat.this, MainActivity.class));
       finish();
        break;
    case R.id.xemdonhang:
        startActivity(new Intent(DonHangDaDuotDat.this, XemDonHang.class));
}
    }
    public void Xoa(final int id) {
        RequestQueue requestQueue = Volley.newRequestQueue(Objects.requireNonNull(this));
        StringRequest stringRequest = new StringRequest(Request.Method.POST, xoa,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.trim().equals("success")) {
                        } else {
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DonHangDaDuotDat.this, "Xảy ra lỗi", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parm = new HashMap<>();
                parm.put("ero", String.valueOf(id));

                return parm;
            }
        };
        requestQueue.add(stringRequest);
    }
    private void PostData(final String url) {
        final RequestQueue requestQueue = Volley.newRequestQueue(Objects.requireNonNull(this));
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(final String response) {
                int sum = 0;
                int ship=11000;

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("giohang");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject explrObject = jsonArray.getJSONObject(i);
                        DecimalFormat formatter = new DecimalFormat("#,###,###");
                        int a = sum += explrObject.getInt("Price_cart") * explrObject.getInt("Tongsp");
                        int b=ship * jsonArray.length();
                        String yourFormattedString = formatter.format(a+b);
                        donhangdadat.setText(yourFormattedString + "đ");
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
                params.put("user_id", preferences.getString("userId",userId));
                return params;
            }
        };
        requestQueue.add(stringRequest);


    }


}
