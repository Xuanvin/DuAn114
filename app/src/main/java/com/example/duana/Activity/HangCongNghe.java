package com.example.duana.Activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.duana.Adapter.HangCongNgheAdapter;
import com.example.duana.Adapter.SanphamAdapter1;
import com.example.duana.MainActivity;
import com.example.duana.mode.ModeHangCongNghe;
import com.example.duana.mode.SanPham;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.duana.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HangCongNghe extends AppCompatActivity {
private ArrayList<ModeHangCongNghe> modeHangCongNghes;
HangCongNgheAdapter adapter;
RecyclerView listView;
ImageView imgcongnghe;
private Activity mactivity;
SwipeRefreshLayout swipeRefreshLayout;
String url="http://sanphambanhang.000webhostapp.com/Hangcongnghe.php";
    private ProgressDialog myProgress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hang_cong_nghe);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mactivity=HangCongNghe.this;
        swipeRefreshLayout=findViewById(R.id.SwipeRefreshLayouthangcongnghe);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                       GetData(url);
                       modeHangCongNghes.clear();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 2500);
            }
        });
        imgcongnghe=findViewById(R.id.imgsapsan);
        Picasso.get().load("http://viethotdeal.com/wp-content/uploads/2018/02/05/8.jpg").into(imgcongnghe);
        myProgress = new ProgressDialog(this);
        myProgress.setMessage("Đang tải.....");
        myProgress.setCancelable(true);
        myProgress.show();
        listView=findViewById(R.id.listviewHangCongNghe);
        listView.setHorizontalScrollBarEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        modeHangCongNghes=new ArrayList<>();

        GetData(url);
    }

    @Override
    protected void onResume() {
        super.onResume();
        modeHangCongNghes=new ArrayList<>();
    }
    public static void restartActivity(Activity activity) {
        if (Build.VERSION.SDK_INT >= 11) {
            activity.recreate();
        } else {
            activity.finish();
            activity.startActivity(activity.getIntent());
        }
    }
    private void GetData(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject object = response.getJSONObject(i);
                                Log.d("ac", "onResponse: "+response.length());
                                modeHangCongNghes.add(new ModeHangCongNghe(
                                        object.getInt("ID"),
                                        object.getString("TenSP"),
                                        object.getString("Gia"),
                                        object.getString("GiamGia"),
                                        object.getString("DacDiem"),
                                        object.getString("BinhLuan"),
                                        object.getString("HinhAnh1")



                                ));
                                myProgress.dismiss();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        adapter=new HangCongNgheAdapter(HangCongNghe.this,modeHangCongNghes,R.layout.itemgiamgia);
                        listView.setAdapter(adapter);
                        listView.setLayoutManager(new GridLayoutManager(HangCongNghe.this, 1));
                        Log.d("acv", "onResponse: "+adapter);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(HangCongNghe.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }

}
