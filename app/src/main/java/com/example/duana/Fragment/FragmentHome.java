package com.example.duana.Fragment;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.duana.Activity.DienThoai;
import com.example.duana.Activity.LAPTOP;
import com.example.duana.Activity.LAPTOPDELL;
import com.example.duana.Activity.TimKiem;
import com.example.duana.Adapter.SliderAdapterExample;
import com.example.duana.OnDigList;
import com.example.duana.Adapter.MyAdapter;
import com.example.duana.R;
import com.example.duana.ThongtinTk;
import com.example.duana.mode.Review;
import com.example.duana.mode.SanPham;
import com.example.duana.Adapter.SanphamAdapter1;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;
import java.util.Collections;

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
    String urlJson = "http://sanphambanhang.000webhostapp.com/Sanpham.php";
    TextView txt;
    LinearLayout laptop;
    SliderView sliderView;
    private Activity mActivity;
    LinearLayout dienthoai;
    ImageView imageView;
    CardView linearLayout;
    private static Context context;
    private ProgressDialog myProgress;

    public FragmentHome() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fragment_home, container, false);
//        final FlipperLayout flipperLayout = v.findViewById(R.id.fli);
        myProgress = new ProgressDialog(getContext());
        myProgress.setMessage("Đang tải.....");
        myProgress.setCancelable(true);
        myProgress.show();
        mActivity=getActivity();
        linearLayout=v.findViewById(R.id.laptopdellb);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), LAPTOPDELL.class));
            }
        });
        swipeRefreshLayout=v.findViewById(R.id.SwipeRefreshLayoutHome);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                      GetData(urlJson);
                        sanPhamArrayList.clear();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 2500);
            }
        });
        laptop = v.findViewById(R.id.Laptop);
        laptop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), LAPTOP.class));
            }
        });
//        txt=v.findViewById(R.id.txt);
        searchView = v.findViewById(R.id.seachbiew);
searchView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        startActivity(new Intent(getContext(), TimKiem.class));
    }
});
        gridView = v.findViewById(R.id.gridview);

        dienthoai = v.findViewById(R.id.dienthoai);
        dienthoai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), DienThoai.class));
            }
        });
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
        sliderView = v.findViewById(R.id.imageSlider);

        final SliderAdapterExample adapter = new SliderAdapterExample(getContext());

        sliderView.setSliderAdapter(adapter);

        sliderView.setIndicatorAnimation(IndicatorAnimations.WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(4); //set scroll delay in seconds :
        sliderView.startAutoCycle();
        sliderView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), ThongtinTk.class));
            }
        });

        sliderView.setOnIndicatorClickListener(new DrawController.ClickListener() {
            @Override
            public void onIndicatorClicked(int position) {
                sliderView.setCurrentPagePosition(position);
            }
        });
        MyAdapter mAdapter = new MyAdapter(getContext(), itemsData);
        mAdapter.setOnDigList(new OnDigList() {
            @Override
            public void onFondoClick(int position) {
                Toast.makeText(getActivity(), "â", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAccionClick(int position) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(getContext(), FragmentHome.class));
                }
            }
        });
        sanPhamArrayList = new ArrayList<>();

        GetData(urlJson);


        return v;
    }
    public static void restartActivity(Activity activity) {
        if (Build.VERSION.SDK_INT >= 11) {
            activity.recreate();
        } else {
            activity.finish();
            activity.startActivity(activity.getIntent());
        }
    }
    @Override
    public void onResume() {
        sanPhamArrayList = new ArrayList<>();
        super.onResume();
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
                                myProgress.dismiss();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        for (SanPham abc : sanPhamArrayList) {

                        }
                        sanPhamAdapter = new SanphamAdapter1(FragmentHome.this, R.layout.itemsanpham, sanPhamArrayList);
                        gridView.setLayoutManager(new GridLayoutManager(getContext(), 2));
                        gridView.setAdapter(sanPhamAdapter);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), "Bạn bị mất kết nối mạng !", Toast.LENGTH_SHORT).show();
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
