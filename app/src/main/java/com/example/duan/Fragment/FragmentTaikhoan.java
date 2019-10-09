package com.example.duan.Fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.duan.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTaikhoan extends Fragment {

ImageView view;
    public FragmentTaikhoan() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_fragment_taikhoan, container, false);
        view=v.findViewById(R.id.xu);
        Animation animFade = AnimationUtils.loadAnimation(getContext(), R.anim.animo);
        view.startAnimation(animFade);
        return  v;
    }

}
