package com.example.duana.Adapter.Vin.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duana.Adapter.AdapterChoVanChuyen;
import com.example.duana.Adapter.Vin.Adapter.model.Modelthongbao;
import com.example.duana.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class Adapterthongbao extends RecyclerView.Adapter<Adapterthongbao.ViewHolder>{
    private Context context;
    private List<Modelthongbao>modelthongbaos;

    public Adapterthongbao(Context context, List<Modelthongbao> modelthongbaos) {
        this.context = context;
        this.modelthongbaos = modelthongbaos;

    }

    @NonNull
    @Override
    public Adapterthongbao.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        @SuppressLint("InflateParams") View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_thongbao, null);
        return new ViewHolder(itemLayoutView);
    }


    @Override
    public void onBindViewHolder(@NonNull Adapterthongbao.ViewHolder holder, int position) {
        final Modelthongbao modelthongbao = modelthongbaos.get(position);
        holder.name.setText(modelthongbao.getName());
        holder.ngay.setText(modelthongbao.getNgay());
        holder.thongbao.setText(modelthongbao.getThongtin());
        new AsyncTaskLoadImage1(holder.img1).execute(modelthongbao.getImg1());

    }

    @Override
    public int getItemCount() {
        return modelthongbaos.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
   ImageView img1;
   TextView name,ngay,thongbao;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
                img1 =itemView.findViewById(R.id.idhinhthongbao);
                name = itemView.findViewById(R.id.name);
                ngay = itemView.findViewById(R.id.idngaythongbao);
                thongbao = itemView.findViewById(R.id.idthongtinthongbao);
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
