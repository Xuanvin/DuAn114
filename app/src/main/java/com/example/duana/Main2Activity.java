package com.example.duana;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.duana.Adapter.SanphamAdapter1;
import com.example.duana.Adapter.ViewpaperAdapter;
import com.example.duana.Fragment.Chitiet;
import com.example.duana.Fragment.FragmentGiohang;
import com.example.duana.Fragment.ThemGioHang;
import com.example.duana.mode.MuaSP;
import com.example.duana.mode.SanPham;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.example.duana.Adapter.SanphamAdapter1.gia;
import static com.example.duana.Adapter.SanphamAdapter1.img1;
import static com.example.duana.Adapter.SanphamAdapter1.img2;
import static com.example.duana.Adapter.SanphamAdapter1.img3;
import static com.example.duana.Adapter.SanphamAdapter1.tenSp;

public class Main2Activity extends AppCompatActivity {
    Chitiet chitiet;
    ViewPager viewPager;
    MenuItem prevMenuItem;
    LinearLayout addthem;
    SanphamAdapter1 adapter1;
    int position;
    private ArrayList<MuaSP> arrayList;
String urString="http://sanphambanhang.000webhostapp.com/Themgiohang.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        addthem = findViewById(R.id.addthem);
        addthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ThemGioHang themGioHang=new ThemGioHang();
                themGioHang.show(getSupportFragmentManager(),"exampleBottomSheet");
//                DangKi(urString);
            }
        });
        viewPager = (ViewPager) findViewById(R.id.viewpager1);
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



}
