package com.example.duana.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.duana.R;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.List;

public class BottomSheetdienthoai extends BottomSheetDialogFragment {
    List<BottomNavigationView>navigationViews;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view=inflater.inflate(R.layout.bottomsheetdienthoai,container,false);
       setupBadging();
        return view;
    }
    private void setupBadging() {
        for (BottomNavigationView bn : navigationViews) {
            int menuItemId = bn.getMenu().getItem(0).getItemId();
            // An icon only badge will be displayed.
            BadgeDrawable badge = bn.getOrCreateBadge(menuItemId);
            badge.setVisible(true);

            menuItemId = bn.getMenu().getItem(1).getItemId();
            // A badge with the text "99" will be displayed.
            badge = bn.getOrCreateBadge(menuItemId);
            badge.setVisible(true);
            badge.setNumber(99);

            menuItemId = bn.getMenu().getItem(2).getItemId();
            // A badge with the text "999+" will be displayed.
            badge = bn.getOrCreateBadge(menuItemId);
            badge.setVisible(true);
            badge.setNumber(9999);
        }
    }
}
