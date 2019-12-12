package com.example.duana.Fragment.TrangChinhFragment;


import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.duana.ThuTucThanhToan.KiemTra;
import com.example.duana.Adapter.AdapterGioHang;
import com.example.duana.MainChinh.MainActivity;
import com.example.duana.R;
import com.example.duana.model.ModelGioHang;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.example.duana.Adapter.AdapterGioHang.gioHang1;
import static com.example.duana.UserName.LoginApp.userId;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentGiohang extends Fragment {
    private ArrayList<ModelGioHang> arrayList;
    private AdapterGioHang adapter;
    public static RecyclerView listView;
    private SharedPreferences preferences;
    @SuppressLint("StaticFieldLeak")
    public static LinearLayout tienhanhthanhtoan, bottom_navigation, giohang,giohanga;
    @SuppressLint("StaticFieldLeak")
    public static TextView phivanchuyen, tongcong;
    private String url = "http://sanphambanhang.000webhostapp.com/giohang.php";
    private String xoa = "http://sanphambanhang.000webhostapp.com/delete.php";
    public static int phivc;
    public static int idcartdetell;
    public  static  int idcart;
    private Button tieptucmuasap;
    private ProgressDialog myProgress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        preferences =getContext().getSharedPreferences("PrefsFile", Context.MODE_PRIVATE);
        View v = inflater.inflate(R.layout.fragment_fragment_giohang, container, false);
        myProgress = new ProgressDialog(getContext());
        myProgress.setMessage("Đang tải.....");
        myProgress.setCancelable(true);
        myProgress.show();
        // =================================== ánh xạ
        listView = v.findViewById(R.id.listviewGioHang);
        phivanchuyen = v.findViewById(R.id.phivc);
        tongcong = v.findViewById(R.id.tongcong);
        tienhanhthanhtoan = v.findViewById(R.id.addthem);
        giohang = v.findViewById(R.id.giohang);
        tieptucmuasap=v.findViewById(R.id.tieptucmuasap);
        bottom_navigation = v.findViewById(R.id.bottom_navigation);
        giohanga=v.findViewById(R.id.giohanga);
        //=======================================
        tienhanhthanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), KiemTra.class));
            }
        });
        tieptucmuasap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), MainActivity.class));
                Objects.requireNonNull(getActivity()).onBackPressed();
            }
        });
        phivc = Integer.parseInt(phivanchuyen.getText().toString().trim());
        arrayList = new ArrayList<>();



            PostData(url);




        return v;
//        GetData(url);
    }

    public void Xoa(final int id) {
        RequestQueue requestQueue = Volley.newRequestQueue(Objects.requireNonNull(getContext()));
        StringRequest stringRequest = new StringRequest(Request.Method.POST, xoa,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.trim().equals("success")) {
                            Toast.makeText(getContext(), "Xóa Thành công", Toast.LENGTH_SHORT).show();
                            PostData(url);
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
            protected Map<String, String> getParams() {
                Map<String, String> parm = new HashMap<>();
                parm.put("ero", String.valueOf(id));

                return parm;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void PostData(final String url) {
        giohang.setVisibility(View.VISIBLE);
        myProgress.dismiss();
        tongcong.setText("0 đ");
        phivanchuyen.setText("0 đ");
        bottom_navigation.setVisibility(View.GONE);
        final RequestQueue requestQueue = Volley.newRequestQueue(Objects.requireNonNull(getContext()));
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(final String response) {
                int sum = 0;
                int ship = 11000;
                arrayList.clear();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("giohang");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject explrObject = jsonArray.getJSONObject(i);
                        // sum1=explrObject.getInt("Price_cart")* explrObject.getInt("Tongsp");
                        DecimalFormat formatter = new DecimalFormat("#,###,###");
                        String yourFormattedString1 = formatter.format(phivc = ship * jsonArray.length());
                        String yourFormattedString = formatter.format(((sum += explrObject.getInt("Price_cart") * explrObject.getInt("Tongsp"))) + (ship * jsonArray.length()));
                        phivanchuyen.setText(yourFormattedString1 + "  đ");
                        tongcong.setText(yourFormattedString + " đ ");
                        bottom_navigation.setVisibility(View.VISIBLE);
                        giohang.setVisibility(View.GONE);
                        arrayList.add(new ModelGioHang(
                                explrObject.getInt("Id_detell"),
                                explrObject.getInt("Id_cart"),
                                explrObject.getInt("Tongsp"),
                                explrObject.getString("Name_cart"),
                                explrObject.getInt("Price_cart"),
                                explrObject.getString("User_id"),
                                explrObject.getString("Img")
                        ));
                        idcartdetell = explrObject.getInt("Id_detell");
                        idcart=explrObject.getInt("Id_cart");
                        myProgress.dismiss();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                adapter = new AdapterGioHang(FragmentGiohang.this, arrayList);
                listView.setLayoutManager(new GridLayoutManager(getContext(), 1));
                listView.setAdapter(adapter);
                ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                        return false;

                    }

                    @Override
                    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                        int position = viewHolder.getAdapterPosition();
                        arrayList.remove(position);
                        adapter.XacNhanxoa1(gioHang1.getTenSP(), gioHang1.getId_detell());
                        adapter.notifyDataSetChanged();
                    }
                });
                helper.attachToRecyclerView(listView);

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
                params.put("user_id",preferences.getString("userId",userId));
                return params;
            }
        };
        requestQueue.add(stringRequest); // cái ni để làm chi qên r


    }


}
