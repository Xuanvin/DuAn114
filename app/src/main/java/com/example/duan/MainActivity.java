package com.example.duan;

import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;



import com.example.duan.Adapter.ViewpaperAdapter;
import com.example.duan.Fragment.FragmentDao;
import com.example.duan.Fragment.FragmentGiohang;
import com.example.duan.Fragment.FragmentHome;
import com.example.duan.Fragment.FragmentTaikhoan;
import com.example.duan.Fragment.FragmentTintuc;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    //This is our viewPager
    ViewPager viewPager;


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

        viewPager = (ViewPager) findViewById(R.id.viewpager);

        //Initializing the bottomNavigationView
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.navigation_home:
                                viewPager.setCurrentItem(0);
                                break;
                            case R.id.navigation_dao:
                                viewPager.setCurrentItem(1);
                                break;
                            case R.id.navigation_tintuc:
                                viewPager.setCurrentItem(2);
                                break;
                            case R.id.navigation_giohang:
                                viewPager.setCurrentItem(3);
                                break;
                            case R.id.navigation_taikhoan:
                                viewPager.setCurrentItem(4);
                                break;
                        }
                        return false;
                    }
                });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (prevMenuItem != null) {
                    prevMenuItem.setChecked(false);
                } else {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                Log.d("page", "onPageSelected: " + position);
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
                prevMenuItem = bottomNavigationView.getMenu().getItem(position);

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
        fragHome = new FragmentHome();
        fragDao = new FragmentDao();
        fragTintuc = new FragmentTintuc();
        fragGiohang = new FragmentGiohang();
        fragTaikhoan = new FragmentTaikhoan();

        adapter.addFragment(fragHome);
        adapter.addFragment(fragDao);
        adapter.addFragment(fragTintuc);
        adapter.addFragment(fragGiohang);
        adapter.addFragment(fragTaikhoan);
        viewPager.setAdapter(adapter);
    }
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
}