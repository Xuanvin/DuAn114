package com.example.duana.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duana.MainChinh.Main2Activity;
import com.example.duana.R;
import com.example.duana.model.SanPham;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Objects;

public class SanphamAdapter1 extends RecyclerView.Adapter<SanphamAdapter1.MyViewHolder> {

    public static String tenSp;
    public static int gia;
    public static String giamgia;
    public static String danhgia;
    public static String img1;
    public static String img2;
    public static String img3;
    public static float rating;
    public static String Name1_Information;
    public static String Name2_Information;
    public static String Information;
    public static String KhaNang;
    public static int Id_Category;
    public static SanPham pos;
    @SuppressLint("StaticFieldLeak")
    public static Context context;
    private List<SanPham> sanPhamList;

    public SanphamAdapter1(Context context, List<SanPham> sanPhamList) {
        SanphamAdapter1.context = context;
        this.sanPhamList = sanPhamList;


    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        @SuppressLint("InflateParams") View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemsanpham, null);

        // create ViewHolder

        return new MyViewHolder(itemLayoutView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        final SanPham sanPham = sanPhamList.get(position);
        holder.tensp.setText(sanPham.getName_Product());
//        holder.gia.setText(sanPham.getPrice_product());
        holder.giaGiam.setText(sanPham.getCharacteristics());
        holder.diaChi.setText(sanPham.getDiaChi());
        new SanphamAdapter1.AsyncTaskLoadImage1(holder.sanphamimg).execute(sanPham.getImg1());
        holder.ratingBar.setRating(sanPham.getRatingbar());
        holder.binhluan.setText(sanPham.getComment());
        DecimalFormat formatter = new DecimalFormat("#,###,###");
        String yourFormattedString = formatter.format(sanPham.getPrice_product());
        holder.gia.setText(yourFormattedString + " đ ");
        holder.chitiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context.getApplicationContext(), Main2Activity.class);
                rating = sanPham.getRatingbar();
                tenSp = sanPham.getName_Product();
                gia = sanPham.getPrice_product();
                danhgia = sanPham.getComment();
                img1 = sanPham.getImg1();
                img2 = sanPham.getImg2();
                img3 = sanPham.getImg3();
                giamgia = sanPham.getCharacteristics();
                Name1_Information = sanPham.getName1_information();
                Name2_Information = sanPham.getName2_information();
                Information = sanPham.getInformation();
                KhaNang = sanPham.getKhaNangLuuTru();
                Id_Category = sanPham.getId_Category();
                pos = sanPhamList.get(position);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return sanPhamList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView sanphamimg;
        TextView tensp, gia, giaGiam, diaChi, binhluan;
        RatingBar ratingBar;
        LinearLayout chitiet;


        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            sanphamimg = itemView.findViewById(R.id.imgsanpham);
            tensp = itemView.findViewById(R.id.tensanpham);
            gia = itemView.findViewById(R.id.gia);
            giaGiam = itemView.findViewById(R.id.giamgia);
            diaChi = itemView.findViewById(R.id.diachi);
            binhluan = itemView.findViewById(R.id.binhluan);
            ratingBar = itemView.findViewById(R.id.ratingbar);
            chitiet = itemView.findViewById(R.id.linner);
//            Animation animation;
//            animation = AnimationUtils.loadAnimation(context, R.anim.listviewani);
//            itemView.setAnimation(animation);
        }
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
