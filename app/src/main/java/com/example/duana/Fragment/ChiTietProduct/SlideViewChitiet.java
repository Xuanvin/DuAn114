package com.example.duana.Fragment.ChiTietProduct;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;

import androidx.annotation.ColorInt;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.duana.Adapter.slideviewpag.ViewPagerAdapter1;
import com.example.duana.Fragment.BottomSheetDialog.BottomSheet;
import com.example.duana.Fragment.BottomSheetDialog.BottomSheetdienthoai;
import com.example.duana.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.example.duana.Adapter.DienThoaiAdapter.postion;
import static com.example.duana.Adapter.slideviewpag.ViewpagDienthoai.image;
import static com.example.duana.UserName.LoginApp.userId;

public class SlideViewChitiet extends Fragment implements View.OnClickListener {
    private TextView hang;
    ImageView imageView;
    private static final int COLOR_INACTIVE = Color.RED;
    private static final int COLOR_ACTIVE = Color.BLUE;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      View view=  inflater.inflate(R.layout.fragment_slide_view_chitiet, container, false);
            // anh xa
        hang = view.findViewById(R.id.hanghoa);
        ViewPager viewPager = view.findViewById(R.id.pager);
        TextView name = view.findViewById(R.id.nameslide);
        TextView price = view.findViewById(R.id.prices);
        imageView=view.findViewById(R.id.quaylai);
        TextView charatis = view.findViewById(R.id.charactise);
        LinearLayout giaohang = view.findViewById(R.id.giaohang);
        LinearLayout dactinh = view.findViewById(R.id.dactinh);
        LinearLayout binhluan1 = view.findViewById(R.id.binhluan1);
        // set text
        name.setText(postion.getName_Product());
        price.setText(""+postion.getPrice_product());
        charatis.setText(postion.getCharacteristics());
        giaohang.setOnClickListener(this);
        dactinh.setOnClickListener(this);
        binhluan1.setOnClickListener(this);
        imageView.setOnClickListener(this);
        ViewPagerAdapter1 viewPagerAdapter = new ViewPagerAdapter1(getContext(), viewPager);
        viewPager.setAdapter(viewPagerAdapter);
        final LinearLayout indicator = view.findViewById(R.id.indicator);
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
        String url = "http://sanphambanhang.000webhostapp.com/giohang.php";
        PostData(url);
        return view;
    }
    @Override
    public void onClick(View v) {
switch (v.getId()){
    case  R.id.quaylai:
        Objects.requireNonNull(getActivity()).finish();
        break;
    case  R.id.dactinh:
        BottomSheetdienthoai bottomSheetdienthoai = new BottomSheetdienthoai();
        assert getFragmentManager() != null;
        bottomSheetdienthoai.show(getFragmentManager(), "exampleBottomSheet");
        break;
    case R.id.giaohang:
        BottomSheet bottomSheet = new BottomSheet();
        assert getFragmentManager() != null;
        bottomSheet.show(getFragmentManager(), "exampleBottomSheet");
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
            public void onErrorResponse(VolleyError error) { // lau vc // log zo di t xem thu// chòe xíu, chừ lấy từng cái ra phải hân thì listview á
                Log.d("vvvv", "onErrorResponse: " + error); // mở phai php t xem thửu ok
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("user_id", String.valueOf(userId));
                return params;
            }
        };
        requestQueue.add(stringRequest); // cái ni để làm chi qên r


    }


}
