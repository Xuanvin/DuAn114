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
import com.example.duana.mode.ModelLaptapdell;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class LapTopDellAdapter extends RecyclerView.Adapter<LapTopDellAdapter.MyViewHolder> {
    private static Context context;
    private List<ModelLaptapdell> LaModeLaptops;
    private int layout;

    public LapTopDellAdapter(Context context,List<ModelLaptapdell> laModeLaptops, int layout) {
       this.LaModeLaptops = laModeLaptops;
       this.context=context;
        this.layout = layout;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.iteamlaptopdell, null);
        MyViewHolder viewHolder = new MyViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
final ModelLaptapdell modelLaptapdell=LaModeLaptops.get(position);
        new AsyncTaskLoadImage1(holder.img).execute(modelLaptapdell.getImg());
        holder.txt1.setText(modelLaptapdell.getTenSp());
        holder.txt2.setText(modelLaptapdell.getGiasp());
        holder.txt3.setText(modelLaptapdell.getGiamgiasp());
        holder.txt4.setText(modelLaptapdell.getKhuyenmaisp());
        holder.txt4.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
    }

    @Override
    public int getItemCount() {
        return LaModeLaptops.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView txt1, txt2, txt3, txt4;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.imglaptopdell);
            txt1=itemView.findViewById(R.id.tenlaptopdell);
            txt2=itemView.findViewById(R.id.gialaptopdell);
            txt3=itemView.findViewById(R.id.giamgialaptopdell);
            txt4=itemView.findViewById(R.id.khuyenmaidell);
            Animation animation;
            animation = AnimationUtils.loadAnimation(context,R.anim.scase);
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
