package com.example.duana.ThuTucThanhToan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

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

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.example.duana.UserName.LoginApp.userId;

public class PhuongThucThanhToan extends AppCompatActivity implements View.OnClickListener {
    TextView tongtien,tamtinh;
    LinearLayout thanhtoan;
    private SharedPreferences preferences;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phuongthucthanhtoa);
        preferences =getSharedPreferences("PrefsFile", Context.MODE_PRIVATE);
        Toolbar toolbar=findViewById(R.id.toolbar);
        thanhtoan=findViewById(R.id.thanhtoan);
        tamtinh = findViewById(R.id.tamtinh);
        tongtien = findViewById(R.id.tongtien);
        thanhtoan.setOnClickListener(this);
        toolbar.setOnClickListener(this);
        String url = "http://sanphambanhang.000webhostapp.com/giohang.php";
        PostData(url);
    }
    @Override
    public void onClick(View v) {
switch (v.getId()){
    case R.id.toolbar:
        finish();
        break;
    case R.id.thanhtoan:
        startActivity(new Intent(PhuongThucThanhToan.this, ThanhToanKhiNhanHang.class));

}
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
                        String yourFormattedString1 = formatter.format(a);
                         tongtien.setText(yourFormattedString + "đ");
                         tamtinh.setText(yourFormattedString1+ "đ");
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
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("user_id",  preferences.getString("userId",userId));
                return params;
            }
        };
        requestQueue.add(stringRequest); // cái ni để làm chi qên r


    }


}
