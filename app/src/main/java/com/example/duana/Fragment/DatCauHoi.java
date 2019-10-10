package com.example.duana.Fragment;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.duana.R;

import java.util.ArrayList;

public class DatCauHoi extends AppCompatActivity {
    ImageView back;
    EditText edt1;
    ImageButton img1;
    ListView datcauhs;
    ListView listView;
    ArrayList<String> arrayList;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dat_cau_hoi);
        edt1 = findViewById(R.id.edt1);
        img1 = findViewById(R.id.img1);
        arrayList=new ArrayList<String>();
        arrayList.add("aaaa");
        adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayList);
        listView=findViewById(R.id.datcauhoi1);
        listView.setAdapter(adapter);
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String add=edt1.getText().toString();
                arrayList.add(add);
                adapter.notifyDataSetChanged();


            }
        });
        datcauhs = findViewById(R.id.datcauhoi1);
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

}
