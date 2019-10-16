package com.example.duana.Adapter;

import android.annotation.SuppressLint;
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
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.example.duana.Main2Activity;
import com.example.duana.R;
import com.example.duana.mode.SanPham;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class SanPhamAdapter extends BaseAdapter {

    public static String tenSp;
    public static String gia;
    public static String danhgia;
    public static String img1;
    public static String img2;
    public static String img3;
    public  static float rating;

    private GridView gridView;
    private ViewPager viewPager;
    // đây ,, bắt sự kiện onclick thì luôn luôn bắt trong ADAPTER ngen, không bắt ngoài activity
    // vì :
    // lấy được cái position là vị trí tương ứng của mỗi item trong list/recycle/gridView vân vân ...


    private Context context;
    private int layout;
    String urlJson = "http://192.168.1.165/duan/Sanpham.php";
    private List<SanPham> sanPhamList;
    private ArrayList<SanPham> sanPhamArrayList;

    public SanPhamAdapter(Context context, int layout, List<SanPham> sanPhamList, GridView gridView) {
        this.context = context;
        this.layout = layout;
        this.sanPhamList = sanPhamList;
        this.sanPhamArrayList = new ArrayList<SanPham>();
        this.sanPhamArrayList.addAll(sanPhamList);
        this.gridView = gridView;
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
        TextView tensp, gia, giaGiam, diaChi, binhluan;
        RatingBar ratingBar;
    }

    @SuppressLint("ResourceType")
    @Override
    // cái int i nớ, là position
    public View getView(final int i, View view, ViewGroup viewGroup) {
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
            holder.binhluan = view.findViewById(R.id.binhluan);
            holder.ratingBar = view.findViewById(R.id.ratingbar);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        final SanPham sanPham = sanPhamList.get(i);
        holder.tensp.setText(sanPham.getTenSP());
        holder.gia.setText(sanPham.getGia());
        holder.giaGiam.setText(sanPham.getGia());
        holder.giaGiam.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        holder.diaChi.setText(sanPham.getDiaChi());
        new AsyncTaskLoadImage1(holder.sanphamimg).execute(sanPham.getHinhAnh1());
        holder.ratingBar.setRating((float) sanPham.getRatingbar());
        holder.binhluan.setText(sanPham.getBinhluan());
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                img1=sanPhamList.get(i).getHinhAnh1();
                img2=sanPhamList.get(i).getHinhAnh2();
                img3=sanPhamList.get(i).getHinhAnh3();
                tenSp = sanPhamList.get(i).getTenSP();
                gia = sanPhamList.get(i).getGia();
                danhgia=sanPhamList.get(i).getBinhluan();
                rating= (float) sanPhamList.get(i).getRatingbar();
                Intent intent = new Intent(context, Main2Activity.class);
                context.startActivity(intent);
            }
        });
        return view;
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
