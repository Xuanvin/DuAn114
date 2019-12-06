package com.example.duana.Adapter.slideviewpag;

import android.content.Context;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.duana.R;
import com.squareup.picasso.Picasso;

import static com.example.duana.Adapter.DienThoaiAdapter.postion;
import static com.example.duana.Adapter.SanphamAdapter1.img1;
import static com.example.duana.Adapter.SanphamAdapter1.img2;
import static com.example.duana.Adapter.SanphamAdapter1.img3;

public class ViewPagerAdapter1 extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private  ViewPager viewPager;
    private String[] images = {postion.getImg1(),postion.getImg2(),postion.getImg3()
    };

    public ViewPagerAdapter1(Context context,ViewPager viewPager) {
        this.context = context;
        this.viewPager=viewPager;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {

        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_layout, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        Picasso.get().load(images[position]).into(imageView);
        ViewPager vp = (ViewPager) container;

        vp.addView(view, 0);

        return view;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        ViewPager vp = (ViewPager) container;
        View view = (View) object;

        vp.removeView(view);

    }
}