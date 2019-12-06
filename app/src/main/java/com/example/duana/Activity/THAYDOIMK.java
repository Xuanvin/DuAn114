package com.example.duana.Activity;

import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duana.R;

import java.util.HashMap;
import java.util.Map;

import static com.example.duana.Activity.CAPNHATTHONGTIN.pass;
import static com.example.duana.UserName.LoginApp.userId;

public class THAYDOIMK extends AppCompatActivity {
    private String url = "http://sanphambanhang.000webhostapp.com/updatelogin.php";
    private  String getUrl="http://sanphambanhang.000webhostapp.com/findidlogin.php";
    int id = 0;
    private Button thaydoi;
    private EditText editText1, editText2,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thay_doi_mk);
        //++++++++++++++++++++++++++++++++++++++++++++++ ánh xạ
        Toolbar toolbar = findViewById(R.id.toolbar);
        thaydoi = findViewById(R.id.thaydoi);
        editText1 = findViewById(R.id.newpassword);
        editText2 = findViewById(R.id.cofimnewpassword);
        password=findViewById(R.id.password);
        password.setText(pass);
        thaydoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pass1 = editText1.getText().toString().trim();
                String cofipass = editText2.getText().toString().trim();
                String passs=password.getText().toString().trim();
                if  (pass1.matches("") || cofipass.equals("")||passs.equals("")){
                    Toast.makeText(THAYDOIMK.this, "Vui  lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {

                    UpdaterThongtin();

                }

            }
        });
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void UpdaterThongtin() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.trim().equals("success")) {
                    Toast.makeText(THAYDOIMK.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                    finish();

                } else {
                    Toast.makeText(THAYDOIMK.this, "Lỗi cập nhật", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(THAYDOIMK.this, "Lỗi ", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("user_id", String.valueOf(userId));
                params.put("password", editText1.getText().toString().trim());
                return params;
            }
        };
        requestQueue.add(request);
    }
}
