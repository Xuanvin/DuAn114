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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duana.MainChinh.Main2Activity;
import com.example.duana.MainChinh.Main3Activity;
import com.example.duana.R;
import com.example.duana.model.SanPham;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Objects;

public class DienThoaiAdapter extends RecyclerView.Adapter<DienThoaiAdapter.MyViewHolder> {
    @SuppressLint("StaticFieldLeak")
    private static Context context;
    public static List<SanPham> dienThoais;
    private int layout;
    public static String Name_dienthoai;
    public static int Price_dienthoai;
    public static String Characteristics;
    public static String Img_dienthoai;
    public static String Img2_dienthoai;
    public static String Img3_dienthoai;
    public static SanPham postion;

    public DienThoaiAdapter(Context context, List<SanPham> dienThoais, int layout) {
        DienThoaiAdapter.context = context;
        DienThoaiAdapter.dienThoais = dienThoais;
        this.layout = layout;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        @SuppressLint("InflateParams") View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemslide, null);
        return new MyViewHolder(itemLayoutView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        final SanPham dienThoai = dienThoais.get(position);
        new AsyncTaskLoadImage1(holder.img).execute(dienThoai.getImg1());
        holder.txt1.setText(dienThoai.getName_Product());
        holder.txt3.setText(dienThoai.getCharacteristics());
        DecimalFormat formatter = new DecimalFormat("#,###,###");
        String yourFormattedString = formatter.format(dienThoai.getPrice_product());
        holder.txt2.setText(yourFormattedString + " đ ");
        holder.giohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Main3Activity.class);
                Name_dienthoai=dienThoai.Name_Product;
                Price_dienthoai=dienThoai.Price_product;
                Characteristics=dienThoai.Characteristics;
                Img_dienthoai=dienThoai.Img1;
                Img2_dienthoai=dienThoai.Img2;
                Img3_dienthoai=dienThoai.Img3;
                postion=dienThoais.get(position);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return dienThoais.size();
    }

    public int getLayout() {
        return layout;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView txt1, txt2, txt3;

        ImageView giohang;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imgslide);
            txt1 = itemView.findViewById(R.id.nameslide);
            txt2 = itemView.findViewById(R.id.priceslide);
            txt3 = itemView.findViewById(R.id.charactise);
            giohang = itemView.findViewById(R.id.imgslidegiohang);
            Animation animation;
            animation = AnimationUtils.loadAnimation(context, R.anim.scase);
            itemView.setAnimation(animation);
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
