package com.example.duana.MainChinh;

import android.annotation.SuppressLint;
import android.content.Intent;
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
import com.example.duana.Fragment.ChiTietProduct.SlideViewChitiet;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.duana.Fragment.ThemProduct.ThemSlideViewFragment;
import com.example.duana.R;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.example.duana.Adapter.DienThoaiAdapter.postion;
import static com.example.duana.Fragment.ThemProduct.ThemGioHang.number;
import static com.example.duana.MainChinh.Main2Activity.idcartt;
import static com.example.duana.UserName.LoginApp.userId;

public class Main3Activity extends AppCompatActivity {
    SlideViewChitiet slideViewChitiet;
    ViewPager viewPager;
    MenuItem prevMenuItem;
    LinearLayout addthem,muangay;
    private String urString = "http://sanphambanhang.000webhostapp.com/Themgiohang.php";
    private String getUrString = "http://sanphambanhang.000webhostapp.com/idcart.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        addthem = findViewById(R.id.addthem);
        muangay=findViewById(R.id.muangay);
        PostData(getUrString);
        muangay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DangKi(urString);
            }
        });
        addthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ThemSlideViewFragment themGioHang=new ThemSlideViewFragment();
                themGioHang.show(getSupportFragmentManager(),"exampleBottomSheet");
//
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
        slideViewChitiet = new SlideViewChitiet();
        adapter.addFragment(slideViewChitiet      );

        viewPager.setAdapter(adapter);
    }
    private void DangKi(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(Objects.requireNonNull(this));
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.trim().equals("success")) {
                            startActivity(new Intent(Main3Activity.this, KiemTra.class));
                        }else {
                            Toast.makeText(Main3Activity.this, "Vui  lòng  nhập đày đủ thông tin", Toast.LENGTH_SHORT).show();

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Main3Activity.this, "loiox mangj", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("Id_cart", idcartt);
                params.put("Tongsp",number.getText().toString().trim());
                params.put("Name_cart", postion.getName_Product());
                params.put("Price_cart", String.valueOf(postion.getPrice_product()));
                params.put("Img", postion.getImg1());

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
                Toast.makeText(Main3Activity.this, response, Toast.LENGTH_SHORT).show();

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
                params.put("user_id", String.valueOf(userId));
                return params;
            }
        };
        requestQueue.add(stringRequest); // cái ni để làm chi qên r


    }

}
