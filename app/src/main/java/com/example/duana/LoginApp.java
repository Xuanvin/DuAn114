package com.example.duana;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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

public class LoginApp extends AppCompatActivity {
    ImageView imgLogin,imgWithGoogle,imgWithFacebook,imgRegister;
    EditText edtUserLogin,edtPassLogin;
    FirebaseAuth auth;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_app);
        auth = FirebaseAuth.getInstance();
        imgLogin = findViewById(R.id.btnLogin);
        imgWithFacebook = findViewById(R.id.btnWithFacebook);
        imgWithGoogle = findViewById(R.id.btnWithGoogle);
        imgRegister = findViewById(R.id.btnRegister);
        imgRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),RegisterUser.class));
            }
        });
        edtUserLogin = findViewById(R.id.edtEmailLogin);
        edtPassLogin = findViewById(R.id.edtPasswordLogin);
        imgLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new ProgressDialog(LoginApp.this);
                dialog.setMessage("Xin vui lòng chờ đăng nhập");
                dialog.setCanceledOnTouchOutside(false);

                String email = edtUserLogin.getText().toString();
                final String password = edtPassLogin.getText().toString();
                if (TextUtils.isEmpty(email)){
                    Toast.makeText(LoginApp.this, "Yêu cầu nhập account email đã đăng ký ...!!", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (TextUtils.isEmpty(password)){
                    Toast.makeText(LoginApp.this, "Yêu cầu nhập password đã đăng ký...!!!", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    Toast.makeText(LoginApp.this, "Dang nhap thanh cong...!!!", Toast.LENGTH_SHORT).show();
                    dialog.cancel();
                }
                auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(LoginApp.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()){
                            if (password.length() < 6){
                                edtPassLogin.setError("Mật khẩu không quá ít 6 ký tự...!!!");
                            }else {
                                Toast.makeText(LoginApp.this, "Lỗi đăng nhập,yêu cầu kiểm tra lại email và mật khẩu khi đăng ký...!!!", Toast.LENGTH_SHORT).show();
                            dialog.cancel();
                            }
                        }else {
                            startActivity(new Intent(getApplication(),MainActivity.class));
                            finish();
                        }
                        edtUserLogin.getText().clear();
                        edtPassLogin.getText().clear();
                    }
                });
                dialog.show();
            }
        });
    }
}
