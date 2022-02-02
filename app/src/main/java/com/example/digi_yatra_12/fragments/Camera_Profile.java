package com.example.digi_yatra_12.fragments;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.example.digi_yatra_12.R;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class Camera_Profile extends AppCompatActivity {
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_profile);

     this.imageView = (ImageView)this.findViewById(R.id.profile_image);
        Intent intent = getIntent();
        Bitmap photo = (Bitmap) intent.getExtras().get("data");
        imageView.setImageBitmap(photo);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                final Intent mainIntent = new Intent(Camera_Profile.this, Camera_profile2.class);
                Camera_Profile.this.startActivity(mainIntent);
                Camera_Profile.this.finish();
            }
        }, 3000);





    }
}