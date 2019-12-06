package com.example.duana.Fragment.TrangChinhFragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.duana.Activity.CAPNHATTHONGTIN;
import com.example.duana.Activity.CHOVANCHUYEN;
import com.example.duana.Activity.HUYDONHANG;
import com.example.duana.UserName.LoginApp;
import com.example.duana.R;
import com.example.duana.UserName.ThongtinTk;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import java.util.Objects;

import static com.example.duana.UserName.LoginApp.edtUserLogin1;
import static com.example.duana.UserName.LoginApp.email;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTaikhoan extends Fragment implements View.OnClickListener {
    private SharedPreferences preferences;
    private  SharedPreferences.Editor editor;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        preferences = getContext().getSharedPreferences("PrefsFile", Context.MODE_PRIVATE);
        editor = preferences.edit();

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fragment_taikhoan, container, false);
        // ++++++++++++++++++++++++++++++++++++++++++ ánh xạ
        TextView txtAccount = v.findViewById(R.id.txtname);
        TextView txtemail = v.findViewById(R.id.txtemail);
        ImageView imageView = v.findViewById(R.id.imgperson);
        TextView Chvanchuyenr=v.findViewById(R.id.Chvanchuyenr);
        TextView donhang=v.findViewById(R.id.donhang);
        Button dx = v.findViewById(R.id.dangxuat);
        TextView capnhatthongtin=v.findViewById(R.id.capnhatthongtin);
        LinearLayout linearLayout = v.findViewById(R.id.caidat);
        //+++++++++++++++++++++++++++++++++++++++++
        Chvanchuyenr.setOnClickListener(this);
        donhang.setOnClickListener(this);
        dx.setOnClickListener(this);
        Chvanchuyenr.setOnClickListener(this);
        capnhatthongtin.setOnClickListener(this);
        linearLayout.setOnClickListener(this);
        //+++++++++++++++++++++++++++++++++
            txtAccount.setText(preferences.getString("pref_name", ""));
            txtemail.setText(email);
        return v;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.capnhatthongtin:
                startActivity(new Intent(getContext(), CAPNHATTHONGTIN.class));
                break;
            case R.id.Chvanchuyenr:
                startActivity(new Intent(getContext(), CHOVANCHUYEN.class));
                break;
            case R.id.donhang:
                startActivity(new Intent(getContext(), HUYDONHANG.class));
                break;
            case R.id.dangxuat:
                editor.clear().apply();
                startActivity(new Intent(getContext(), LoginApp.class));
                Objects.requireNonNull(getActivity()).finish();
        }
    }
}
