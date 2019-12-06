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
import com.example.duana.model.ModelGioHang;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.Button;
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
import static com.example.duana.ThuTucThanhToan.KiemTra.gioHangList;
import static com.example.duana.ThuTucThanhToan.KiemTra.tongcong1;
import static com.example.duana.UserName.LoginApp.userId;

public class ThanhToanKhiNhanHang extends AppCompatActivity implements View.OnClickListener {
    TextView tongtien, tamtinh;
    Button xacnhandonhang;
    private SharedPreferences preferences;
    private  int tong=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanh_toan_khi_nhan_hang);
        preferences =getSharedPreferences("PrefsFile", Context.MODE_PRIVATE);
        Toolbar toolbar = findViewById(R.id.toolbar);
        tongtien = findViewById(R.id.tongtien);
        tamtinh = findViewById(R.id.tamtinh);
        xacnhandonhang = findViewById(R.id.xacnhandonhang);
        toolbar.setOnClickListener(this);
        xacnhandonhang.setOnClickListener(this);
        String url = "http://sanphambanhang.000webhostapp.com/giohang.php";

        PostData(url);

    }

    @Override
    public void onClick(View v) {
        String url3 = "http://sanphambanhang.000webhostapp.com/totalproduct.php";
        switch (v.getId()) {
            case R.id.toolbar:
                finish();
                break;
            case R.id.xacnhandonhang:
                DangKi(url3);
                startActivity(new Intent(ThanhToanKhiNhanHang.this, DonHangDaDuotDat.class));

        }
    }

    private void DangKi(String url) {
        for (final ModelGioHang modelGioHang : gioHangList) {
            Log.d("adc", "DangKi: " + modelGioHang.toString());

            RequestQueue requestQueue = Volley.newRequestQueue(Objects.requireNonNull(this));
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if (response.trim().equals("success")) {
                                Toast.makeText(ThanhToanKhiNhanHang.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(ThanhToanKhiNhanHang.this, "Vui  lòng  nhập đày đủ thông tin", Toast.LENGTH_SHORT).show();

                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(ThanhToanKhiNhanHang.this, "loiox mangj", Toast.LENGTH_SHORT).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
                    params.put("User_id",  preferences.getString("userId",userId));
                    params.put("Id_cartuser", String.valueOf(tong));
                    params.put("Name", modelGioHang.getTenSP());
                    params.put("Price", String.valueOf(modelGioHang.getGiaSP()));
                    params.put("Img", modelGioHang.getImg());
                    params.put("Tongsp", String.valueOf(modelGioHang.getTongso()));
                    params.put("Productprice", String.valueOf(modelGioHang.getGiaSP()*modelGioHang.getTongso()));
                    params.put("Totalproduct", tongcong1);

                    return params;
                }
            };
            requestQueue.add(stringRequest);
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
                        String yourFormattedString = formatter.format(a);
                        String yourFormattedString1 = formatter.format(a+b);
                        tongtien.setText(yourFormattedString1 + "đ");
                        tamtinh.setText(yourFormattedString + "đ");
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
