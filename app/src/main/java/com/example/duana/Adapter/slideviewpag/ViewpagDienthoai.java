package com.example.duana.Adapter.slideviewpag;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.duana.R;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static com.example.duana.Adapter.DienThoaiAdapter.Img2_dienthoai;
import static com.example.duana.Adapter.DienThoaiAdapter.Img3_dienthoai;
import static com.example.duana.Adapter.DienThoaiAdapter.Img_dienthoai;

public class ViewpagDienthoai extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private ViewPager viewPager;
    public static String[] image = {Img_dienthoai,Img2_dienthoai,Img3_dienthoai
    };



    public ViewpagDienthoai(Context context,ViewPager viewPager) {
        this.context = context;
        this.viewPager=viewPager;
    }

    @Override
    public int getCount() {
        return image.length;
    }

    @Override
    public boolean isViewFromObject(@NotNull View view, @NotNull Object object) {

        return view == object;
    }

    @NotNull
    @Override
    public Object instantiateItem(@NotNull ViewGroup container, final int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert layoutInflater != null;
        @SuppressLint("InflateParams") View view = layoutInflater.inflate(R.layout.custom_layout, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        Picasso.get().load(image[position]).into(imageView);
        ViewPager vp = (ViewPager) Objects.requireNonNull(container);
        vp.setCurrentItem(1);
        vp.addView(view, 0);

        return view;

    }

    @Override
    public void destroyItem(@NotNull ViewGroup container, int position, @NotNull Object object) {

        ViewPager vp = (ViewPager) container;
        View view = (View) object;

        vp.removeView(view);

    }
}