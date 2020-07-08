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

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private EditText name, index_no, email, current_gpa, password, mobile_no, confirm_password;
    private Button submit;
    private ProgressDialog progressDialog;
    private static final Pattern IndexNoPattern =Pattern.compile("17+[0-9]{4}+[a-zA-Z]{1}");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.editText);
        index_no = findViewById(R.id.editText2);
        email = findViewById(R.id.editText3);
        current_gpa = findViewById(R.id.editText5);
        password = findViewById(R.id.editText6);
        confirm_password = findViewById(R.id.editText7);
        mobile_no = findViewById(R.id.editText4);
        submit = findViewById(R.id.button);



        progressDialog = new ProgressDialog(MainActivity.this);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(name.getText().toString())) {
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    Toast.makeText(getApplicationContext(), "please Enter The Name!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(index_no.getText().toString())) {
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    Toast.makeText(getApplicationContext(), "please Enter The Index No!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if( !IndexNoPattern.matcher(index_no.getText().toString()).matches()){
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    Toast.makeText(getApplicationContext(), "Please Enter The valid Index No!", Toast.LENGTH_SHORT).show();
                    return;
                }



                if (TextUtils.isEmpty(email.getText().toString())) {
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    Toast.makeText(getApplicationContext(), "please Enter The Email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if( !android.util.Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()){
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    Toast.makeText(getApplicationContext(), "Please Enter The valid Email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (mobile_no.getText().toString().length() != 10) {
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    Toast.makeText(getApplicationContext(), " Please Enter The Phone Number Correctly!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(current_gpa.getText().toString())) {
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    Toast.makeText(getApplicationContext(), "Please Enter The current GPA!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password.getText().toString())) {
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    Toast.makeText(getApplicationContext(), "Please Enter the password!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.getText().toString().length() < 6) {
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    Toast.makeText(getApplicationContext(), "Please Enter password should more than 6 char!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(confirm_password.getText().toString())) {
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    Toast.makeText(getApplicationContext(), "Please Enter Confirm Password!", Toast.LENGTH_SHORT).show();

                    return;
                }
                if (!confirm_password.getText().toString().equals(password.getText().toString())) {
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    Toast.makeText(getApplicationContext(), "Please Enter the matching Confirm Password and password!", Toast.LENGTH_SHORT).show();

                    return;
                }

                register();
            }
        });


    }


    //register function
    public void register(){
        progressDialog.setMessage("Registering wait.........");
        progressDialog.show();


        try {
            //create the Db helper object
            //insert data
            DbHelper db = new DbHelper(MainActivity.this);
            Boolean result= db.insertUserData(name.getText().toString(),index_no.getText().toString(),email.getText().toString(),mobile_no.getText().toString(), current_gpa.getText().toString(),password.getText().toString());
            Log.d("RESULTTTTTTTTTTTTTTTT",result.toString());
            if(result){
                Toast.makeText(getApplicationContext(), "Successfully registered", Toast.LENGTH_SHORT).show();

                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                //after successful registration open login activity
                Intent startActivity1 = new Intent(MainActivity.this, login.class);
                startActivity(startActivity1);
            }
            else {
                Toast.makeText(getApplicationContext(), "something wrong while inserting the data!", Toast.LENGTH_SHORT).show();

            }

        }catch (Exception e){
            e.printStackTrace();
        }



    }
}
