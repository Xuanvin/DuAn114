package com.example.duana.BoSuuTap;

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
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;
import com.example.duana.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class LapTopGameMing extends AppCompatActivity {
    ArrayList<SanPham> sanPhamArrayList;
    RecyclerView recyclerView2;
    SanphamAdapter1 sanPhamAdapter;
    String url="https://sanphambanhang.000webhostapp.com/productgamemming.php";
private  ProgressDialog myProgress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laptopgamemming);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        myProgress = new ProgressDialog(this);
        myProgress.setMessage("Đang tải.....");
        myProgress.setCancelable(true);
        myProgress.show();
        recyclerView2 = findViewById(R.id.recyeviewgame);
        sanPhamArrayList = new ArrayList<>();
        GetData(url);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
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
                                sanPhamArrayList.add(new SanPham(
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

                        sanPhamAdapter = new SanphamAdapter1(LapTopGameMing.this, sanPhamArrayList);
                        recyclerView2.setLayoutManager(new GridLayoutManager(LapTopGameMing.this, 2));
                        recyclerView2.setAdapter(sanPhamAdapter);
                        Log.d("abv", "onResponse: " + sanPhamArrayList);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.getNetworkTimeMs();
                        Toast.makeText(LapTopGameMing.this, "Bạn bị mất kết nối mạng !", Toast.LENGTH_SHORT).show();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }

}
