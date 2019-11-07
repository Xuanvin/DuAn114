package com.example.duana;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.duana.mode.Account;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterUser extends AppCompatActivity {
    public static final String TAG = RegisterUser.class.getSimpleName();

    EditText edtuser, edtemail, edtpassword, edtreppassword;
    ImageView clickreg;
    ImageView btnLogin;
    private ProgressDialog pDialog;
    FirebaseAuth auth;
    String username, password, confipassword, email;
    public static final String REGISTER_URL = "http://sanphambanhang.000webhostapp.com/dulieu.php";

    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_EMAIL = "email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        auth = FirebaseAuth.getInstance();
        edtuser = findViewById(R.id.edtUserBy);
        edtemail = findViewById(R.id.edtEmailRegister);
        edtpassword = findViewById(R.id.edtPasswordRegister);
        edtreppassword = findViewById(R.id.edtConfirmPasswordRegister);
//        btnLogin = findViewById(R.id.btnRegister1);
        clickreg = findViewById(R.id.btnRegister1);
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Đang đăng ký...");
        pDialog.setCanceledOnTouchOutside(false);
        addEvents();
    }

    private void addEvents() {
        clickreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Get data input
                username = edtuser.getText().toString().trim();
                password = edtpassword.getText().toString().trim();
                confipassword = edtreppassword.getText().toString().trim();
                email = edtemail.getText().toString().trim();

                //Call method register
                registerUser(username, password, email);
            }
        });
//        btnLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(RegisterUser.this, LoginApp.class);
//                startActivity(intent);
//            }
//        });
    }

    private void registerUser(final String username, final String password, final String email) {

        if (checkEditText(edtuser) && checkEditText(edtpassword) && checkEditText(edtreppassword) && checkEditText(edtemail) && isValidEmail(email)) {
            pDialog.show();
            StringRequest registerRequest = new StringRequest(Request.Method.POST, REGISTER_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.d(TAG, response);
                            String message = "";
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                if (jsonObject.getInt("success") == 1) {
                                    Account account = new Account();
                                    account.setUsername(jsonObject.getString("username"));
                                    account.setEmail(jsonObject.getString("email"));
                                    message = jsonObject.getString("message");
                                    Toast.makeText(RegisterUser.this, message, Toast.LENGTH_LONG).show();
                                    //Start LoginActivity
                                    Intent intent = new Intent(RegisterUser.this, LoginApp.class);
                                    startActivity(intent);
                                } else {
                                    message = jsonObject.getString("message");
                                    Toast.makeText(RegisterUser.this, message, Toast.LENGTH_LONG).show();
                                }
                            } catch (JSONException error) {
                                VolleyLog.d(TAG, "Error: " + error.getMessage());
                            }
                            pDialog.dismiss();
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(RegisterUser.this, "Bạn bị mất kết nối !", Toast.LENGTH_SHORT).show();
                            VolleyLog.d(TAG, "Error: " + error.getMessage());
                            pDialog.dismiss();
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
                    params.put(KEY_USERNAME, username);
                    params.put(KEY_PASSWORD, password);
                    params.put(KEY_EMAIL, email);
                    return params;
                }

            };
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(registerRequest);
        }
    }

    /**
     * Check Input
     */
    private boolean checkEditText(EditText editText) {
        if (editText.getText().toString().trim().length() > 0)
            return true;
        {
            editText.setError("Vui lòng nhập dữ liệu!");
        }
        return false;
    }

    /**
     * Check Email
     */
    private boolean isValidEmail(String target) {
        if (target.matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+.[a-z]"))
            return true;
        else {
            edtemail.setError("Email sai định dạng!");
        }
        return false;
    }
}
