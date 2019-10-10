package com.example.duana;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.example.duana.Adapter.ViewpaperAdapter;
import com.example.duana.Fragment.Chitiet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.MenuItem;

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
