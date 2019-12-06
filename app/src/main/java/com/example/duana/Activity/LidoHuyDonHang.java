package com.example.duana.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.duana.Fragment.BottomSheetDialog.BotomSheetHuy;
import com.example.duana.R;

public class LidoHuyDonHang extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lido_huy_don_hang);
        TextView lidohuy= findViewById(R.id.lidohuy);
        lidohuy.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.lidohuy) {
            BotomSheetHuy bottomSheetdienthoai = new BotomSheetHuy();
            assert getFragmentManager() != null;
            bottomSheetdienthoai.show(getSupportFragmentManager(), "exampleBottomSheet");
        }
    }
}
