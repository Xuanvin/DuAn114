package com.example.duana.Fragment.TrangChinhFragment;


import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;

import android.graphics.Color;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.duana.BoSuuTap.SmartWatch;
import com.example.duana.BoSuuTap.Xiaomi;
import com.example.duana.TrangChinh.DienThoaiTrangChinh;
import com.example.duana.TrangChinh.DongHo;
import com.example.duana.TrangChinh.LapTopTrangChinh;
import com.example.duana.BoSuuTap.LapTopDell;
import com.example.duana.BoSuuTap.LapTopGameMing;
import com.example.duana.Activity.MatMang;
import com.example.duana.Activity.TIMKIEM;
import com.example.duana.Adapter.SliderAdapterExample;
import com.example.duana.R;
import com.example.duana.TrangChinh.PhuKien;
import com.example.duana.UserName.ThongtinTk;

import com.example.duana.model.SanPham;
import com.example.duana.Adapter.SanphamAdapter1;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentHome extends Fragment implements View.OnClickListener {
    private RecyclerView gridView;
    private ArrayList<SanPham> sanPhamArrayList;
    private SanphamAdapter1 sanPhamAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private String urlJson = "http://sanphambanhang.000webhostapp.com/Sanpham.php";
    private SliderView sliderView;
    private ProgressDialog myProgress;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_fragment_home, container, false);

        //loading giữ liệu
        myProgress = new ProgressDialog(getContext());
        myProgress.setMessage("Đang tải.....");
        myProgress.setCancelable(true);
        myProgress.show();
        //ánh xạ
        ImageView imageView = v.findViewById(R.id.imagview);
        TextView giamdan=v.findViewById(R.id.giamdan);
        TextView searchView = v.findViewById(R.id.seachbiew);
        LinearLayout laptop = v.findViewById(R.id.Laptop);
        LinearLayout dienthoai = v.findViewById(R.id.dienthoai);
        LinearLayout dongh = v.findViewById(R.id.dongho);
        CardView linearLayout = v.findViewById(R.id.laptopdell);
        CardView xiaomi = v.findViewById(R.id.xiaomi);
        LinearLayout phukien = v.findViewById(R.id.phukien);
        CardView laptopgamemming = v.findViewById(R.id.laptopgamemming);
        CardView smartWatch = v.findViewById(R.id.smartWatch);
        ImageView imageView1=v.findViewById(R.id.buttonfa);
       // RecyclerView recyclerView = v.findViewById(R.id.smartWatch);
        gridView = v.findViewById(R.id.gridview);
        sliderView = v.findViewById(R.id.imageSlider);
        swipeRefreshLayout = v.findViewById(R.id.SwipeRefreshLayoutHome);
        //
        dienthoai.setOnClickListener(this);
        laptop.setOnClickListener(this);
        laptopgamemming.setOnClickListener(this);
        searchView.setOnClickListener(this);
        linearLayout.setOnClickListener(this);
        xiaomi.setOnClickListener(this);
        phukien.setOnClickListener(this);
        dongh.setOnClickListener(this);
        smartWatch.setOnClickListener(this);
        giamdan.setOnClickListener(this);
        imageView1.setOnClickListener(this);
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
//        Picasso.get().load("https://laz-img-cdn.alicdn.com/images/ims-web/TB1zRjdlYj1gK0jSZFuXXcrHpXa.jpg_1200x1200.jpg").into(imageView);


        gridView.setHorizontalScrollBarEnabled(true);
       // recyclerView.setHorizontalScrollBarEnabled(true);
//
//        Toolbar toolbar = (Toolbar) v.findViewById(R.id.toolbar);
//        //for crate home button
//        AppCompatActivity activity = (AppCompatActivity) getActivity();
//        activity.setSupportActionBar(toolbar);


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
        sanPhamArrayList = new ArrayList<>();
        GetData(urlJson);

//        Collections.sort(sanPhamArrayList, new Comparator<SanPham>() {
//            @Override
//            public int compare(SanPham o1, SanPham o2) {
//                return (o2.Name_Product.compareTo(o1.Name_Product));
//
//            }
//        });
        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dienthoai:
                startActivity(new Intent(getContext(), DienThoaiTrangChinh.class));
                break;
            case R.id.Laptop:
                startActivity(new Intent(getContext(), LapTopTrangChinh.class));
                break;
            case R.id.laptopgamemming:
                startActivity(new Intent(getContext(), LapTopGameMing.class));
                break;
            case R.id.seachbiew:
                startActivity(new Intent(getContext(), TIMKIEM.class));
                break;
            case R.id.laptopdell:
                startActivity(new Intent(getContext(), LapTopDell.class));
                break;
            case R.id.xiaomi:
                startActivity(new Intent(getContext(), Xiaomi.class));
                break;
            case R.id.phukien:
                startActivity(new Intent(getContext(), PhuKien.class));
                break;
            case R.id.dongho:
                startActivity(new Intent(getContext(), DongHo.class));
                break;
            case R.id.smartWatch:
                startActivity(new Intent(getContext(), SmartWatch.class));
                break;
            case R.id.buttonfa:
//                Fragment nvcart= Objects.requireNonNull(getActivity()).getSupportFragmentManager().findFragmentById(R.id.viewpager);
//                NavController navController= NavHostFragment.findNavController(nvcart);
//                navController.navigate(R.id.navigation_giohang);



        }
    }

    @Override
    public void onResume() {
        sanPhamArrayList = new ArrayList<>();
        super.onResume();
    }

    private void GetData(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(Objects.requireNonNull(getActivity()));
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {

                            try {
                                JSONObject object = response.getJSONObject(i);
                                sanPhamArrayList.add(new SanPham(
                                        object.getInt("Laptop_id"),
                                        object.getInt("Id_Category"),
                                        object.getString("khanangluutru"),
                                        object.getString("Name_Product"),
                                        object.getInt("Price_product"),
                                        object.getString("Characteristics"),
                                        object.getString("Name1_Information"),
                                        object.getInt("Ratingbar"),
                                        object.getString("Img1"),
                                        object.getString("Img2"),
                                        object.getString("Img3"),
                                        object.getString("Comment"),
                                        object.getString("Name1_Information"),
                                        object.getString("Name2_Information"),
                                        object.getString("Name3_Information"),
                                        object.getString("Name4_Information"),
                                        object.getString("Name5_Information"),
                                        object.getString("Information"),
                                        object.getString("ram"),
                                        object.getString("cpu"),
                                        object.getString("bonhotrong"),
                                        object.getString("dungluongpin")

                                ));

                                myProgress.dismiss();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        sanPhamAdapter = new SanphamAdapter1(getContext(), sanPhamArrayList);
                        gridView.setLayoutManager(new GridLayoutManager(getContext(), 2));
                        gridView.setAdapter(sanPhamAdapter);
                        Log.d("abv", "onResponse: " + sanPhamArrayList);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        myProgress.dismiss();
                        startActivity(new Intent(getActivity(), MatMang.class));
                        Objects.requireNonNull(getActivity()).finish();
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
        int id = item.getItemId();
        //noinspection
        if (id == R.id.bttinnhan) {
            return true;
        }


        return super.onOptionsItemSelected(item);
    }


}
