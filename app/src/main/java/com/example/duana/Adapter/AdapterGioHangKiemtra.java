package com.example.duana.Adapter;

import android.annotation.SuppressLint;
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

import com.example.duana.ThuTucThanhToan.KiemTra;
import com.example.duana.R;
import com.example.duana.model.ModelGioHang;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.List;

public class AdapterGioHangKiemtra extends RecyclerView.Adapter<AdapterGioHangKiemtra.MyViewHolder> {
    private static KiemTra context;
    private List<ModelGioHang> gioHangList;

    public AdapterGioHangKiemtra(KiemTra context, List<ModelGioHang> gioHangList) {
        this.context = context;
        this.gioHangList = gioHangList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        @SuppressLint("InflateParams") View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.iteamkiemtra, null);

        // create ViewHolder

        return new MyViewHolder(itemLayoutView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final ModelGioHang gioHang = gioHangList.get(position);
        holder.tensp.setText("" + gioHang.getTenSP());
        holder.tongso.setText("" + gioHang.getGiaSP());
        holder.number.setText("" + gioHang.getTongso());
        for (int i = 0; i < position; i++) {
            holder.goihang.setText("" + position);
        }

        new AsyncTaskLoadImage1(holder.img).execute(gioHang.getImg());
        DecimalFormat formatter = new DecimalFormat("#,###,###");
        String yourFormattedString = formatter.format(gioHang.getGiaSP());
        holder.textView1.setText(yourFormattedString + " đ ");
        String format = formatter.format(gioHang.getGiaSP() * gioHang.getTongso());
        holder.tongso.setText(format + "đ");

    }


    @Override
    public int getItemCount() {
        return gioHangList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView tensp, tongso, textView1, goihang, number;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imgkiemtra);
            tensp = itemView.findViewById(R.id.textsp1);
            tongso = itemView.findViewById(R.id.tongso);
            textView1 = itemView.findViewById(R.id.textgia);
            goihang = itemView.findViewById(R.id.goihang);
            number = itemView.findViewById(R.id.number);
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
