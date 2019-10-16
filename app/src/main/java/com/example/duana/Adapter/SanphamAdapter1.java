package com.example.duana.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duana.Main2Activity;
import com.example.duana.R;
import com.example.duana.mode.SanPham;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class SanphamAdapter1 extends RecyclerView.Adapter<SanphamAdapter1.MyViewHolder> {

    public static String tenSp;
    public static String gia;
    public static String danhgia;
    public static String img1;
    public static String img2;
    public static String img3;
    public static float rating;


    private static Context context;
    private int layout;
    private List<SanPham> sanPhamList;

    public SanphamAdapter1(Context context, int layout, List<SanPham> sanPhamList) {
        this.context = context;
        this.layout = layout;
        this.sanPhamList = sanPhamList;


    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemsanpham, null);

        // create ViewHolder

        MyViewHolder viewHolder = new MyViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        final SanPham sanPham = sanPhamList.get(position);
        holder.tensp.setText(sanPham.getTenSP());
        holder.gia.setText(sanPham.getGia());
        holder.giaGiam.setText(sanPham.getGiaGiam());
        holder.giaGiam.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        holder.diaChi.setText(sanPham.getDiaChi());
        new SanphamAdapter1.AsyncTaskLoadImage1(holder.sanphamimg).execute(sanPham.getHinhAnh1());
        holder.ratingBar.setRating((float) sanPham.getRatingbar());
        holder.binhluan.setText(sanPham.getBinhluan());
        holder.chitiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Main2Activity.class);
                rating=sanPham.getRatingbar();
                tenSp=sanPham.getTenSP();
                gia=sanPham.getGia();
                danhgia=sanPham.getBinhluan();
                img1=sanPham.getHinhAnh1();
                img2=sanPham.getHinhAnh2();
                img3=sanPham.getHinhAnh3();


                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return sanPhamList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView sanphamimg;
        TextView tensp, gia, giaGiam, diaChi, binhluan;
        RatingBar ratingBar;
        LinearLayout chitiet;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            sanphamimg = itemView.findViewById(R.id.imgsanpham);
            tensp = itemView.findViewById(R.id.tensanpham);
            gia = itemView.findViewById(R.id.gia);
            giaGiam = itemView.findViewById(R.id.giamgia);
            diaChi = itemView.findViewById(R.id.diachi);
            binhluan = itemView.findViewById(R.id.binhluan);
            ratingBar = itemView.findViewById(R.id.ratingbar);
            chitiet = itemView.findViewById(R.id.linner);
            Animation animation;
            animation = AnimationUtils.loadAnimation(context,R.anim.listviewani);
            itemView.setAnimation(animation);
        }
    }

    public class AsyncTaskLoadImage1 extends AsyncTask<String, String, Bitmap> {
        private final static String TAG = "AsyncTaskLoadImage";
        private ImageView imageView;

        public AsyncTaskLoadImage1(ImageView imageView) {
            this.imageView = imageView;
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            Bitmap bitmap = null;
            try {
                URL url = new URL(params[0]);
                bitmap = BitmapFactory.decodeStream((InputStream) url.getContent());
            } catch (IOException e) {
                Log.e(TAG, e.getMessage());
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            imageView.setImageBitmap(bitmap);
        }
    }
}
