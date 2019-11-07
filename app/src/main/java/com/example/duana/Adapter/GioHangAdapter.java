package com.example.duana.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

import com.example.duana.Fragment.FragmentGiohang;
import com.example.duana.R;
import com.example.duana.mode.ModelGioHang;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.List;

public class GioHangAdapter extends BaseAdapter {
    private static FragmentGiohang context;
    private int layout;
    private List<ModelGioHang> gioHangList;

    public GioHangAdapter(FragmentGiohang context, int layout, List<ModelGioHang> gioHangList) {
        this.context = context;
        this.layout = layout;
        this.gioHangList = gioHangList;
    }

    @Override
    public int getCount() {
        return gioHangList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    public  class ViewHolder{
        TextView txt1,txt2,txt3;
        ImageView  img;
        CheckBox checkBox;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view==null){
            holder=new ViewHolder();
            LayoutInflater inflater=(LayoutInflater)context.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(R.layout.itemgiohang,null);
            holder.txt1=view.findViewById(R.id.tenSp);
            holder.txt2=view.findViewById(R.id.giaSp);
            holder.txt3=view.findViewById(R.id.giamga);
            holder.img=view.findViewById(R.id.imggiohang);
            holder.checkBox=view.findViewById(R.id.checkBox);
            view.setTag(holder);
        }else {
            holder=(ViewHolder)view.getTag();
        }
        final ModelGioHang gioHang=gioHangList.get(i);
        holder.txt1.setText(gioHang.getTenSP());
        holder.txt3.setText(gioHang.getGiamgiaSP());
        DecimalFormat formatter = new DecimalFormat("#,###,###");
        String yourFormattedString = formatter.format(gioHang.getGiaSP());
        holder.txt2.setText(yourFormattedString + " đ " );
        new AsyncTaskLoadImage1(holder.img).execute(gioHang.getImg());
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                XacNhanxoa(gioHang.getTenSP(),gioHang.getId());
            }
        });
        return view;
    }
    private void XacNhanxoa(final String ten, final int id) {
        AlertDialog.Builder dilogxoa = new AlertDialog.Builder(context.getContext());
        dilogxoa.setMessage("Bạn có muốn xóa " + ten + " không ?");
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
