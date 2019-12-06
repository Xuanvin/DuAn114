package com.example.duana.Fragment.BottomSheetDialog;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.duana.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BotomSheetHuy extends BottomSheetDialogFragment {
    private BottomSheetListener mlListener;
   private CheckBox checkboxa,checkBoxb,checkBoxc;
   private Button btn;
   private TextView txta,txtb,txtc;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View  view=inflater.inflate(R.layout.bottomshethuy,container,false);
        checkboxa=view.findViewById(R.id.chechboxa);
        checkBoxb=view.findViewById(R.id.chechboxb);
        checkBoxc=view.findViewById(R.id.chechboxc);
        txta=view.findViewById(R.id.trungdonhang);
        txtb=view.findViewById(R.id.thaydoidiadiem);
        txtc=view.findViewById(R.id.thaydoiy);
        btn=view.findViewById(R.id.xatnhan);
        checkboxa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        return view;
    }
    public interface BottomSheetListener{
        void onButtonClick(String text);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }
}
