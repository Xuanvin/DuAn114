package com.example.duana.Fragment.TrangChinhFragment;


import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.duana.Adapter.Vin.Adapter.Frag1;
import com.example.duana.Adapter.Vin.Adapter.Frag2;
import com.example.duana.Adapter.Vin.Adapter.ui.main.SectionsPagerAdapter;
import com.example.duana.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTintuc extends Fragment {
    TabLayout tabLayout;
    ViewPager viewPager;
    Frag1 frag1 ;
    Frag2 frag2;
    private int[] tabIcons = {
            R.drawable.icontt1,
            R.drawable.qqq,

    };
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_fragment_tintuc, container, false);
        tabLayout = view.findViewById(R.id.tabs1);
        viewPager = view.findViewById(R.id.view_pager1);

        frag1 = new Frag1();
        frag2 = new Frag2();
        setFragment(frag1);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabTextColors(Color.parseColor("#01579b"), Color.parseColor("#ffffff"));
        setupViewPager(viewPager);
        setupTabIcons();
        return view;
    }
    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
    }
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        adapter.addFragment(new Frag1(), "Thông báo ");
        adapter.addFragment(new Frag2(), "Cập nhật tin");


        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(3);
    }
    private void setFragment(Fragment fragmentHome) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.view_pager1, fragmentHome);
        fragmentTransaction.commit();
    }
    class ViewPagerAdapter extends FragmentPagerAdapter {

        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }

    }



}
