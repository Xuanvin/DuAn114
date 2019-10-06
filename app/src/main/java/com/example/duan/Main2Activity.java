package com.example.duan;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.example.duan.Adapter.ViewpaperAdapter;
import com.example.duan.Fragment.Chitiet;
import com.example.duan.Fragment.FragmentDao;
import com.example.duan.Fragment.FragmentGiohang;
import com.example.duan.Fragment.FragmentHome;
import com.example.duan.Fragment.FragmentTaikhoan;
import com.example.duan.Fragment.FragmentTintuc;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class Main2Activity extends AppCompatActivity {
Chitiet chitiet;
ViewPager viewPager;
    MenuItem prevMenuItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        viewPager = (ViewPager) findViewById(R.id.viewpager1);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (prevMenuItem != null) {
                    prevMenuItem.setChecked(false);
                } else {

                }
                Log.d("page", "onPageSelected: " + position);


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        setupViewPager(viewPager);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
    }
    private void setupViewPager(ViewPager viewPager) {
        ViewpaperAdapter adapter = new ViewpaperAdapter(getSupportFragmentManager());
       chitiet = new Chitiet();



        adapter.addFragment(chitiet);

        viewPager.setAdapter(adapter);
    }

}
