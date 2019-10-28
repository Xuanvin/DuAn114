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
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duana.R;
import com.example.duana.mode.ModeLaptop;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Collections;
import java.util.List;

public class LaptopAdapter extends RecyclerView.Adapter<LaptopAdapter.MyViewHolder> {
    private static Context context;
    private List<ModeLaptop> LaModeLaptops= Collections.emptyList();
    private int layout;

    public LaptopAdapter(Context context, List<ModeLaptop> LaModeLaptops, int layout) {
        this.context = context;
        this.LaModeLaptops = LaModeLaptops;
        this.layout = layout;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.iteamphone, null);
        MyViewHolder viewHolder = new MyViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
final  ModeLaptop laptop=LaModeLaptops.get(position);
        new AsyncTaskLoadImage1(holder.img).execute(laptop.getImg());
        holder.txt1.setText(laptop.getTenSp());
        holder.txt2.setText(laptop.getGiasp());
        holder.txt3.setText(laptop.getGiamgiasp());
        holder.txt4.setText(laptop.getKhuyenmaisp());
        holder.txt4.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
    }

    @Override
    public int getItemCount() {
        return LaModeLaptops.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView img;
        TextView txt1, txt2, txt3, txt4;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.imglaptop);
            txt1=itemView.findViewById(R.id.tenlaptop);
            txt2=itemView.findViewById(R.id.gialaptop);
            txt3=itemView.findViewById(R.id.giamgialaptop);
            txt4=itemView.findViewById(R.id.khuyenmai);
            Animation animation;
            animation = AnimationUtils.loadAnimation(context,R.anim.scase);
            itemView.setAnimation(animation);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

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

