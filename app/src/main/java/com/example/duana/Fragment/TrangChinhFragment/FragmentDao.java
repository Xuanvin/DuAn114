package com.example.duana.Fragment.TrangChinhFragment;


import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;

import androidx.annotation.ColorInt;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.example.duana.Adapter.ViewPagerAdapter1;
import com.example.duana.Adapter.Vin.Adapter.MediaObject;
import com.example.duana.Adapter.Vin.Adapter.Resources;
import com.example.duana.Adapter.Vin.Adapter.VerticalSpacingItemDecorator;
import com.example.duana.Adapter.Vin.Adapter.VideoPlayerRecyclerAdapter;
import com.example.duana.Adapter.Vin.Adapter.VideoPlayerRecyclerView;
import com.example.duana.Adapter.slideviewpag.ViewPagerAdapter;
import com.example.duana.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

import static com.example.duana.Adapter.ViewPagerAdapter1.imagess;
import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentDao extends Fragment {
    ViewPager viewPager;
    private static final int COLOR_INACTIVE = Color.WHITE;
    private static final int COLOR_ACTIVE = Color.BLUE;
    private static final String TAG = "FragmentDao";

    private VideoPlayerRecyclerView mRecyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      View v=inflater.inflate(R.layout.fragment_fragment_dao, container, false);

        viewPager = v.findViewById(R.id.viewPager);
        mRecyclerView =v.findViewById(R.id.recycler_view);
        ViewPagerAdapter1 viewPagerAdapter = new ViewPagerAdapter1(getContext());

        viewPager.setAdapter(viewPagerAdapter);

        final LinearLayout indicator = v.findViewById(R.id.indicator);
        for (int i = 0; i < imagess.length; i++) {
            // COLOR_ACTIVE ứng với chấm ứng với vị trí hiện tại của ViewPager,
            // COLOR_INACTIVE ứng với các chấm còn lại
            // ViewPager có vị trí mặc định là 0, vì vậy color ở vị trí i == 0 sẽ là COLOR_ACTIVE
            View dot = createDot(indicator.getContext(), i == 0 ? COLOR_ACTIVE : COLOR_INACTIVE);
            indicator.addView(dot);


        }
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                for (int i = 0; i < imagess.length; i++) {
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


initRecyclerView();
initGlide();
    return v;
    }
    private void initRecyclerView(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        VerticalSpacingItemDecorator itemDecorator = new VerticalSpacingItemDecorator(10);
        mRecyclerView.addItemDecoration(itemDecorator);

        ArrayList<MediaObject> mediaObjects = new ArrayList<MediaObject>(Arrays.asList(Resources.MEDIA_OBJECTS));
        mRecyclerView.setMediaObjects(mediaObjects);
        VideoPlayerRecyclerAdapter adapter = new VideoPlayerRecyclerAdapter(mediaObjects, initGlide());
        mRecyclerView.setAdapter(adapter);
    }
    private RequestManager initGlide(){
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.white_background)
                .error(R.drawable.white_background);

        return Glide.with(this)
                .setDefaultRequestOptions(options);
    }

    @Override
    public void onDestroy() {
        if(mRecyclerView!=null)
            mRecyclerView.releasePlayer();
        super.onDestroy();
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
//    private void GetData(String url) {
//        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
//                new Response.Listener<JSONArray>() {
//                    @Override
//                    public void onResponse(JSONArray response) {
//                        sanPhamArrayList.clear();
//                        for (int i = 0; i < response.length(); i++) {
//                            try {
//                                JSONObject object = response.getJSONObject(i);
////                                sanPhamArrayList.add(new SanPham(
////                                        object.getInt("ID"),
////                                        object.getString("TenSP"),
////                                        object.getString("Gia"),
////                                        object.getString("GiamGia"),
////                                        object.getString("DiaChi"),
////                                        object.getString("HinhAnh"),
////                                        object.getString("Ratingbar")
////                                ));
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                        sanPhamAdapter.notifyDataSetChanged();
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
