package com.example.duana.MainChinh;

import androidx.annotation.NonNull;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;


import com.example.duana.Fragment.TrangChinhFragment.FragmentDao;
import com.example.duana.Fragment.TrangChinhFragment.FragmentGiohang;
import com.example.duana.Fragment.TrangChinhFragment.FragmentHome;
import com.example.duana.Fragment.TrangChinhFragment.FragmentTaikhoan;
import com.example.duana.Fragment.TrangChinhFragment.FragmentTintuc;
import com.example.duana.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;


public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    //This is our viewPager
    public static final int REQUEST_CODE_GIOHANG = 123;
    public static String localhost = "sunner.000webhostapp.com";
    public static final String API_URL = "http://" + localhost + "/";
    private boolean close = false;    //Fragments

    FragmentDao fragDao;
    FragmentHome fragHome;
    FragmentGiohang fragGiohang;
    FragmentTaikhoan fragTaikhoan;
    FragmentTintuc fragTintuc;
    BottomNavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragHome = new FragmentHome();
        fragDao = new FragmentDao();
        navView = findViewById(R.id.bottom_navigation);
        final FrameLayout frameLayout = findViewById(R.id.viewpager);
        fragTintuc = new FragmentTintuc();
        fragGiohang = new FragmentGiohang();
        fragTaikhoan = new FragmentTaikhoan();
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
//        counterFab = findViewById(R.id.coubfab);
//        counterFab.setCount(0);
//        counterFab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                counterFab.increase();
//            }
//        });
        setFragment(fragHome);


    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    setFragment(fragHome);
                    return true;
                case R.id.navigation_dao:
                    setFragment(fragDao);
                    return true;
                case R.id.navigation_tintuc:
                    setFragment(fragTintuc);
                    return true;
                case R.id.navigation_giohang:
                    setFragment(fragGiohang);
                    return true;
                case R.id.navigation_taikhoan:
                    setFragment(fragTaikhoan);
                    return true;
            }
            return false;
        }
    };

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == REQUEST_CODE_GIOHANG) {
            //load fragment gio hang
            setFragment(new FragmentGiohang());
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (close) {
                finish();
            } else {
                Toast.makeText(this, "Nhấn lần nữa để thoát", Toast.LENGTH_SHORT).show();
                close = true;
            }
        }
        return false;
    }
}