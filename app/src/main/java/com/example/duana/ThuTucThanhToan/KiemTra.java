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
import com.example.duana.Activity.DIACHI;
import com.example.duana.Adapter.AdapterGioHangKiemtra;
import com.example.duana.model.ModelGioHang;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.duana.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.example.duana.UserName.LoginApp.userId;

public class KiemTra extends AppCompatActivity {
    private RecyclerView listView;
    private LinearLayout addthem;
    private TextView tongcong, tenng, diachikiemtra, chinhsua;
    private EditText phone, email;
    private SharedPreferences preferences;
    private String url = "http://sanphambanhang.000webhostapp.com/giohang.php";
    private String url2 = "http://sanphambanhang.000webhostapp.com/GetIdlogin.php";
    private String url3 = "http://sanphambanhang.000webhostapp.com/totalproduct.php";
    public static List<ModelGioHang> gioHangList;
    AdapterGioHangKiemtra adapterGioHangKiemtra;
    public static String tongcong1;
    private  String tongsp1;
    private String productprice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kiem_tra);
        preferences =getSharedPreferences("PrefsFile", Context.MODE_PRIVATE);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //++++++++++++++++++++++++++++++++++++++++++ ánh xạ
        listView = findViewById(R.id.listview);
        tongcong = findViewById(R.id.tongcong);
        addthem = findViewById(R.id.addthem);
        tenng = findViewById(R.id.tenng);
        diachikiemtra = findViewById(R.id.diachikiemtra);
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.email);
        chinhsua = findViewById(R.id.chinhsua);
        //=============================================
        chinhsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(KiemTra.this, DIACHI.class));
            }
        });
        addthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(KiemTra.this, PhuongThucThanhToan.class));
//                DangKi(url3);
//                Xoa(idcart);
            }
        });
        gioHangList = new ArrayList<>();
        PostData(url);
        PostData1(url2);

    }

    private void PostData(final String url) {
        tongcong.setText("0 đ");
        final RequestQueue requestQueue = Volley.newRequestQueue(Objects.requireNonNull(this));
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(final String response) {
                int sum = 0;
                int ship = 11000;
                gioHangList.clear();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("giohang");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject explrObject = jsonArray.getJSONObject(i);
                        DecimalFormat formatter = new DecimalFormat("#,###,###");

                        int a=sum += explrObject.getInt("Price_cart") * explrObject.getInt("Tongsp");
                        int b=ship * jsonArray.length();
                        String yourFormattedString = formatter.format(a+b);
                        tongcong1= String.valueOf(a+b);
                        tongcong.setText(yourFormattedString);
                        productprice= String.valueOf(a);
                             gioHangList.add(new ModelGioHang(
                                explrObject.getInt("Id_detell"),
                                explrObject.getInt("Id_cart"),
                                explrObject.getInt("Tongsp"),
                                explrObject.getString("Name_cart"),
                                explrObject.getInt("Price_cart"),
                                explrObject.getString("User_id"),
                                explrObject.getString("Img")
                        ));

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                adapterGioHangKiemtra = new AdapterGioHangKiemtra(KiemTra.this, gioHangList);
                listView.setLayoutManager(new GridLayoutManager(KiemTra.this, 1));
                Log.d("acv", "onResponse: " + listView);
                listView.setAdapter(adapterGioHangKiemtra);

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
                params.put("user_id", preferences.getString("userId",userId));
                return params;
            }
        };
        requestQueue.add(stringRequest); // cái ni để làm chi qên r


    }

    private void PostData1(final String url) {
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
                        tenng.setText(explrObject.getString("hoten"));
                        phone.setText(explrObject.getString("sdt"));
                        email.setText(explrObject.getString("email"));
                        diachikiemtra.setText(explrObject.getString("diachi")+"," + explrObject.getString("tinh")+"," + explrObject.getString("phuongxa")+"," +
                                explrObject.getString("quanhuyen")+"," + explrObject.getString("sdt"));
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
                params.put("user_id", preferences.getString("userId",userId));
                return params;
            }
        };
        requestQueue.add(stringRequest); // cái ni để làm chi qên r


    }

//    private void DangKi(String url) {
//        for (final ModelGioHang modelGioHang : gioHangList) {
//            Log.d("adc", "DangKi: " + modelGioHang.toString());
//
//            RequestQueue requestQueue = Volley.newRequestQueue(Objects.requireNonNull(this));
//            StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
//                    new Response.Listener<String>() {
//                        @Override
//                        public void onResponse(String response) {
//                            if (response.trim().equals("success")) {
//                                Toast.makeText(KiemTra.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
//                            } else {
//                                Toast.makeText(KiemTra.this, "Vui  lòng  nhập đày đủ thông tin", Toast.LENGTH_SHORT).show();
//
//                            }
//                        }
//                    }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    Toast.makeText(KiemTra.this, "loiox mangj", Toast.LENGTH_SHORT).show();
//                }
//            }) {
//                @Override
//                protected Map<String, String> getParams() {
//                    Map<String, String> params = new HashMap<>();
//                    params.put("User_id", userId);
//                    params.put("Id_cartuser", String.valueOf(tong));
//                    params.put("Name", modelGioHang.getTenSP());
//                    params.put("Price", String.valueOf(modelGioHang.getGiaSP()));
//                    params.put("Img", modelGioHang.getImg());
//                    params.put("Tongsp", String.valueOf(modelGioHang.getTongso()));
//                    params.put("Productprice", String.valueOf(modelGioHang.getGiaSP()*modelGioHang.getTongso()));
//                    params.put("Totalproduct", tongcong1);
//
//                    return params;
//                }
//            };
//            requestQueue.add(stringRequest);
//        }



}
