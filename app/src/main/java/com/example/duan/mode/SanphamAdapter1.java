package com.example.duan.mode;

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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.duan.Main2Activity;
import com.example.duan.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class SanphamAdapter1 extends RecyclerView.Adapter<SanphamAdapter1.MyViewHolder> {

    public static String tenSp;
    public static String gia;
    public static String danhgia;
    public static String img1;
    public static String img2;
    public static String img3;
    public static float rating;


    private Context context;
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
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        final SanPham sanPham = sanPhamList.get(position);
        holder.tensp.setText(sanPham.getTenSP());
        holder.gia.setText(sanPham.getGia());
        holder.giaGiam.setText(sanPham.getGia());
        holder.giaGiam.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        holder.diaChi.setText(sanPham.getDiaChi());
        new SanphamAdapter1.AsyncTaskLoadImage1(holder.sanphamimg).execute(sanPham.getHinhAnh1());
        holder.ratingBar.setRating((float) sanPham.getRatingbar());
        holder.binhluan.setText(sanPham.getBinhluan());
        holder.chitiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                img1 = sanPhamList.get(position).getHinhAnh1();
//                img2 = sanPhamList.get(position).getHinhAnh2();
//                img3 = sanPhamList.get(position).getHinhAnh3();
//                tenSp = sanPhamList.get(position).getTenSP();
//                gia = sanPhamList.get(position).getGia();
//                danhgia = sanPhamList.get(position).getBinhluan();
//                rating = (float) sanPhamList.get(position).getRatingbar();
                Intent intent = new Intent(context, Main2Activity.class);
//                intent.putExtra("hinhanh1",sanPhamList.get(position).getHinhAnh1());
//                intent.putExtra("hinhanh2",sanPhamList.get(position).getHinhAnh2());
//                intent.putExtra("hinhanh3",sanPhamList.get(position).getHinhAnh3());
                intent.putExtra("tensanpham",sanPhamList.get(position).getTenSP()+"");
                intent.putExtra("Gia",sanPhamList.get(position).getGia()+"");
                intent.putExtra("binhluan",sanPhamList.get(position).getBinhluan()+"");
                intent.putExtra("ratiing",sanPhamList.get(position).getRatingbar()+"");
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
