package com.example.duana.BoSuuTap;

import android.app.ProgressDialog;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.duana.Adapter.LapTopDellAdapter;
import com.example.duana.Adapter.SanphamAdapter1;
import com.example.duana.Sanpham;
import com.example.duana.model.ModelLaptapdell;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.duana.R;
import com.example.duana.model.SanPham;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class LapTopDell extends AppCompatActivity {
RecyclerView recyclerView;
private ArrayList<SanPham> arrayList;
SanphamAdapter1 adapter;
ImageView back12;

    SwipeRefreshLayout swipeRefreshLayout;
    String url="http://sanphambanhang.000webhostapp.com/laptopDell.php";
    private ProgressDialog myProgress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laptopdell);
        recyclerView=findViewById(R.id.laptopdell);
        arrayList=new ArrayList<>();

        back12=findViewById(R.id.back12);
        back12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        swipeRefreshLayout=findViewById(R.id.sw);
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
        Toolbar toolbar =findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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
                                Log.d("ac", "onResponse: "+response.length());
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

                                ) );

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        adapter=new SanphamAdapter1(LapTopDell.this,arrayList);
                        recyclerView.setAdapter(adapter);
                        recyclerView.setLayoutManager(new GridLayoutManager(LapTopDell.this, 2));
                        myProgress.dismiss();
                        Log.d("acv", "onResponse: "+adapter);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LapTopDell.this, "Mất kết nối mạng !", Toast.LENGTH_SHORT).show();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }

}
