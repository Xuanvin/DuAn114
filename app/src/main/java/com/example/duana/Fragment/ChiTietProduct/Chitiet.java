package com.example.duana.Fragment.ChiTietProduct;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.duana.Activity.UploadingImage;
import com.example.duana.Adapter.slideviewpag.ViewPagerAdapter;


import androidx.annotation.ColorInt;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duana.Fragment.BottomSheetDialog.BottomSheet;
import com.example.duana.Fragment.BottomSheetDialog.BottomSheetThongTinLaptop;
import com.example.duana.Fragment.BottomSheetDialog.BottomSheetThongtinPhone;
import com.example.duana.Fragment.BottomSheetDialog.BottomSheetdienthoai;
import com.example.duana.Fragment.DatCauHoi;
import com.example.duana.MainChinh.MainActivity;
import com.example.duana.R;
import com.example.duana.VietDanhGia.DanhGia;
import com.example.duana.model.SanPham;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.example.duana.Adapter.SanphamAdapter1.Id_Category;
import static com.example.duana.Adapter.SanphamAdapter1.Information;

import static com.example.duana.Adapter.SanphamAdapter1.danhgia;
import static com.example.duana.Adapter.SanphamAdapter1.gia;
import static com.example.duana.Adapter.SanphamAdapter1.giamgia;

import static com.example.duana.Adapter.SanphamAdapter1.img1;
import static com.example.duana.Adapter.SanphamAdapter1.rating;
import static com.example.duana.Adapter.SanphamAdapter1.tenSp;
import static com.example.duana.Adapter.slideviewpag.ViewpagDienthoai.image;
import static com.example.duana.UserName.LoginApp.userId;


public class Chitiet extends Fragment implements View.OnClickListener {
    public static TextView gtxtgia, thogtin1, danhgia1, datcauhoi, giamgiachitiet, danhgia2, textthongtin, hang;
    private ArrayList<SanPham> arrayList;
    private SharedPreferences preferences;
    private static final int COLOR_INACTIVE = Color.WHITE;
    private static final int COLOR_ACTIVE = Color.BLUE;
    Button vietdanhgia;

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        preferences = Objects.requireNonNull(getContext()).getSharedPreferences("PrefsFile", Context.MODE_PRIVATE);
        View v = inflater.inflate(R.layout.activity_chitiet, container, false);
        arrayList = new ArrayList<>();
        //*************************************************** ánh xạ
        ViewPager viewPager = v.findViewById(R.id.pager);
        LinearLayout giaohang = v.findViewById(R.id.giaohang);
        danhgia2 = v.findViewById(R.id.danhgia2);
        CardView thongtinsp=v.findViewById(R.id.thongtinsp);
        textthongtin = v.findViewById(R.id.textthongtin);
        ImageView giohanga=v.findViewById(R.id.giohangaa);
        ImageView quaylai = v.findViewById(R.id.quaylai);
        gtxtgia = v.findViewById(R.id.pricechitiet);
        thogtin1 = v.findViewById(R.id.charactisechitiet);
        RatingBar rating1 = v.findViewById(R.id.ratingbar1);
        LinearLayout binhluan1 = v.findViewById(R.id.binhluan1);
        danhgia1 = v.findViewById(R.id.danhgia);
        datcauhoi = v.findViewById(R.id.datcauhoi);
        LinearLayout dactinh = v.findViewById(R.id.dactinh);
        RatingBar rating2 = v.findViewById(R.id.ratingbar2);
        giamgiachitiet = v.findViewById(R.id.giamgiachitiet);
        hang = v.findViewById(R.id.hanghoa);
        vietdanhgia=v.findViewById(R.id.vietdahgia);
        // **************************************************************
        String url = "http://sanphambanhang.000webhostapp.com/giohang.php";
        PostData(url);
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        giaohang.setOnClickListener(this);
        dactinh.setOnClickListener(this);
        datcauhoi.setOnClickListener(this);
        binhluan1.setOnClickListener(this);
        quaylai.setOnClickListener(this);
        thongtinsp.setOnClickListener(this);
        vietdanhgia.setOnClickListener(this);
        giohanga.setOnClickListener(this);
        //************************************************* settext sản phầm
        danhgia1.setText(danhgia);
        rating1.setRating(rating);
        thogtin1.setText(tenSp);
        giamgiachitiet.setText(giamgia);
        textthongtin.setText(Information);
        danhgia2.setText(danhgia);
        rating2.setRating(rating);
        DecimalFormat formatter = new DecimalFormat("#,###,###");
        String yourFormattedString = formatter.format(gia);
        gtxtgia.setText(yourFormattedString + " đ ");
//******************************************************* điện thoại
//        thogtin1.setText(Name_dienthoai);
//        giamgiachitiet.setText(Characteristics);
//        gtxtgia.setText(Price_dienthoai);
//        ViewpagDienthoai viewpagDienthoai = new ViewpagDienthoai(getContext(), viewPager);
//        viewPager.setAdapter(viewpagDienthoai);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getContext(), viewPager);
        viewPager.setAdapter(viewPagerAdapter);
        final LinearLayout indicator = v.findViewById(R.id.indicator);
        for (int i = 0; i < image.length; i++) {
            // COLOR_ACTIVE ứng với chấm ứng với vị trí hiện tại của ViewPager,
            // COLOR_INACTIVE ứng với các chấm còn lại
            // ViewPager có vị trí mặc định là 0, vì vậy color ở vị trí i == 0 sẽ là COLOR_ACTIVE
            View dot = createDot(indicator.getContext(), i == 0 ? COLOR_ACTIVE : COLOR_INACTIVE);
            indicator.addView(dot);


        }
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                for (int i = 0; i < image.length; i++) {
                    // Duyệt qua từng "chấm" trong indicator
                    // Nếu i == position, tức i đang là vị trí hiện tại của ViewPager,
                    // ta sẽ đổi màu "chấm" thành COLOR_ACTIVE, nếu không
                    // thì sẽ đổi thành màu COLOR_INACTIVE
                    indicator.getChildAt(i).getBackground().mutate().setTint(i == position ? COLOR_ACTIVE : COLOR_INACTIVE);

                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        return v;
    }
    @Override
    public void onClick(View v) {
switch (v.getId()){
    case  R.id.giohang:
        BottomSheet bottomSheet = new BottomSheet();
        assert getFragmentManager() != null;
        bottomSheet.show(getFragmentManager(), "exampleBottomSheet");
    break;
    case R.id.dactinh:
        BottomSheetdienthoai bottomSheetdienthoai = new BottomSheetdienthoai();
        assert getFragmentManager() != null;
        bottomSheetdienthoai.show(getFragmentManager(), "exampleBottomSheet");
        break;
    case R.id.datcauhoi:
        startActivity(new Intent(getContext(), DatCauHoi.class));
        break;
    case R.id.binhluan1:
        startActivity(new Intent(getContext(), UploadingImage.class));
        break;
    case R.id.quaylai:
        Objects.requireNonNull(getActivity()).onBackPressed();
        break;
    case R.id.thongtinsp:
        if (Id_Category==2){
            BottomSheetThongtinPhone bottomSheetThongtinPhone = new BottomSheetThongtinPhone();
            assert getFragmentManager() != null;
            bottomSheetThongtinPhone.show(getFragmentManager(), "exampleBottomSheet");
        }else if (Id_Category==1){
            BottomSheetThongTinLaptop bottomSheetThongTinLaptop = new BottomSheetThongTinLaptop();
            assert getFragmentManager() != null;
            bottomSheetThongTinLaptop.show(getFragmentManager(), "exampleBottomSheet");
        }
        break;
    case R.id.vietdahgia:
        startActivity(new Intent(getContext(), DanhGia.class));
        break;
    case R.id.giohangaa:
        Objects.requireNonNull(getActivity()).setResult(MainActivity.REQUEST_CODE_GIOHANG);
        getActivity().finish();
        break;

}
    }
    private View createDot(Context context, @ColorInt int color) {
        View dot = new View(context);
        LinearLayout.MarginLayoutParams dotParams = new LinearLayout.MarginLayoutParams(20, 20);
        dotParams.setMargins(20, 10, 20, 10);
        dot.setLayoutParams(dotParams);
        ShapeDrawable drawable = new ShapeDrawable(new OvalShape());
        drawable.setTint(color);
        dot.setBackground(drawable);
        return dot;
    }

    @Override
    public void onStart() {

        super.onStart();
    }

    private void PostData(final String url) {

        final RequestQueue requestQueue = Volley.newRequestQueue(Objects.requireNonNull(getContext()));
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(final String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("giohang");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        hang.setText(""+jsonArray.length());

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("vvvv", "onErrorResponse: " + error); // mở phai php t xem thử ok
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

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (activity instanceof MainActivity) {
            //here is your code
        } else {

        }
    }

}
