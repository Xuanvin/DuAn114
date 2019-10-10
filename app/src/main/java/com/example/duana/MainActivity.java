package com.example.duana;

import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;


import com.example.duana.Fragment.FragmentDao;
import com.example.duana.Fragment.FragmentGiohang;
import com.example.duana.Fragment.FragmentHome;
import com.example.duana.Fragment.FragmentTaikhoan;
import com.example.duana.Fragment.FragmentTintuc;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    //This is our viewPager


    //Fragments

    FragmentDao fragDao;
    FragmentHome fragHome;
    FragmentGiohang fragGiohang;
    FragmentTaikhoan fragTaikhoan;
    FragmentTintuc fragTintuc;
    MenuItem prevMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragHome = new FragmentHome();
        fragDao = new FragmentDao();
        final FrameLayout frameLayout = findViewById(R.id.viewpager);
        fragTintuc = new FragmentTintuc();
        fragGiohang = new FragmentGiohang();
        fragTaikhoan = new FragmentTaikhoan();
        setFragment(fragHome);
//        viewPager = (ViewPager) findViewById(R.id.viewpager);

        //Initializing the bottomNavigationView
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.navigation_home:
                                setFragment(fragHome);
                                frameLayout.setBackgroundColor(0);
                                break;
                            case R.id.navigation_dao:
                                setFragment(fragDao);
                                frameLayout.setBackgroundColor(1);
                                break;
                            case R.id.navigation_tintuc:
                                setFragment(fragTintuc);
                                frameLayout.setBackgroundColor(2);
                                break;
                            case R.id.navigation_giohang:
                                setFragment(fragGiohang);
                                break;
                            case R.id.navigation_taikhoan:
                                setFragment(fragTaikhoan);
                                break;
                        }
                        return false;
                    }
                });
        frameLayout.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View view, int i, int i1, int i2, int i3, int i4, int i5, int i6, int i7) {
                if (prevMenuItem != null) {
                    prevMenuItem.setChecked(false);
                } else {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                Log.d("page", "onPageSelected: " + i);
                bottomNavigationView.getMenu().getItem(i).setChecked(true);
                prevMenuItem = bottomNavigationView.getMenu().getItem(i);

            }

        });

////             b.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
////            @Override
////            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
////
////            }
////
////            @Override
////            public void onPageSelected(int position) {
////                if (prevMenuItem != null) {
////                    prevMenuItem.setChecked(false);
////                } else {
////                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
////                }
////                Log.d("page", "onPageSelected: " + position);
////                bottomNavigationView.getMenu().getItem(position).setChecked(true);
////                prevMenuItem = bottomNavigationView.getMenu().getItem(position);
////
////            }
////
////            @Override
////            public void onPageScrollStateChanged(int state) {
////
////            }
////        });
////        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
//   }
    }
//    private void setupViewPager(ViewPager viewPager) {
//        ViewpaperAdapter adapter = new ViewpaperAdapter(getSupportFragmentManager());
//        fragHome = new FragmentHome();
//        fragDao = new FragmentDao();
//        fragTintuc = new FragmentTintuc();
//        fragGiohang = new FragmentGiohang();
//        fragTaikhoan = new FragmentTaikhoan();
//
//        adapter.addFragment(fragHome);
//        adapter.addFragment(fragDao);
//        adapter.addFragment(fragTintuc);
//        adapter.addFragment(fragGiohang);
//        adapter.addFragment(fragTaikhoan);
//        viewPager.setAdapter(adapter);
//    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu3cham, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.bttinnhan) {
            return true;
        }


        return super.onOptionsItemSelected(item);
    }
    private void setFragment(Fragment fragmentHome) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.viewpager, fragmentHome);
        fragmentTransaction.commit();
    }
}