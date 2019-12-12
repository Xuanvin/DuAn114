package com.example.duana.Adapter.Vin.Adapter;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.duana.Activity.DIACHI;
import com.example.duana.R;
import com.example.duana.model.Interface.DemoInterface;
import com.example.duana.model.Sanpham1;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.example.duana.Adapter.SanphamAdapter1.Id_Category;
import static com.example.duana.Adapter.SanphamAdapter1.KhaNang;
import static com.example.duana.Adapter.SanphamAdapter1.gia;
import static com.example.duana.Adapter.SanphamAdapter1.img1;
import static com.example.duana.Adapter.SanphamAdapter1.tenSp;
import static com.example.duana.Adapter.Vin.Adapter.SanPhamAdapter.sanpham1;
import static com.example.duana.UserName.LoginApp.userId;

public class ThemGioHangVin extends BottomSheetDialogFragment  implements DemoInterface, View.OnClickListener {
    final String TAG = this.getClass().toString();
    private SharedPreferences preferences;
    private TextView txt1, thaydoilink, link, luutru;
    private String urString = "http://sanphambanhang.000webhostapp.com/Themgiohang.php";
    private String getUrString = "http://sanphambanhang.000webhostapp.com/idcart.php";
    private String urla = "http://sanphambanhang.000webhostapp.com/GetIdlogin.php";
    private String getUrla = "http://sanphambanhang.000webhostapp.com/updatethemlist.php";
    private ProgressDialog myProgress;
    public static TextView number;
    private String idcartt;
    private String diachi;
    private ImageView remove, adđ;
    private int count = 1;
    Sanpham1 sanPham ;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.themgiohangvin, container, false);
        sanPham = (Sanpham1) getActivity().getIntent().getSerializableExtra("abc");
        ImageView imageView = v.findViewById(R.id.imgthemgiohang);
        thaydoilink = v.findViewById(R.id.thaydoilink);
        preferences = getContext().getSharedPreferences("PrefsFile", Context.MODE_PRIVATE);
        txt1 = v.findViewById(R.id.giohanggia);
        TextView txt3 = v.findViewById(R.id.tenspgiohang);
        Button themgiohang = v.findViewById(R.id.Themgiohang);
        link = v.findViewById(R.id.link);
        number = v.findViewById(R.id.number);
        remove = v.findViewById(R.id.remove);
        luutru = v.findViewById(R.id.luutru);
        LinearLayout khanang = v.findViewById(R.id.khanang);
        adđ = v.findViewById(R.id.addd);


        /////
        diachi = "";
        adđ.setOnClickListener(this);
        remove.setOnClickListener(this);
        thaydoilink.setOnClickListener(this);
        PostData(getUrString);
        PostData1(urla, this);
        myProgress = new ProgressDialog(getContext());
        myProgress.setMessage("Đang tải.....");
        myProgress.setCancelable(true);
        txt1.setText("" + sanPham.getGiasp());
        txt3.setText(sanPham.getTensp());
        Picasso.get().load(sanPham.getHinhanhsp())
                .placeholder(R.drawable.loading)
                .error(R.drawable.no_image)
                .into(imageView);
        themgiohang.setOnClickListener(this);

        if (sanPham.getId_category() == 2) {
            khanang.setVisibility(View.VISIBLE);
            luutru.setText(sanPham.getData());


        } else {
            khanang.setVisibility(View.INVISIBLE);
        }
        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.thaydoilink:
                startActivity(new Intent(getContext(), DIACHI.class));
                break;
            case R.id.Themgiohang:
                Log.d(TAG, ": " + diachi);
                if (diachi == null || diachi.equals("")) {
                    startActivity(new Intent(getContext(), DIACHI.class));
                } else {
                    myProgress.show();
                    DangKi(urString);

                }
                break;
            case R.id.remove:
                if (count == 1) {
                    return;
                }
                count--;
                number.setText("" + count);

                break;
            case R.id.addd:
                count++;
                number.setText("" + count);

        }

    }
    private void DangKi(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(Objects.requireNonNull(getContext()));
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.trim().equals("success")) {
                            Toast.makeText(getContext(), "Thêm  thành Công", Toast.LENGTH_SHORT).show();
                            dismiss();

                        } else {
                            Toast.makeText(getContext(), "Vui  lòng  nhập đày đủ thông tin", Toast.LENGTH_SHORT).show();

                        }
                        myProgress.dismiss();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "loiox mangj", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("Id_cart", idcartt);
                params.put("Tongsp",number.getText().toString().trim());
                params.put("Name_cart",sanPham.getTensp());
                params.put("Price_cart", String.valueOf(sanPham.getGiasp()));
                params.put("Img", sanPham.getHinhanhsp());

                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
    private void PostData(final String url) {
        final RequestQueue requestQueue = Volley.newRequestQueue(Objects.requireNonNull(getContext()));
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(final String response) {
                idcartt = response;

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
                params.put("user_id", preferences.getString("userId",userId));
                return params;
            }
        };
        requestQueue.add(stringRequest); // cái ni để làm chi qên r


    }

    private void PostData1(final String url, final DemoInterface demoInterface) {
        final RequestQueue requestQueue = Volley.newRequestQueue(Objects.requireNonNull(getContext()));
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(final String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("giohang");
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject explrObject = jsonArray.getJSONObject(i);
                        String diachi = explrObject.getString("diachi") + explrObject.getString("tinh") + explrObject.getString("phuongxa") +
                                explrObject.getString("quanhuyen") + explrObject.getString("sdt");
                        demoInterface.chuyendoi(diachi);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


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

    @Override
    public void chuyendoi(String diaChi) {
        Log.d(TAG, ": " + diaChi);
        diachi = diaChi;
        link.setText(diachi);
    }
}
