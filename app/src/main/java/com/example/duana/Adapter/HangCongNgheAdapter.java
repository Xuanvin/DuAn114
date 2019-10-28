package com.example.duana.Adapter;

import android.content.Context;
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
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duana.R;
import com.example.duana.mode.ModeHangCongNghe;


import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class HangCongNgheAdapter extends RecyclerView.Adapter<HangCongNgheAdapter.MyViewHolder> {
    private static Context context;
    private List<ModeHangCongNghe> hangCongNgheList;
    private int layout;

    public HangCongNgheAdapter(Context context, List<ModeHangCongNghe> hangCongNgheList, int layout) {
        this.context = context;
        this.hangCongNgheList = hangCongNgheList;
        this.layout = layout;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemgiamgia, null);
        MyViewHolder viewHolder = new MyViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final ModeHangCongNghe hangCongNghe = hangCongNgheList.get(position);
        new AsyncTaskLoadImage1(holder.img).execute(hangCongNghe.getImg1());
        holder.txt1.setText(hangCongNghe.getTxt1());
        holder.txt2.setText(hangCongNghe.getTxt2());
        holder.txt3.setText(hangCongNghe.getTxt3());
        holder.txt4.setText(hangCongNghe.getTxt4());
        holder.txt5.setText(hangCongNghe.getDanhgia1());
        holder.txt3.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
    }

    @Override
    public int getItemCount() {
        return hangCongNgheList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView txt1, txt2, txt3, txt4, txt5;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imgviewcongnghe);
            txt1 = itemView.findViewById(R.id.tensp);
            txt2 = itemView.findViewById(R.id.gia1);
            txt3 = itemView.findViewById(R.id.gia2);
            txt4 = itemView.findViewById(R.id.giathap);
            txt5 = itemView.findViewById(R.id.danhgia2);
            Animation animation;
            animation = AnimationUtils.loadAnimation(context,R.anim.slide_left);
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
