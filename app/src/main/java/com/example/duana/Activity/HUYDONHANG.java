package com.example.duana.Activity;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.duana.Adapter.Adapterhuydon;
import com.example.duana.model.ModelChoVanChuyen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;

import com.example.duana.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.example.duana.UserName.LoginApp.userId;

public class HUYDONHANG extends AppCompatActivity {
private String url="http://sanphambanhang.000webhostapp.com/HuyDonHang.php";
private RecyclerView recyclerView;
private List<ModelChoVanChuyen >vanChuyens;
private Adapterhuydon adapterChoVanChuyen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_huydonhang);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // +++++++++++++++++++++++++++++++++++++++++++ ánh xạ
        recyclerView=findViewById(R.id.recyeview);
        vanChuyens=new ArrayList<>();
        PostData(url);
    }
    private void PostData(final String url) {
        final RequestQueue requestQueue = Volley.newRequestQueue(Objects.requireNonNull(this));
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(final String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("giohang");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject explrObject = jsonArray.getJSONObject(i);
                        vanChuyens.add(new ModelChoVanChuyen(
                                explrObject.getInt("id_user"),
                                explrObject.getInt("id_cartuser"),
                                explrObject.getString("name"),
                                explrObject.getInt("price"),
                                explrObject.getInt("tongsp"),
                                explrObject.getInt("tongsp"),
                                explrObject.getString("img"),
                                explrObject.getInt("productprice")
                        ));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                adapterChoVanChuyen = new Adapterhuydon(vanChuyens, HUYDONHANG.this);
                recyclerView.setLayoutManager(new GridLayoutManager(HUYDONHANG.this, 1));
                recyclerView.setAdapter(adapterChoVanChuyen);

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
