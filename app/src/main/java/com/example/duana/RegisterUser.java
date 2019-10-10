package com.example.duana;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterUser extends AppCompatActivity {
    EditText edtuser,edtemail,edtpassword,edtreppassword;
    ImageView clickreg;
    String email,password,reppassword;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        auth = FirebaseAuth.getInstance();
        edtuser = findViewById(R.id.edtUserBy);
        edtemail = findViewById(R.id.edtEmailRegister);
        edtpassword = findViewById(R.id.edtPasswordRegister);
        edtreppassword = findViewById(R.id.edtConfirmPasswordRegister);
        clickreg = findViewById(R.id.btnRegister1);
        clickreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = edtemail.getText().toString();
                password = edtpassword.getText().toString();
                reppassword = edtreppassword.getText().toString();
                if (TextUtils.isEmpty(email)){
                    Toast.makeText(getApplicationContext(), "Yêu cầu nhập địa chỉ rõ ràng", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    Toast.makeText(getApplicationContext(), "Yêu cầu nhập Mật Khẩu", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.length() < 6){
                    Toast.makeText(getApplicationContext(), "Mật khẩu quá ngắn yêu cầu nhập trên 6", Toast.LENGTH_SHORT).show();
                    return;
                }
                auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(RegisterUser.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast.makeText(RegisterUser.this, "Tạo Tài khoản Email là" + task.toString(), Toast.LENGTH_SHORT).show();
                        if (!task.isSuccessful()){
                            Toast.makeText(RegisterUser.this, "Lỗi khi đăng ký (có tài khoản rồi !!!)", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            startActivity(new Intent(RegisterUser.this,MainActivity.class));
                            finish();
                        }
                    }
                });
            }
        });
    }
}
