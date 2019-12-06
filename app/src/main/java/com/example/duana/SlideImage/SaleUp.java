package com.example.duana.SlideImage;

import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.duana.Adapter.DienThoaiAdapter;
import com.example.duana.model.SanPham;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.Toast;

import com.example.duana.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class SaleUp extends AppCompatActivity {
    RecyclerView recyclerView;
    DienThoaiAdapter adapter;
    ArrayList<SanPham> arrayList;
    String url = "http://sanphambanhang.000webhostapp.com/productsaleup.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saleup);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        //anh xa
        recyclerView = findViewById(R.id.saleup);
        //
        arrayList = new ArrayList<>();
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

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        adapter = new DienThoaiAdapter(SaleUp.this, arrayList, R.layout.itemslide);
                        recyclerView.setLayoutManager(new GridLayoutManager(SaleUp.this, 1));
                        recyclerView.setAdapter(adapter);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.getNetworkTimeMs();
                        Toast.makeText(SaleUp.this, "Bạn bị mất kết nối mạng !", Toast.LENGTH_SHORT).show();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }

}
