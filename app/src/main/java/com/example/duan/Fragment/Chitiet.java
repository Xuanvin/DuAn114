package com.example.duan.Fragment;

import android.content.Intent;
import android.os.Bundle;


import com.example.duan.ViewPagerAdapter;


import androidx.fragment.app.Fragment;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.duan.R;


public class Chitiet extends Fragment {
    private static final int NUM_PAGES = 5;
    ViewPager viewPager;
    ImageView quaylai;
    TextView gtxtgia, thogtin1, danhgia1, datcauhoi;
    RatingBar rating1;
    String TAG = "AAA";
    private PagerAdapter pagerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.activity_chitiet, container, false);
        viewPager = (ViewPager) v.findViewById(R.id.pager);

//        TextView abc = v.findViewById(R.id.abc);
        datcauhoi = v.findViewById(R.id.datcauhoi);
        datcauhoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(),DatCauHoi.class));
            }
        });
        quaylai = v.findViewById(R.id.quaylai);
        gtxtgia = v.findViewById(R.id.txtgia);
        thogtin1 = v.findViewById(R.id.thongtin1);
        rating1 = v.findViewById(R.id.ratingbar1);

        danhgia1 = v.findViewById(R.id.danhgia);
        Intent intent = getActivity().getIntent();
        String hinhanh1 = intent.getExtras().getString("hinhanh1");
        String hinhanh2 = intent.getExtras().getString("hinhanh1");
        String hinhanh3 = intent.getExtras().getString("hinhanh1");
        String tensanpham = intent.getExtras().getString("tensanpham");
        String gia1 = intent.getExtras().getString("Gia");
        String binhluan = intent.getExtras().getString("binhluan");
        int rating12 = intent.getExtras().getInt("ratiing");

        quaylai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getContext(), viewPager);
        danhgia1.setText(binhluan);
        rating1.setNumStars((int) rating12);
        gtxtgia.setText(gia1);
        rating1.setStepSize((float) 5);
        rating1.setMax(5);
        rating1.setId((int) 0.1);
        rating1.setRating(5f);
        thogtin1.setText(tensanpham); // oke sai chổ mô rứa ko để ý
//        abc.setText(tenSp);
//        Log.d(TAG, "tenSp: " + tenSp);
        viewPager.setAdapter(viewPagerAdapter);
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
