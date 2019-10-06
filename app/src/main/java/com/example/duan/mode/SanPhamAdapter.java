package com.example.duan.mode;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duan.Fragment.FragmentHome;
import com.example.duan.R;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class SanPhamAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    String urlJson = "http://192.168.1.165/duan/Sanpham.php";
    private List<SanPham> sanPhamList;
    private ArrayList<SanPham> sanPhamArrayList;

    public SanPhamAdapter(Context context, int layout, List<SanPham> sanPhamList) {
        this.context = context;
        this.layout = layout;
        this.sanPhamList = sanPhamList;
        this.sanPhamArrayList = new ArrayList<SanPham>();
        this.sanPhamArrayList.addAll(sanPhamList);
    }

    @Override
    public int getCount() {
        return sanPhamList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder {
        ImageView sanphamimg;
        TextView tensp, gia, giaGiam, diaChi,binhluan;
        RatingBar ratingBar;
    }

    @SuppressLint("ResourceType")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.itemsanpham, null);
            holder.sanphamimg = view.findViewById(R.id.imgsanpham);
            holder.tensp = view.findViewById(R.id.tensanpham);
            holder.gia = view.findViewById(R.id.gia);
            holder.giaGiam = view.findViewById(R.id.giamgia);
            holder.diaChi = view.findViewById(R.id.diachi);
            holder.binhluan=view.findViewById(R.id.binhluan);
            holder.ratingBar=view.findViewById(R.id.ratingbar);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        SanPham sanPham = sanPhamList.get(i);
        holder.tensp.setText(sanPham.getTenSP());
        holder.gia.setText(sanPham.getGia());
        holder.giaGiam.setText(sanPham.getGia());
        holder.giaGiam.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        holder.diaChi.setText(sanPham.getDiaChi());
       new  AsyncTaskLoadImage1(holder.sanphamimg).execute(sanPham.getHinhAnh());
        holder.ratingBar.setNumStars(sanPham.getRatingbar());
        holder.ratingBar.setStepSize((float) 5);
        holder.ratingBar.setMax(5);
        holder.ratingBar.setId(5);
        holder.ratingBar.setRating(5f);
        holder.binhluan.setText(sanPham.getBinhluan());
        return view;
    }


    public class AsyncTaskLoadImage1  extends AsyncTask<String, String, Bitmap> {
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
