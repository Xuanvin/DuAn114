package com.example.duana.UserName;

import android.content.Intent;
import android.os.Bundle;

import com.example.duana.R;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ThongtinTk extends AppCompatActivity {
    TextView tk;
    Button dx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongtin_tk);
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
                LoginManager.getInstance().logOut();
               startActivity(new Intent(ThongtinTk.this, LoginApp.class));
               finish();
            }
        });
//        tk = findViewById(R.id.settx);
//        tk.setText(user.getEmail());

    }

}
