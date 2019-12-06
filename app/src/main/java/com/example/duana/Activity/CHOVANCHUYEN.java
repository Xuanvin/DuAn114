package com.example.duana.Activity;

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
import com.example.duana.Adapter.AdapterChoVanChuyen;
import com.example.duana.model.ModelChoVanChuyen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;

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

public class CHOVANCHUYEN extends AppCompatActivity {
private String url="http://sanphambanhang.000webhostapp.com/Chovanchuyen.php";
private List<ModelChoVanChuyen>vanChuyens;
private AdapterChoVanChuyen setAdapter;
public static RecyclerView recyviewa;
    private SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chovanchuyen);
        preferences =getSharedPreferences("PrefsFile", Context.MODE_PRIVATE);
        Toolbar toolbar = findViewById(R.id.toolbar);
       toolbar.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               finish();
           }
       });
        vanChuyens=new ArrayList<>();
        // anh xạ
        recyviewa=findViewById(R.id.recyviewa);
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

                setAdapter = new AdapterChoVanChuyen(vanChuyens, CHOVANCHUYEN.this);
                recyviewa.setLayoutManager(new GridLayoutManager(CHOVANCHUYEN.this, 1));
                recyviewa.setAdapter(setAdapter);

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
                params.put("user_id",  preferences.getString("userId",userId));
                return params;
            }
        };
        requestQueue.add(stringRequest); // cái ni để làm chi qên r


    }

}
