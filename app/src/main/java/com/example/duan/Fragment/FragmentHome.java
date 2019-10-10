package com.example.duan.Fragment;


import android.content.Intent;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.duan.Activity.ExpandableHeightGridView;
import com.example.duan.Main2Activity;
import com.example.duan.OnDigList;
import com.example.duan.ThongTin;
import com.example.duan.mode.MyAdapter;
import com.example.duan.R;
import com.example.duan.mode.Review;
import com.example.duan.mode.SanPham;
import com.example.duan.mode.SanPhamAdapter;
import com.example.duan.mode.SanphamAdapter1;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.net.MalformedURLException;
import java.util.ArrayList;

import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import technolifestyle.com.imageslider.FlipperLayout;
import technolifestyle.com.imageslider.FlipperView;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentHome extends Fragment {
    // trong phạm vi package hay chi đó qên mẹ r
    RecyclerView gridView;
    RecyclerView recyclerView;
    ArrayList<SanPham> sanPhamArrayList;
    SanphamAdapter1 sanPhamAdapter;
    SearchView searchView;
    SwipeRefreshLayout swipeRefreshLayout;
    String urlJson = "http://172.15.25.246:8080/duan/Sanpham.php";
    TextView txt;

    public FragmentHome() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fragment_home, container, false);
//        final FlipperLayout flipperLayout = v.findViewById(R.id.fli);

//        txt=v.findViewById(R.id.txt);
        searchView = v.findViewById(R.id.seachbiew);
        swipeRefreshLayout = v.findViewById(R.id.swiperefresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                doYourUpdate();
            }
        });
        gridView = v.findViewById(R.id.gridview);


        gridView.setHorizontalScrollBarEnabled(true);
        Review itemsData[] = {
                new Review(R.drawable.dein, "Điện thoại"),
                new Review(R.drawable.laptop, "Laptop"),
                new Review(R.drawable.phu1, "Phụ kiện"),
                new Review(R.drawable.ho, "Đồng hồ "),
                new Review(R.drawable.ic_news, "aaa"),
                new Review(R.drawable.ic_thongtin, "aaa")};


        Toolbar toolbar = (Toolbar) v.findViewById(R.id.toolbar);
        //for crate home button
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);
//        String[] url = {"https://www.tampacific.vn/wp-content/uploads/2019/01/tat-ca-ma-giam-gia-accesstrade-cong-nghe-tampacific.png",
//                "https://cdn1.vienthonga.vn/image/2018/12/4/100000_05-12-vinid-rewwardsavt.jpg",
//                "https://cdn.tgdd.vn/Files/2017/08/10/1011941/1_760x367.jpg"
//        };
//        for (int i = 0; i < url.length; i++) {
//            FlipperView view = new FlipperView(getContext());
//            if (i == 0) {
//                view.setDescription("Mã Giảm Giá Công Nghệ")
//                        .setDescriptionBackgroundColor(Color.TRANSPARENT)
//                        .resetDescriptionTextView();
//            }
//            if (i == 1) {
//                view.setDescription("Ưu đãi giảm giá 3% với VinID Rewards")
//                        .setDescriptionTextColor(Color.BLACK)
//                        .resetDescriptionTextView();
//            }
//            if (i == 2) {
//                view.setDescription("Top 5 phụ kiện đang giảm giá đến 49%")
//                        .setDescriptionBackgroundColor(Color.TRANSPARENT)
//                        .resetDescriptionTextView();
//            }
//            flipperLayout.setCircleIndicatorHeight(150);
//            flipperLayout.setCircularIndicatorLayoutParams(5, 5);
//            flipperLayout.setCircleIndicatorWidth(300);
//            flipperLayout.removeCircleIndicator();
//            flipperLayout.showCircleIndicator();
//            view.setOnFlipperClickListener(new FlipperView.OnFlipperClickListener() {
//                @Override
//                public void onFlipperClick(@NotNull FlipperView flipperView) {
//
//                }
//            });
//            try {
//                view.setImage(url[i], new Function2<ImageView, Object, Unit>() {
//                    @Override
//                    public Unit invoke(ImageView imageView, Object image) {
//                        Picasso.get().load((String) image).into(imageView);
//                        return Unit.INSTANCE;
//                    }
//                });
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            }
//            flipperLayout.addFlipperView(view);
        MyAdapter mAdapter = new MyAdapter(getContext(),itemsData);
        mAdapter.setOnDigList(new OnDigList() {
            @Override
            public void onFondoClick(int position) {
                Toast.makeText(getActivity(), "â", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAccionClick(int position) {
               switch (position){
                   case 0:
                       startActivity(new Intent(getContext(),FragmentHome.class));
               }
            }
        });
        sanPhamArrayList = new ArrayList<>();

        GetData(urlJson);


        return v;
    }

    @Override
    public void onResume() {
        sanPhamArrayList = new ArrayList<>();
        super.onResume();
    }


    private void doYourUpdate() {
        // TODO implement a refresh
        swipeRefreshLayout.setRefreshing(false); // Disables the refresh icon
    }

    private void GetData(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject object = response.getJSONObject(i);
                                sanPhamArrayList.add(new SanPham(
                                        object.getInt("ID"),
                                        object.getString("TenSP"),
                                        object.getString("Gia"),
                                        object.getString("GiamGia"),
                                        object.getString("DiaChi"),
                                        object.getInt("Ratingbar"),
                                        object.getString("HinhAnh1"),
                                        object.getString("BinhLuan"),
                                        object.getString("HinhAnh2"),
                                        object.getString("HinhAnh3")


                                ));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        for (SanPham abc : sanPhamArrayList) {

                        }
                        sanPhamAdapter = new SanphamAdapter1(getContext(), R.layout.itemsanpham, sanPhamArrayList);
                        gridView.setLayoutManager(new GridLayoutManager(getContext(),2));
                        gridView.setAdapter(sanPhamAdapter);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu3cham, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.bttinnhan) {
            return true;
        }


        return super.onOptionsItemSelected(item);
    }
}
