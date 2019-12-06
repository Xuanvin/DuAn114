package com.example.duana.Adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
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
import com.example.duana.Fragment.TrangChinhFragment.FragmentGiohang;
import com.example.duana.R;
import com.example.duana.model.ModelGioHang;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.example.duana.Fragment.TrangChinhFragment.FragmentGiohang.bottom_navigation;
import static com.example.duana.Fragment.TrangChinhFragment.FragmentGiohang.phivanchuyen;
import static com.example.duana.Fragment.TrangChinhFragment.FragmentGiohang.phivc;
import static com.example.duana.Fragment.TrangChinhFragment.FragmentGiohang.tongcong;
import static com.example.duana.UserName.LoginApp.userId;

public class AdapterGioHang extends RecyclerView.Adapter<AdapterGioHang.MyViewHolder> {
    @SuppressLint("StaticFieldLeak")
    private static FragmentGiohang context;
    private String url = "http://sanphambanhang.000webhostapp.com/updatethemlist.php";
    private String url1 = "http://sanphambanhang.000webhostapp.com/giohang.php";
    private List<ModelGioHang> gioHangList;
    private TextView number2;
    public static ModelGioHang gioHang1;
    public static String name1;
    public static String imggihang;
    public static String price;
    private SharedPreferences preferences;
    public AdapterGioHang(FragmentGiohang context, List<ModelGioHang> gioHangList) {
        AdapterGioHang.context = context;
        this.gioHangList = gioHangList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        @SuppressLint("InflateParams") View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemgiohang, null);
        return new MyViewHolder(itemLayoutView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final AdapterGioHang.MyViewHolder holder, int position) {
        preferences =context.getContext().getSharedPreferences("PrefsFile", Context.MODE_PRIVATE);
        final ModelGioHang gioHang = gioHangList.get(position);
        final int id = gioHang.getId_detell();
        holder.tensp.setText(gioHang.getTenSP());
        holder.txt3.setText(gioHang.getGiamgiaSP());
        final int[] count = {gioHang.getTongso()};
        holder.number1.setText("" + gioHang.getTongso());
        holder.addd1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PostData(url1);
                count[0]++;
                holder.number1.setText("" + count[0]);
                UpdaterThongtin(id);
                number2 = holder.number1;

            }
        });
        holder.remove1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PostData(url1);
                if (count[0] == 1) {

                    return;
                }

                count[0]--;
                holder.number1.setText("" + count[0]);
                UpdaterThongtin(id);
                number2 = holder.number1;

            }
        });


        name1 = gioHang.getTenSP();
        price = String.valueOf(gioHang.getGiaSP());
        imggihang = gioHang.getImg();
        gioHang1 = gioHang;
        DecimalFormat formatter = new DecimalFormat("#,###,###");
        String yourFormattedString = formatter.format(gioHang.getGiaSP());
        holder.txt2.setText(yourFormattedString + " đ ");
        new AsyncTaskLoadImage1(holder.img).execute(gioHang.getImg());
        holder.xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                XacNhanxoa(gioHang.getTenSP(),id);


            }
        });
    }

    @Override
    public int getItemCount() {
        return gioHangList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tensp, txt2, txt3, number1;
        ImageView img, xoa, remove1, addd1;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tensp = itemView.findViewById(R.id.tenSp);
            txt2 = itemView.findViewById(R.id.giaSp);
            txt3 = itemView.findViewById(R.id.charactise1);
            img = itemView.findViewById(R.id.imggiohang);
            number1 = itemView.findViewById(R.id.number1);
            remove1 = itemView.findViewById(R.id.remove1);
            addd1 = itemView.findViewById(R.id.addd1);
            xoa = itemView.findViewById(R.id.xoa);
        }


    }

    private void XacNhanxoa(final String ten, final int id) {
        AlertDialog.Builder dilogxoa = new AlertDialog.Builder(context.getContext());
        dilogxoa.setMessage("Bạn có muốn xóa " + id + ten + " không ?");
        dilogxoa.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


                context.Xoa(id);
            }
        }).setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        dilogxoa.show();

    }

    public void XacNhanxoa1(final String ten, final int id) {
        context.Xoa(id);
    }

    private void UpdaterThongtin(final int id) {
        RequestQueue requestQueue = Volley.newRequestQueue(Objects.requireNonNull(context.getContext()));
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.trim().equals("success")) {
                } else {
                    Toast.makeText(context.getContext(), "Lỗi cập nhật", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context.getContext(), "Lỗi ", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("id_detell", String.valueOf(id));
                params.put("tongsp", number2.getText().toString().trim());
                return params;
            }
        };
        requestQueue.add(request);
    }


    private void PostData(final String url) {
        final RequestQueue requestQueue = Volley.newRequestQueue(Objects.requireNonNull(context.getContext()));
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(final String response) {
                int sum = 0;
                int ship = 11000;
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("giohang");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject explrObject = jsonArray.getJSONObject(i);
                        DecimalFormat formatter = new DecimalFormat("#,###,###");
                        String yourFormattedString1 = formatter.format(phivc = ship * jsonArray.length());
                        String yourFormattedString = formatter.format(((sum += explrObject.getInt("Price_cart") * explrObject.getInt("Tongsp"))) + (ship * jsonArray.length()));
                        phivanchuyen.setText(yourFormattedString1 + "  đ");
                        tongcong.setText(yourFormattedString + " đ ");
                        bottom_navigation.setVisibility(View.VISIBLE);


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("vvvv", "onErrorResponse: " + error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("user_id", preferences.getString("userId",userId));
                return params;
            }
        };
        requestQueue.add(stringRequest); // cái ni để làm chi qên r


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

