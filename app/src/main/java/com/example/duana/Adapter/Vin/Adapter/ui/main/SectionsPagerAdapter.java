package com.example.duana.Adapter.Vin.Adapter.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.duana.Adapter.Vin.Adapter.Frag1;
import com.example.duana.Adapter.Vin.Adapter.Frag2;
import com.example.duana.R;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
      Fragment fragment = new Frag1();
      switch (position) {
          case 0:
               fragment = new Frag1();
               break;

          case 1:
              fragment = new Frag2();
              break;

      }
  return fragment;
}

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
switch (position){
    case 0:
        return  "SECTION 1 ";
    case 1:
        return  "SECTION 2";
}


       return null;
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 2;
    }
}