package com.example.duana.Fragment.BottomSheetDialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.duana.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import static com.example.duana.Adapter.SanphamAdapter1.Name1_Information;
import static com.example.duana.Adapter.SanphamAdapter1.Name2_Information;

public class BottomSheetdienthoai extends BottomSheetDialogFragment {
    String urlJson = "http://sanphambanhang.000webhostapp.com/Sanpham.php";
    TextView textView, textView1, textView2, textView3, textView4;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottomsheetdienthoai, container, false);
        textView = view.findViewById(R.id.name1);
        textView2 = view.findViewById(R.id.name2);
        textView.setText(Name1_Information);
        textView2.setText(Name2_Information);
        return view;
    }



}
