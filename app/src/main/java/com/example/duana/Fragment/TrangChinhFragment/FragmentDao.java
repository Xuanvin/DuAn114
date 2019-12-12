package com.example.duana.Fragment.TrangChinhFragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;


import com.example.duana.Adapter.Vin.Adapter.LoaiSpAdapter;
import com.example.duana.Adapter.Vin.Adapter.LoaiSpResponse;
import com.example.duana.Adapter.Vin.Adapter.SanPhamAdapter;
import com.example.duana.Adapter.Vin.Adapter.SanPhamResponse;
import com.example.duana.MainChinh.MainActivity;
import com.example.duana.R;
import com.example.duana.Sanpham;
import com.example.duana.model.Loaisanpham;
import com.example.duana.model.Sanpham1;
import com.example.duana.model.remote.ApiService;
import com.example.duana.model.remote.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentDao extends Fragment {

    private ListView lvLoaiSp;
    private RecyclerView rvChitietLoaiSP;

    private List<Loaisanpham> loaiSPList;
    private LoaiSpAdapter loaiSpAdapter;

    private List<Sanpham1> sanphamList;
    private SanPhamAdapter sanPhamAdapter;

    private ApiService apiService;

    private String idLoaiSP = "1";

    private EditText edtTimkiem;
    private ImageView ivTimkiem;
    public final int NORMAL_MODE = 0;
    public final int SEACH_MODE = 1;
    private int currenMode;

    public FragmentDao() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fragment_dao, container, false);
        initView(v);
        initData();
        initEvent();
        return v;
    }

    private void initEvent() {

        lvLoaiSp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                idLoaiSP = loaiSPList.get(i).getIdLoaisp();
                loadSPtheoLoai(idLoaiSP);
            }
        });
        edtTimkiem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                timSanPham(edtTimkiem.getText().toString());
            }
        });

    }

    private void initData() {
        apiService = RetrofitClient.getClient(MainActivity.API_URL).create(ApiService.class);

        rvChitietLoaiSP.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        //list loại sản phẩm
        loadLoaiSP();

        //list tất cả các sản phẩm
        loadAllSP();

    }

    private void initView(View view) {
        lvLoaiSp = view.findViewById(R.id.lv_loaisp);
        rvChitietLoaiSP = view.findViewById(R.id.rv_chitietloaisp);
        edtTimkiem = view.findViewById(R.id.edt_timkiem);
        ivTimkiem = view.findViewById(R.id.iv_tiemkiem);
    }
    private void loadLoaiSP(){
        apiService.getLoaiSp().enqueue(new Callback<LoaiSpResponse>() {
            @Override
            public void onResponse(Call<LoaiSpResponse> call, Response<LoaiSpResponse> response) {
                LoaiSpResponse loaiSpResponse = response.body();
                loaiSPList = loaiSpResponse.getLoaisanpham();
                loaiSpAdapter = new LoaiSpAdapter(getActivity(),loaiSPList);
                lvLoaiSp.setAdapter(loaiSpAdapter);

            }

            @Override
            public void onFailure(Call<LoaiSpResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "Lỗi kết nối đến loại sản phẩm", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void loadSPtheoLoai(String idLoaiSP){
        apiService.getSpTheoloai(idLoaiSP).enqueue(new Callback<SanPhamResponse>() {
            @Override
            public void onResponse(Call<SanPhamResponse> call, Response<SanPhamResponse> response) {
                SanPhamResponse sanPhamResponse = response.body();
                sanphamList = sanPhamResponse.getSanpham();
                sanPhamAdapter = new SanPhamAdapter(getActivity(),sanphamList);
                rvChitietLoaiSP.setAdapter(sanPhamAdapter);
            }

            @Override
            public void onFailure(Call<SanPhamResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "Lỗi tải sản phẩm theo loại", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void loadAllSP() {
        apiService.getAllSanpham().enqueue(new Callback<SanPhamResponse>() {
            @Override
            public void onResponse(Call<SanPhamResponse> call, Response<SanPhamResponse> response) {
                SanPhamResponse sanPhamResponse = response.body();
                sanphamList = sanPhamResponse.getSanpham();
                sanPhamAdapter = new SanPhamAdapter(getActivity(),sanphamList);
                rvChitietLoaiSP.setAdapter(sanPhamAdapter);
            }

            @Override
            public void onFailure(Call<SanPhamResponse> call, Throwable t) {

            }
        });
    }

    private void timSanPham(String s) {
        if(s.trim().length()>0){
            //search
            //clear list

            ArrayList<Sanpham1> listSeach = new ArrayList<>();
            listSeach.clear();
            //search item
            for (int i = 0; i< sanphamList.size(); i++){
                //kiểm tra input có nằm trong danh sách không?
                if (sanphamList.get(i).getTensp().trim().toLowerCase().contains(s.trim().toLowerCase())){
                    listSeach.add(sanphamList.get(i));
                }
            }
            //change mode view
            currenMode = SEACH_MODE;
            //updet ui
            sanPhamAdapter = new SanPhamAdapter(getActivity(),listSeach);
            rvChitietLoaiSP.setAdapter(sanPhamAdapter);
        }else {
            //show normal listview
            //change mode view
            currenMode = NORMAL_MODE;

            sanPhamAdapter = new SanPhamAdapter(getActivity(),sanphamList);
            rvChitietLoaiSP.setAdapter(sanPhamAdapter);
        }
    }



}