package com.example.tha_174178x;

import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

public class profile extends AppCompatActivity {

    private TextView name,index_no,email,mobile_no,current_gpa,password;
    private String name1,index1,email1,mobile1,gpa1,password1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        name=findViewById(R.id.textView23);
        index_no=findViewById(R.id.textView24);
        email=findViewById(R.id.textView25);
        mobile_no=findViewById(R.id.textView26);
        current_gpa=findViewById(R.id.textView27);
//        password=findViewById(R.id.profile_password);

        DbHelper db=new DbHelper(profile.this);
        Cursor result=db.getUserDetails();

        if (result.moveToFirst()) {
            do {
                name1 =result.getString(0);
                index1 =result.getString(1);
                email1 =result.getString(2);
                mobile1 =result.getString(3);
                gpa1 =result.getString(4);
//                password1 =result.getString(5);

            } while (result.moveToNext());
        }

        name.setText(name1);
        index_no.setText(index1);
        email.setText(email1);
        mobile_no.setText(mobile1);
        current_gpa.setText(gpa1);
//        password.setText(password1);
    }
}

