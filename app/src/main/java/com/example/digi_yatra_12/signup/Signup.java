package com.example.digi_yatra_12.signup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.digi_yatra_12.MainActivity;
import com.example.digi_yatra_12.R;

public class Signup extends AppCompatActivity {
    Button otpbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        otpbtn =findViewById(R.id.RegisterBtn);
        otpbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Signup.this, Otp_page.class);
                startActivity(intent);
            }
        });
    }
}