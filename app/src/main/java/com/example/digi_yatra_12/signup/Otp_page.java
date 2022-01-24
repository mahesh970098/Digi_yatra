package com.example.digi_yatra_12.signup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.digi_yatra_12.R;
import com.example.digi_yatra_12.navbar.Navbar_main;

public class Otp_page extends AppCompatActivity {
Button home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_page);
        home =findViewById(R.id.SubmitBtn);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Otp_page.this, Navbar_main.class);
                startActivity(intent);
            }
        });
    }
}