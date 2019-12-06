package com.example.duana.VietDanhGia;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import com.example.duana.Adapter.PhotoAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import com.example.duana.R;

public class DanhGia extends AppCompatActivity implements View.OnClickListener {
    private int PICK_IMAGE_REQUEST = 1;
    RecyclerView selectImg;
    private PhotoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_gia);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        /// ánh xạ
        ImageView chonanh = findViewById(R.id.chonanh);
        selectImg = findViewById(R.id.selectImg);

        chonanh.setOnClickListener(this);
        adapter = new PhotoAdapter(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1, RecyclerView.HORIZONTAL, false);
        selectImg.setLayoutManager(gridLayoutManager);
        selectImg.setFocusable(false);
        selectImg.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.chonanh:

        }
    }



}
