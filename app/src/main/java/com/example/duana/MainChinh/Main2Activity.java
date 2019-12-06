package com.example.duana.MainChinh;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.duana.ThuTucThanhToan.KiemTra;
import com.example.duana.Adapter.slideviewpag.ViewpaperAdapter;
import com.example.duana.Fragment.ChiTietProduct.Chitiet;
import com.example.duana.Fragment.ThemProduct.ThemGioHang;
import com.example.duana.R;
import com.example.duana.ThuTucThanhToan.KiemTra2;
import com.example.duana.model.Interface.Idcart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.example.duana.Adapter.SanphamAdapter1.gia;
import static com.example.duana.Adapter.SanphamAdapter1.img1;
import static com.example.duana.Adapter.SanphamAdapter1.tenSp;
import static com.example.duana.Fragment.ThemProduct.ThemGioHang.number;
import static com.example.duana.UserName.LoginApp.userId;

public class Main2Activity extends AppCompatActivity {
    Chitiet chitiet;
    ViewPager viewPager;
    MenuItem prevMenuItem;
    LinearLayout addthem, muangay;
    public static String idcartt;
    private String getIdcartt;
    SharedPreferences.Editor editor;
    private SharedPreferences preferences;
    private String urString = "http://sanphambanhang.000webhostapp.com/Themgiohang2.php";
    private String getUrString = "http://sanphambanhang.000webhostapp.com/idcart.php";
    private String url1 = "http://sanphambanhang.000webhostapp.com/CreatCart.php";
int i=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        preferences =getSharedPreferences("PrefsFile", Context.MODE_PRIVATE);
        editor = preferences.edit();
        addthem = findViewById(R.id.addthem);
        muangay = findViewById(R.id.muangay);
        PostData(getUrString);
        muangay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DangKi(urString);
            }
        });
        getIdcartt = "";
        addthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ThemGioHang themGioHang = new ThemGioHang();
                themGioHang.show(getSupportFragmentManager(), "exampleBottomSheet");

            }
        });
        viewPager = findViewById(R.id.viewpager1);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (prevMenuItem != null) {
                    prevMenuItem.setChecked(false);
                } else {

                }
                Log.d("page", "onPageSelected: " + position);


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        setupViewPager(viewPager);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewpaperAdapter adapter = new ViewpaperAdapter(getSupportFragmentManager());
        chitiet = new Chitiet();
        adapter.addFragment(chitiet);

        viewPager.setAdapter(adapter);
    }

    private void DangKi1(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(Objects.requireNonNull(this));
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.trim().equals("success")) {
                            Toast.makeText(Main2Activity.this, "Thêm  thành Công", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(Main2Activity.this, "Vui  lòng  nhập đày đủ thông tin", Toast.LENGTH_SHORT).show();

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Main2Activity.this, "loiox mangj", Toast.LENGTH_SHORT).show();
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

    private void DangKi(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(Objects.requireNonNull(this));
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.trim().equals("success")) {
                            startActivity(new Intent(Main2Activity.this, KiemTra2.class));
                        } else {
                            Toast.makeText(Main2Activity.this, "Vui  lòng  nhập đày đủ thông tin", Toast.LENGTH_SHORT).show();

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Main2Activity.this, "loiox mangj", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("Id_cart", idcartt);
                params.put("Tongsp", String.valueOf(i));
                params.put("Name_cart", tenSp);
                params.put("Price_cart", String.valueOf(gia));
                params.put("Img", img1);

                return params;
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
                idcartt = response;
                editor.putString("idCart", idcartt);
                editor.apply();
                Toast.makeText(Main2Activity.this, response, Toast.LENGTH_SHORT).show();
                if (response == null || response.equals("")) {
                    DangKi1(url1);

                    Toast.makeText(Main2Activity.this, "1", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Main2Activity.this, "2", Toast.LENGTH_SHORT).show();

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


}
