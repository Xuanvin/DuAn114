package com.example.duana.Fragment;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;


import com.example.duana.Activity.UploadingImage;
import com.example.duana.Adapter.ViewPagerAdapter;


import androidx.fragment.app.Fragment;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.duana.R;

import static com.example.duana.Adapter.SanphamAdapter1.danhgia;
import static com.example.duana.Adapter.SanphamAdapter1.gia;
import static com.example.duana.Adapter.SanphamAdapter1.giamgia;
import static com.example.duana.Adapter.SanphamAdapter1.rating;
import static com.example.duana.Adapter.SanphamAdapter1.tenSp;


public class Chitiet extends Fragment {
    private static final int NUM_PAGES = 5;
    ViewPager viewPager;
    ImageView quaylai;
    TextView gtxtgia, thogtin1, danhgia1, datcauhoi,giamgiachitiet,danhgia2;
    RatingBar rating1,rating2;
    String TAG = "AAA";
    private PagerAdapter pagerAdapter;
    LinearLayout giaohang,dactinh,binhluan1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.activity_chitiet, container, false);
        viewPager = (ViewPager) v.findViewById(R.id.pager);
        giaohang = v.findViewById(R.id.giaohang);
        danhgia2=v.findViewById(R.id.danhgia2);
        danhgia2.setText(danhgia);
        rating2=v.findViewById(R.id.ratingbar2);
        rating2.setRating(rating);
        giamgiachitiet=v.findViewById(R.id.giamgiachitiet);
        giamgiachitiet.setText(giamgia);
        giamgiachitiet.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
          giaohang.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  BottomSheet bottomSheet=new BottomSheet();
                  bottomSheet.show(getFragmentManager(),"exampleBottomSheet");
              }
          });
          dactinh=v.findViewById(R.id.dactinh);
          dactinh.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  BottomSheetdienthoai bottomSheetdienthoai=new BottomSheetdienthoai();
                  bottomSheetdienthoai.show(getFragmentManager(),"exampleBottomSheet");
              }
          });
//        TextView abc = v.findViewById(R.id.abc);
        datcauhoi = v.findViewById(R.id.datcauhoi);
        datcauhoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), DatCauHoi.class));
            }
        });
        quaylai = v.findViewById(R.id.quaylai);
        gtxtgia = v.findViewById(R.id.txtgia);
        thogtin1 = v.findViewById(R.id.thongtin1);
        rating1 = v.findViewById(R.id.ratingbar1);

        danhgia1 = v.findViewById(R.id.danhgia);
        quaylai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getContext(), viewPager);
        danhgia1.setText(danhgia);
        gtxtgia.setText(gia);
        rating1.setRating(rating);
        thogtin1.setText(tenSp); // oke sai chổ mô rứa ko để ý

        viewPager.setAdapter(viewPagerAdapter);
        binhluan1=v.findViewById(R.id.binhluan1);
        binhluan1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), UploadingImage.class));
            }
        });
        return v;
    }


    @Override
    public void onStart() {

        super.onStart();
    }
//
//    private void GetData(String url) {
//        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
//                new Response.Listener<JSONArray>() {
//                    @Override
//                    public void onResponse(JSONArray response) {
//                        for (int i = 0; i < response.length(); i++) {
//                            try {
//                                JSONObject object = response.getJSONObject(i);
//
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                        }
//
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();
//                    }
//                }
//        );
//        requestQueue.add(jsonArrayRequest);
//    }
}
