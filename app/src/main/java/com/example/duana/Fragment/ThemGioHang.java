package com.example.duana.Fragment;

import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.duana.Main2Activity;
import com.example.duana.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

import static com.example.duana.Adapter.SanphamAdapter1.gia;
import static com.example.duana.Adapter.SanphamAdapter1.giamgia;
import static com.example.duana.Adapter.SanphamAdapter1.img1;
import static com.example.duana.Adapter.SanphamAdapter1.tenSp;

public class ThemGioHang extends BottomSheetDialogFragment {
    ImageView imageView;
    TextView txt1, txt2, txt3;
    String urString = "http://sanphambanhang.000webhostapp.com/Themgiohang.php";
    String giagiohang1, giamgiagiohang1, tenspgiohang;
    Button themgiohang;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.themgiohang, container, false);
        imageView = v.findViewById(R.id.imgthemgiohang);
        Picasso.get().load(img1).into(imageView);
        txt1 = v.findViewById(R.id.giohanggia);
        txt2 = v.findViewById(R.id.giamgiagohang);
        txt3 = v.findViewById(R.id.tenspgiohang);
        txt1.setText(gia);
        txt2.setText(giamgia);
        txt2.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        txt3.setText(tenSp);
        themgiohang = v.findViewById(R.id.Themgiohang);
        themgiohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DangKi(urString);
            }
        });
        return v;
    }

    private void DangKi(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.trim().equals("success")) {
                            Toast.makeText(getContext(), "Thêm  thành Công", Toast.LENGTH_SHORT).show();
                               dismiss();
                        } else if (tenSp.toString().equals("") && gia.toString().equals("") &&giamgia.equals("")&&  img1.equals("")) {
                            Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                            dismiss();
                        } else {
                            Toast.makeText(getContext(), "Vui  lòng  nhập đày đủ thông tin", Toast.LENGTH_SHORT).show();

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "loiox mangj", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("TenSP", tenSp);
                params.put("GiaSP", gia);
                params.put("GiaSP", giamgia);
                params.put("Img1", img1);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}
