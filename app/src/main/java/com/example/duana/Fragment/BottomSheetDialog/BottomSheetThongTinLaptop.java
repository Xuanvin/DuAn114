package com.example.duana.Fragment.BottomSheetDialog;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.duana.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Objects;

import static com.example.duana.Adapter.SanphamAdapter1.pos;

public class BottomSheetThongTinLaptop extends BottomSheetDialogFragment {
    TextView thuonghieu,manghinh,ram,bonhotrong,cpu,dungluongpin,thongtin;
    ImageView hinh1,hinh2,hinh3;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.thongtinlaptop,container,false);
        /// ánh xạ
        thuonghieu=v.findViewById(R.id.thuonghieu);
        manghinh=v.findViewById(R.id.mangHinh);
        ram=v.findViewById(R.id.ram);
        bonhotrong=v.findViewById(R.id.bonhotrong);
        cpu=v.findViewById(R.id.cpu);
        dungluongpin=v.findViewById(R.id.dungluongpin);
        hinh1=v.findViewById(R.id.hinh1);
        hinh2=v.findViewById(R.id.hinh2);
        hinh3=v.findViewById(R.id.hinh3);
        thongtin=v.findViewById(R.id.thongtin);
        // settet
        thuonghieu.setText(pos.getName1_information());
        manghinh.setText(pos.getName3_information());
        ram.setText(pos.getRam());
        bonhotrong.setText(pos.getKhaNangLuuTru());
        cpu.setText(pos.getCpu());
        dungluongpin.setText(pos.getDungluongping());
        thongtin.setText(pos.getInformation());
        new AsyncTaskLoadImage1(hinh1).execute(pos.getImg1());
        new AsyncTaskLoadImage1(hinh2).execute(pos.getImg2());
        new AsyncTaskLoadImage1(hinh3).execute(pos.getImg3());
        return v;
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
