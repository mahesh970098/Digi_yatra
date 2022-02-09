package com.example.digi_yatra_12;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.digi_yatra_12.signup.Signup;

public class StartActivity extends AppCompatActivity {
    Button home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start );
        home =findViewById(R.id.GetstartedBtn);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent( StartActivity.this, Signup.class);
                startActivity(intent);
            }
        });

    }

}