package com.example.digi_yatra_12.fragments;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

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
                Intent intent =new Intent(Camera_profile2.this, Id_wallet.class);
                startActivity(intent);
            }
        });






    }
}