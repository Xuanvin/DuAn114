package com.example.duana.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.duana.MainChinh.MainActivity;
import com.example.duana.R;

public class MatMang extends AppCompatActivity {
private TextView kiemtralai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mat_mang);
        kiemtralai=findViewById(R.id.kiemtralai);
        kiemtralai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MatMang.this, MainActivity.class));
                finish();
            }
        });
    }
}
