package com.example.duana.Fragment.ChiTietProduct;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;

import com.example.duana.Adapter.Vin.Adapter.ThemGioHangVin;
import com.example.duana.Fragment.BottomSheetDialog.BottomSheetdienthoai;
import com.example.duana.MainChinh.MainActivity;
import com.example.duana.Sanpham;
import com.example.duana.model.Sanpham1;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duana.R;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Objects;

public class ChiTietSPActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView ivChitiet, btnThoat, btnGiohang, ivChitiet1, ivChitiet2, ivChitiet3;
    private TextView tvTenchitiet, tvGiachitiet, tvMotachitiet, tvMotachitiet1, tvMotachitiet2;
    private Spinner spinner;
    private TextView btnThemGioHang;

    int id = 0;
    String tenchitiet = "";
    int giachitiet = 0;
    String hinhanhchitiet = "";
    String motachitiet = "";
    int idloaisp = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_sp);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        // ánh xạ
        initView();
        getInfo();
//    catEventSpinner();
//    initEvient();
        btnGiohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(MainActivity.REQUEST_CODE_GIOHANG);
                finish();
            }
        });

    }

    private void initView() {

        ivChitiet = findViewById(R.id.iv_chitietsp);
        ivChitiet1 = findViewById(R.id.img1);
        ivChitiet2 = findViewById(R.id.img2);
        ivChitiet3 = findViewById(R.id.img3);
        tvTenchitiet = findViewById(R.id.tv_tenchitietsp);
        tvGiachitiet = findViewById(R.id.tv_giachitietsp);
        tvMotachitiet = findViewById(R.id.tv_motachitietsp);
        tvMotachitiet1 = findViewById(R.id.tv_motachitietsp1);
        tvMotachitiet2 = findViewById(R.id.tv_motachitietsp2);

//        spinner = findViewById(R.id.spinner);

        btnThemGioHang = findViewById(R.id.btn_datmua);
        btnGiohang = findViewById(R.id.btn_giohang);
        btnThoat = findViewById(R.id.btn_thoat);
        btnThemGioHang.setOnClickListener(this);
    }

    private void getInfo() {
        Sanpham1 sanPham = (Sanpham1) getIntent().getSerializableExtra("abc");
        id = Integer.parseInt(sanPham.getId());
        tenchitiet = sanPham.getTensp();
        giachitiet = Integer.parseInt(sanPham.getGiasp());
        hinhanhchitiet = sanPham.getHinhanhsp();
        motachitiet = sanPham.getMotasp();
        idloaisp = Integer.parseInt(sanPham.getIdLoaisp());

        tvTenchitiet.setText(tenchitiet);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        tvGiachitiet.setText(decimalFormat.format(giachitiet) + " Đ");
        tvMotachitiet.setText(motachitiet);
        new AsyncTaskLoadImage1(ivChitiet).execute(sanPham.getHinhanhsp());
        new AsyncTaskLoadImage1(ivChitiet1).execute(sanPham.getHinhanhsp1());
        new AsyncTaskLoadImage1(ivChitiet2).execute(sanPham.getHinhanhsp2());
        new AsyncTaskLoadImage1(ivChitiet3).execute(sanPham.getHinhanhsp());

        tvMotachitiet1.setText(sanPham.getMotasp1());
        tvMotachitiet2.setText(sanPham.getMotasp2());


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_datmua:
                ThemGioHangVin bottomSheetdienthoai = new ThemGioHangVin();
                assert getFragmentManager() != null;
                bottomSheetdienthoai.show(getSupportFragmentManager(), "exampleBottomSheet");
                break;
        }
    }

    @Override
    public void finish() {
        super.finish();
        setResult(RESULT_OK);
        overridePendingTransition(R.anim.left_in, R.anim.right_out);
    }

    @SuppressLint("StaticFieldLeak")
    public class AsyncTaskLoadImage1 extends AsyncTask<String, String, Bitmap> {
        private final static String TAG = "AsyncTaskLoadImage";
        private ImageView imageView;

        AsyncTaskLoadImage1(ImageView imageView) {
            this.imageView = imageView;
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            Bitmap bitmap = null;
            try {
                URL url = new URL(params[0]);
                bitmap = BitmapFactory.decodeStream((InputStream) url.getContent());
            } catch (IOException e) {
                Log.e(TAG, Objects.requireNonNull(e.getMessage()));
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            imageView.setImageBitmap(bitmap);
        }
    }

}
