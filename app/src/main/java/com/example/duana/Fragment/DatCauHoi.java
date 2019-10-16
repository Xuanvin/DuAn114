package com.example.duana.Fragment;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.duana.Adapter.BinhLuanAdapter;
import com.example.duana.R;
import com.example.duana.mode.BinhLuan;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DatCauHoi extends AppCompatActivity {
    ImageView back;
    EditText edt1;
    ImageButton img1;
    ListView datcauhs;
    ArrayList<BinhLuan> arrayList;
    BinhLuanAdapter adapter;
    String url = "http://sanphambanhang.000webhostapp.com/Comment.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dat_cau_hoi);
        edt1 = findViewById(R.id.edt1);
        img1 = findViewById(R.id.img1);
        datcauhs=findViewById(R.id.datcauhoi1);
        back = findViewById(R.id.back);
        arrayList=new ArrayList<>();
        GetData1(url);
        adapter=new BinhLuanAdapter(this,R.layout.datcauhoiiteam,arrayList);
        datcauhs.setAdapter(adapter);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
    private void GetData1(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            Log.d("AB", "size " + response.length());
                            try {
                                Log.e("tracker","datcauhoi: trying" +
                                        "");
                                JSONObject object = response.getJSONObject(i);

                                arrayList.add(new BinhLuan(
                                        object.getInt("ID"),
                                        object.getString("Coment"),
                                        object.getString("Date")



                                ));
                            } catch (JSONException e) {
                                Log.e("tracker","datcauhoi:" + e.getMessage());
                                e.printStackTrace();
                            }
                        }

                        adapter.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DatCauHoi.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }


}
