package com.example.duan.Fragment;


import android.content.Intent;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
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
import com.example.duan.ThongTin;
import com.example.duan.mode.MyAdapter;
import com.example.duan.R;
import com.example.duan.mode.Review;
import com.example.duan.mode.SanPham;
import com.example.duan.mode.SanPhamAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentHome extends Fragment {
    ExpandableHeightGridView gridView;
    RecyclerView recyclerView;
    ArrayList<SanPham> sanPhamArrayList;
    SanPhamAdapter sanPhamAdapter;
    SearchView searchView;
    String urlJson = "http://192.168.1.165:8080/duan/Sanpham.php";
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
        recyclerView = v.findViewById(R.id.recyeview);
//        txt=v.findViewById(R.id.txt);
        searchView = v.findViewById(R.id.seachbiew);

   gridView = v.findViewById(R.id.gridview);
        gridView.setExpanded(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        LinearLayoutManager horizontalLayoutManager
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManager);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        startActivity(new Intent(getActivity(), Main2Activity.class));
                }
            }
        });
gridView.setHorizontalScrollBarEnabled(true);
        Review itemsData[] = {new Review(R.drawable.dein, "Điện thoại"),
                new Review(R.drawable.laptop, "Laptop"),
                new Review(R.drawable.phu1, "Phụ kiện"),
                new Review(R.drawable.ho, "Đồng hồ "),
                new Review(R.drawable.ic_news, "aaa"),
                new Review(R.drawable.ic_thongtin, "aaa")};

        recyclerView.setItemAnimator(new DefaultItemAnimator());
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
        MyAdapter mAdapter = new MyAdapter(itemsData);
        recyclerView.setAdapter(mAdapter);
        sanPhamArrayList = new ArrayList<>();

        GetData(urlJson);


        return v;
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
                                        object.getString("HinhAnh"),
                                        object.getString("BinhLuan")

                                ));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        sanPhamAdapter = new SanPhamAdapter(getContext(), R.layout.itemsanpham, sanPhamArrayList);
                        gridView.setAdapter(sanPhamAdapter);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();
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
