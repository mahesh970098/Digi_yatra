package com.example.digi_yatra_12.fragments;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.digi_yatra_12.R;
import com.example.digi_yatra_12.navbar.Navbar_main;

public class Sucessufully_reg2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sucessufully_reg2);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                final Intent mainIntent = new Intent(Sucessufully_reg2.this, Navbar_main.class);
                Sucessufully_reg2.this.startActivity(mainIntent);
                Sucessufully_reg2.this.finish();
            }
        }, 1000);
    }
}