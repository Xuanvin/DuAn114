package com.example.duana;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ThongtinTk extends AppCompatActivity {
    FirebaseAuth auth;
    TextView tk;
    Button dx;
FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongtin_tk);
        auth = FirebaseAuth.getInstance();


        ImageView imageView=findViewById(R.id.backa);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        dx = findViewById(R.id.dx);
        dx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(ThongtinTk.this,LoginApp.class));
                finish();
            }
        });
        tk = findViewById(R.id.settx);
//        tk.setText(user.getEmail());

    }

}
