package com.example.duana.Fragment;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.duana.Adapter.GioHangAdapter;
import com.example.duana.R;
import com.example.duana.mode.ModelGioHang;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.example.duana.LoginApp.userId;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentGiohang extends Fragment {
    private ArrayList<ModelGioHang> arrayList;
    GioHangAdapter adapter;
    private Context context;
    private ProgressDialog myProgress;
    ListView listView;

    TextView phivanchuyen, tongcong;
    String url = "http://sanphambanhang.000webhostapp.com/giohang.php";
    String xoa = "http://sanphambanhang.000webhostapp.com/delete.php";
    public static String repos;

    public FragmentGiohang() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fragment_giohang, container, false);
        listView = v.findViewById(R.id.listviewGioHang);
        phivanchuyen = v.findViewById(R.id.phivc);
        tongcong = v.findViewById(R.id.tongcong);
        arrayList = new ArrayList<>();
        GetData(url);
        PostData(url);
        return v;
    }

    public void Xoa(final int id) {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, xoa,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.trim().equals("success")) {
                            Toast.makeText(getContext(), "Xóa Thành công", Toast.LENGTH_SHORT).show();
                            GetData(url);
                        } else {
                            Toast.makeText(getContext(), "Lỗi Xóa", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Xảy ra lỗi", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parm = new HashMap<>();
                parm.put("ero", String.valueOf(id));

                return parm;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void PostData(final String url) {
        final RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(final String response) {
                for (int i = 0; i <response.length() ; i++) {


                }

//                Log.d("vvvv", "onErrorResponse: " + response); // ran k co repose :v
//                String rp = response.replace("[", "");
//                String rp1 = rp.replace("]", "");
//                Log.d("vvvv", "onErrorResponse: " + rp1);
//
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) { // lau vc // log zo di t xem thu// chòe xíu, chừ lấy từng cái ra phải hân thì listview á
                Log.d("vvvv", "onErrorResponse: " + error); // mở phai php t xem thửu ok
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("user_id", userId);
                Log.d("vvvv", "onErrorResponse: " + userId);
                return params;
            }
        };
        requestQueue.add(stringRequest); // cái ni để làm chi qên r
        //post id và gét giỏ hàng m viết lại cái postdata như bth chưa jk nhue bữa trc dok


    }//rr

//    private void PostData(final String url) {
//        final RequestQueue requestQueue = Volley.newRequestQueue(getContext());
//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, url, null,
//                new Response.Listener<JSONArray>() {
//
//                    @Override
//                    public void onResponse(JSONArray response) {
//
////                        int sum = 0;
////                        arrayList.clear();
////                        for (int i = 0; i < response.length(); i++) {
////
////                            try {
////                                JSONObject object = response.getJSONObject(i);
////                                DecimalFormat formatter = new DecimalFormat("#,###,###");
////                                String yourFormattedString = formatter.format(sum += object.getInt("Price_cart"));
////                                tongcong.setText(yourFormattedString + " đ ");
////                                arrayList.add(new ModelGioHang(
////                                        object.getInt("Id_cart"),
////                                        object.getString("Name_cart"),
////                                        object.getInt("Price_cart"),
////                                        object.getString("User_id"),
////                                        object.getString("Img")
////                                ));
////                            } catch (JSONException e) {
////                                Log.e("abc", "onResponse: ", e);
////                            }
////                        }
////
////                        adapter = new GioHangAdapter(FragmentGiohang.this, R.layout.itemgiohang, arrayList);
////                        listView.setAdapter(adapter);
//                    }
//
//
//
//                },
//
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(getActivity(), "Bạn bị mất kết nối mạng !", Toast.LENGTH_SHORT).show();
//                    }
//                }
//        ) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<>();
//                params.put("user_id", userId);
//                Log.d("bbb", "getParams: " + userId);
//                return params;
//            }
//
//
//        };
//        requestQueue.add(jsonArrayRequest);
//    }

    private void GetData(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        int sum = 0;
                        arrayList.clear();
                        for (int i = 0; i < response.length(); i++) {

                            try {
                                JSONObject object = response.getJSONObject(i);
                                DecimalFormat formatter = new DecimalFormat("#,###,###");
                                String yourFormattedString = formatter.format(sum += object.getInt("Price_cart"));
                                tongcong.setText(yourFormattedString + " đ ");
                                arrayList.add(new ModelGioHang(
                                        object.getInt("Id_cart"),
                                        object.getString("Name_cart"),
                                        object.getInt("Price_cart"),
                                        object.getString("User_id"),
                                        object.getString("Img")
                                ));
                            } catch (JSONException e) {
                                Log.e("abc", "onResponse: ", e);
                            }
                        }

                        adapter = new GioHangAdapter(FragmentGiohang.this, R.layout.itemgiohang, arrayList);
                        listView.setAdapter(adapter);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), "Bạn bị mất kết nối mạng !", Toast.LENGTH_SHORT).show();
                    }
                }
        );


        requestQueue.add(jsonArrayRequest);
    }


}
