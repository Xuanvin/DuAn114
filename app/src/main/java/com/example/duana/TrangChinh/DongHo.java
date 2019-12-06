package com.example.duana.TrangChinh;

import android.app.ProgressDialog;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.duana.Adapter.SanphamAdapter1;
import com.example.duana.model.SanPham;
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
import android.widget.Toast;

import com.example.duana.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class DongHo extends AppCompatActivity {
    RecyclerView recyclerViewdienthoai;
    private ArrayList<SanPham> arrayList;
    SanphamAdapter1 adapter;
    SwipeRefreshLayout swipeRefreshLayout;
    private ProgressDialog myProgress;
    String url = "http://sanphambanhang.000webhostapp.com/DongHo.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dong_ho);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        arrayList = new ArrayList<>();
        recyclerViewdienthoai = findViewById(R.id.recyeviewgame);
        swipeRefreshLayout = findViewById(R.id.SwipeRefreshLayoutdienthoai);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        GetData(url);
                        arrayList.clear();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 2500);
            }
        });
        myProgress = new ProgressDialog(this);
        myProgress.setMessage("Đang tải.....");
        myProgress.setCancelable(true);
        myProgress.show();
        GetData(url);
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
                                arrayList.add(new SanPham(
                                        object.getInt("Laptop_id"),
                                        object.getInt("Id_Category"),
                                        object.getString("khanangluutru"),
                                        object.getString("Name_Product"),
                                        object.getInt("Price_product"),
                                        object.getString("Characteristics"),
                                        object.getString("Name1_Information"),
                                        object.getInt("Ratingbar"),
                                        object.getString("Img1"),
                                        object.getString("Img2"),
                                        object.getString("Img3"),
                                        object.getString("Comment"),
                                        object.getString("Name1_Information"),
                                        object.getString("Name2_Information"),
                                        object.getString("Name3_Information"),
                                        object.getString("Name4_Information"),
                                        object.getString("Name5_Information"),
                                        object.getString("Information"),
                                        object.getString("ram"),
                                        object.getString("cpu"),
                                        object.getString("bonhotrong"),
                                        object.getString("dungluongpin")

                                ));

                                myProgress.dismiss();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        adapter = new SanphamAdapter1(DongHo.this, arrayList);
                        recyclerViewdienthoai.setLayoutManager(new GridLayoutManager(DongHo.this, 2));
                        recyclerViewdienthoai.setAdapter(adapter);
                        Log.d("abv", "onResponse: " + arrayList);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.getNetworkTimeMs();
                        Toast.makeText(DongHo.this, "Bạn bị mất kết nối mạng !", Toast.LENGTH_SHORT).show();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }
}
