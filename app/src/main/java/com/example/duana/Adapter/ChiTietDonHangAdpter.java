package com.example.duana.Adapter;

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

import com.example.duana.R;
import com.example.duana.model.ModelGioHang;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.List;

public class ChiTietDonHangAdpter extends RecyclerView.Adapter<ChiTietDonHangAdpter.MyViewHolder> {
    private static Context context;
    private List<ModelGioHang> gioHangList;

    public ChiTietDonHangAdpter(Context context, List<ModelGioHang> gioHangList) {
        this.context = context;
        this.gioHangList = gioHangList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        @SuppressLint("InflateParams") View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.iteamxemdonhang, null);

        // create ViewHolder

        return new MyViewHolder(itemLayoutView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final ModelGioHang gioHang = gioHangList.get(position);
        holder.namexem.setText("" + gioHang.getTenSP());
        holder.numberxem.setText("x" + gioHang.getTongso());
        new AsyncTaskLoadImage1(holder.imgxem).execute(gioHang.getImg());
        DecimalFormat formatter = new DecimalFormat("#,###,###");
        String yourFormattedString = formatter.format(gioHang.getGiaSP());
        holder.pricexem.setText(yourFormattedString + " đ ");
        for (int i = 0; i < position; i++) {
            holder.so.setText("" + position);
        }

    }


    @Override
    public int getItemCount() {
        return gioHangList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imgxem;
        TextView namexem, pricexem, numberxem, so;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imgxem = itemView.findViewById(R.id.imgxem);
            namexem = itemView.findViewById(R.id.namexem);
            pricexem = itemView.findViewById(R.id.pricename);
            numberxem = itemView.findViewById(R.id.numberxem);
            so=itemView.findViewById(R.id.so);
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
