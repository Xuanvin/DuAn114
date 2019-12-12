package com.example.duana.Adapter.Vin.Adapter;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.duana.Adapter.Vin.Adapter.model.Modelthongbao;
import com.example.duana.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;


public class Frag2 extends Fragment {
    private RecyclerView recyclerView;
    private ArrayList<Modelthongbao> arrayList;
    private Adapterthongbao adapterthongbao;
    private String url="https://sunner.000webhostapp.com/getthongbao2.php";



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View  view=   inflater.inflate(R.layout.fragment_frag2, container, false);
        recyclerView = view.findViewById(R.id.thongbaoRecyclerVier);
        arrayList= new ArrayList<>();
        GetData(url);
        return view ;

    }
    private void GetData(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(Objects.requireNonNull(getActivity()));
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {

                            try {
                                JSONObject object = response.getJSONObject(i);
                                Toast.makeText(getContext(), object.toString(), Toast.LENGTH_SHORT).show();
                                arrayList.add(new Modelthongbao(
                                        object.getInt("ID"),
                                        object.getString("name"),
                                        object.getString("ngay"),
                                        object.getString("thongtin"),
                                        object.getString("capnhattin"),
                                        object.getString("img1")



                                ));


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        adapterthongbao = new Adapterthongbao(getContext(), arrayList);
                        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1));
                        recyclerView.setAdapter(adapterthongbao);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), ""+error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }




}
