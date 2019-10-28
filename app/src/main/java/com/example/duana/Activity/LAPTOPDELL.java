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
import com.example.duana.Adapter.LapTopDellAdapter;
import com.example.duana.Adapter.LaptopAdapter;
import com.example.duana.mode.ModeLaptop;
import com.example.duana.mode.ModelLaptapdell;
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
import android.widget.Toast;

import com.example.duana.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LAPTOPDELL extends AppCompatActivity {
RecyclerView recyclerView;
private ArrayList<ModelLaptapdell> arrayList;
LapTopDellAdapter adapter;
ImageView back12;
    private Activity mactivity;
    SwipeRefreshLayout swipeRefreshLayout;
    String url="http://sanphambanhang.000webhostapp.com/Laptop.php";
    private ProgressDialog myProgress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laptopdell);
        recyclerView=findViewById(R.id.laptopdell);
        arrayList=new ArrayList<>();
        mactivity=LAPTOPDELL.this;
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
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
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
                                arrayList.add(new ModelLaptapdell(
                                        object.getInt("ID"),
                                        object.getString("HinhAnh1"),
                                        object.getString("TenSP"),
                                        object.getString("Gia"),
                                        object.getString("GiamGia"),
                                        object.getString("Gia")



                                ));

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        adapter=new LapTopDellAdapter(LAPTOPDELL.this,arrayList,R.layout.itemgiamgia);
                        recyclerView.setAdapter(adapter);
                        recyclerView.setLayoutManager(new GridLayoutManager(LAPTOPDELL.this, 2));
                        myProgress.dismiss();
                        Log.d("acv", "onResponse: "+adapter);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LAPTOPDELL.this, "Mất kết nối mạng !", Toast.LENGTH_SHORT).show();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }
    public static void restartActivity(Activity activity) {
        if (Build.VERSION.SDK_INT >= 11) {
            activity.recreate();
        } else {
            activity.finish();
            activity.startActivity(activity.getIntent());
        }
    }
}
