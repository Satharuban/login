package com.example.tha_174178x;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {

    private EditText index_no, password;
    private Button login;
    private ProgressDialog progressDialog;
    public static String user_index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        index_no = findViewById(R.id.txtintn);
        password = findViewById(R.id.txtpas);
        login = findViewById(R.id.btnlogin);
        progressDialog = new ProgressDialog(login.this);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(index_no.getText().toString())) {
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    Toast.makeText(getApplicationContext(), "please Enter The Index No!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password.getText().toString())) {
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    Toast.makeText(getApplicationContext(), "please Enter The Password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                loginUser();
            }
        });

    }
    public void loginUser() {
        progressDialog.setMessage("Login wait.........");
        progressDialog.show();

        DbHelper dbH = new DbHelper(login.this);
        boolean resL = dbH.loginUser(index_no.getText().toString(), password.getText().toString());
        dbH.close();

        if (resL) {
            Log.d("sql", String.valueOf(resL));
            user_index=index_no.getText().toString();
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }

            Toast.makeText(getApplicationContext(), "Successfully Login", Toast.LENGTH_SHORT).show();
            Intent startActivity2 = new Intent(login.this, profile.class);
            startActivity(startActivity2);
        } else {
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
            Toast.makeText(getApplicationContext(), "Incorrect username or Password", Toast.LENGTH_SHORT).show();
        }
    }
}

