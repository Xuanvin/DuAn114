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
import com.example.duana.Adapter.AdapterGioHangKiemtra2;
import com.example.duana.model.ModelGioHang;
import com.example.duana.model.ModelGioHang2;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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

import static com.example.duana.Adapter.AdapterGioHangKiemtra2.gioHangl;
import static com.example.duana.UserName.LoginApp.userId;

public class KiemTra2 extends AppCompatActivity {
    private RecyclerView listView;
    private LinearLayout addthem;
    private TextView tongcong, tenng, diachikiemtra, chinhsua;
    private EditText phone, email;
    private SharedPreferences preferences;
    private String url = "http://sanphambanhang.000webhostapp.com/giohang2.php";
    private String url2 = "http://sanphambanhang.000webhostapp.com/GetIdlogin.php";
    private String url3 = "http://sanphambanhang.000webhostapp.com/totalproduct.php";
    private String xoa = "http://sanphambanhang.000webhostapp.com/delete2.php";
    public static List<ModelGioHang2> gioHangList;
    AdapterGioHangKiemtra2 adapterGioHangKiemtra;
    public static String tongcong1;
    private String tongsp1;
    private String productprice;
   public static int idicart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kiem_tra2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        preferences = getSharedPreferences("PrefsFile", Context.MODE_PRIVATE);
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Xoa(idicart);
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
                startActivity(new Intent(KiemTra2.this, DIACHI.class));
            }
        });
        addthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(KiemTra2.this, PhuongThucThanhToan2.class));
//                DangKi(url3);
//                Xoa(idcart);
            }
        });
        gioHangList = new ArrayList<>();
        PostData(url);
        PostData1(url2);

    }

    public void Xoa(final int id) {
        RequestQueue requestQueue = Volley.newRequestQueue(Objects.requireNonNull(this));
        StringRequest stringRequest = new StringRequest(Request.Method.POST, xoa,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.trim().equals("success")) {
                            Toast.makeText(KiemTra2.this, "Xóa Thành công", Toast.LENGTH_SHORT).show();
                            PostData(url);
                        } else {
                            Toast.makeText(KiemTra2.this, "Lỗi Xóa", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(KiemTra2.this, "Xảy ra lỗi", Toast.LENGTH_SHORT).show();
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

    @Override
    public void onBackPressed() {
        Xoa(idicart);
        super.onBackPressed();
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

                        int a = sum += explrObject.getInt("Price_cart") * explrObject.getInt("Tongsp");
                        int b = ship * jsonArray.length();
                        String yourFormattedString = formatter.format(a + b);
                        tongcong1 = String.valueOf(a + b);
                        tongcong.setText(yourFormattedString);
                        productprice = String.valueOf(a);
                        gioHangList.add(new ModelGioHang2(
                                explrObject.getInt("Id_detell"),
                                explrObject.getInt("Id_cart"),
                                explrObject.getInt("Tongsp"),
                                explrObject.getString("Name_cart"),
                                explrObject.getInt("Price_cart"),
                                explrObject.getString("User_id"),
                                explrObject.getString("Img")
                        ));
                        idicart = explrObject.getInt("Id_cart");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                adapterGioHangKiemtra = new AdapterGioHangKiemtra2(KiemTra2.this, gioHangList);
                listView.setLayoutManager(new GridLayoutManager(KiemTra2.this, 1));
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
                params.put("user_id", preferences.getString("userId", userId));
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
                        diachikiemtra.setText(explrObject.getString("diachi") + "," + explrObject.getString("tinh") + "," + explrObject.getString("phuongxa") + "," +
                                explrObject.getString("quanhuyen") + "," + explrObject.getString("sdt"));
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
                params.put("user_id", preferences.getString("userId", userId));
                return params;
            }
        };
        requestQueue.add(stringRequest); // cái ni để làm chi qên r


    }
}
