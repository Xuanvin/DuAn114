package com.example.duana.ThuTucThanhToan;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.duana.Adapter.ChiTietDonHangAdpter;
import com.example.duana.model.ModelGioHang;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
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
public class XemDonHang extends AppCompatActivity implements View.OnClickListener {
    public static List<ModelGioHang> gioHangList;
    private TextView tamtinh,phivanchuyen,number,tongcong,diachi1,hoten,phone;
    ChiTietDonHangAdpter adpter;
    RecyclerView recyclerView;
    private SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xem_don_hang);
        preferences =getSharedPreferences("PrefsFile", Context.MODE_PRIVATE);
        ///++++++++++++++++++++++++++++++++++++number++++++++
        Toolbar toolbar = findViewById(R.id.toolbar);
        tamtinh=findViewById(R.id.tamtinh);
        phivanchuyen=findViewById(R.id.phivanchuyen);
        number=findViewById(R.id.number);
        tongcong=findViewById(R.id.tongcong);
        recyclerView=findViewById(R.id.recyeview12);
        diachi1=findViewById(R.id.diachi1);
        hoten=findViewById(R.id.hoten);
        phone=findViewById(R.id.phone);
        gioHangList=new ArrayList<>();
        //++++++++++++++++++++++++++++++++++++++++++++++
        toolbar.setOnClickListener(this);
        String url = "http://sanphambanhang.000webhostapp.com/giohang.php";
        String url2 = "http://sanphambanhang.000webhostapp.com/GetIdlogin.php";
        PostData(url);
        PostData1(url2);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.toolbar) {
            finish();
        }
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
                        hoten.setText(explrObject.getString("hoten"));
                        phone.setText(explrObject.getString("sdt"));
                        diachi1.setText( explrObject.getString("diachi")+"," + explrObject.getString("tinh")+"," + explrObject.getString("phuongxa")+"," +
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
    private void PostData(final String url) {
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
                        int a = sum += explrObject.getInt("Price_cart") * explrObject.getInt("Tongsp");
                        int b=ship * jsonArray.length();
                        String formart=formatter.format(a+b);
                        String formart1=formatter.format(a);
                        String formart2=formatter.format(b);
                        tamtinh.setText(""+formart1 + "đ");
                        tongcong.setText(""+formart + "đ");
                        phivanchuyen.setText(formart2 + "đ");
                        number.setText(""+jsonArray.length());
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
                adpter = new ChiTietDonHangAdpter(XemDonHang.this, gioHangList);
                recyclerView.setLayoutManager(new GridLayoutManager(XemDonHang.this, 1));
                Log.d("acv", "onResponse: " + recyclerView);
                recyclerView.setAdapter(adpter);

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
}
