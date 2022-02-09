package com.example.digi_yatra_12.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.digi_yatra_12.R;
import com.example.digi_yatra_12.navbar.Navbar_main;

public class Camera_profile2 extends AppCompatActivity {
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_profile2);

        Button btn =(Button) findViewById(R.id.OkBtn3);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Camera_profile2.this, Navbar_main.class);
                startActivity(intent);
            }
        });

        Button btn1 =(Button) findViewById(R.id.cancel_action);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Camera_profile2.this, Alomst_done.class);
                startActivity(intent);
            }
        });



        ImageButton ib = (ImageButton)findViewById(R.id.backBtn1);
        ib.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}