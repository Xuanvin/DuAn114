package com.example.duana.Adapter;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.duana.Activity.LidoHuyDonHang;
import com.example.duana.R;
import com.example.duana.model.ModelChoVanChuyen;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdapterChoVanChuyen extends RecyclerView.Adapter<AdapterChoVanChuyen.MyViewHolder> {
    private List<ModelChoVanChuyen> hangList;
    private Context context;
    private String url="http://sanphambanhang.000webhostapp.com/Chovanchuyen.php";
    private String url2 = "http://sanphambanhang.000webhostapp.com/updateHuy.php";
    private int iduser = 2;
    private ProgressDialog myProgress;

    public AdapterChoVanChuyen(List<ModelChoVanChuyen> hangList, Context context) {
        this.hangList = hangList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        @SuppressLint("InflateParams") View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.iteamchovanchuyen, null);
        return new MyViewHolder(itemLayoutView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final ModelChoVanChuyen choVanChuyen = hangList.get(position);
        final int  idcart = choVanChuyen.getId();
        holder.txta.setText(choVanChuyen.getName());
        holder.txtc.setText("x" + choVanChuyen.getNumber1());
        holder.txtd.setText("" + choVanChuyen.getPrinumber2());
        new AsyncTaskLoadImage1(holder.img1).execute(choVanChuyen.getImg());
        DecimalFormat formatter = new DecimalFormat("#,###,###");
        String yourFormattedString = formatter.format(choVanChuyen.getPrice());
        String yourFormattedString1 = formatter.format(choVanChuyen.getPrice2());
        holder.txtb.setText(yourFormattedString + "đ");
        holder.txte.setText(yourFormattedString1 + "đ");
        holder.txtf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
context.startActivity(new Intent(context, LidoHuyDonHang.class));
//                UpdaterCho(idcart);
            }
        });
    }

    @Override
    public int getItemCount() {
        return hangList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView img1;
        TextView txta, txtb, txtc, txtd, txte, txtf;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txta = itemView.findViewById(R.id.txta);
            txtb = itemView.findViewById(R.id.txtb);
            txtc = itemView.findViewById(R.id.txtc);
            txtd = itemView.findViewById(R.id.txtd);
            txte = itemView.findViewById(R.id.txte);
            img1 = itemView.findViewById(R.id.imga);
            txtf = itemView.findViewById(R.id.huyhoadon);
        }
    }

    private void UpdaterCho(final int idcart) {
        RequestQueue requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        StringRequest request = new StringRequest(Request.Method.POST, url2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.trim().equals("success")) {
                } else {
                    Toast.makeText(context.getApplicationContext(), "Lỗi cập nhật", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context.getApplicationContext(), "Lỗi ", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("id_user", String.valueOf(idcart));
                params.put("id_cartuser", String.valueOf(iduser));
                return params;
            }
        };
        requestQueue.add(request);
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
