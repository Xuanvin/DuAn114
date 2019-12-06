package com.example.duana.Activity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.duana.R;

import static com.example.duana.Activity.TIMKIEM.searchView;


public class TimKiemNot extends AppCompatActivity {
SearchView search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tim_kiem_not);
        Toolbar toolbar = findViewById(R.id.toolbar);
        search=findViewById(R.id.searchql);
        searchView.onActionViewExpanded();
        search.setQuery( "abc",false);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TimKiemNot.this,TIMKIEM.class));
            }
        });
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
