package com.example.digi_yatra_12.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.digi_yatra_12.R;

public class Pop_issuer extends AppCompatActivity {
    String senderUrl = "http://ab6b1f47c653a4ad9be235211726df19-222719493.ap-south-1.elb.amazonaws.com:10093/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_issuer);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width * .99), (int) (height * .6));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER_HORIZONTAL;
        params.x = 0;
        params.y = 400;

        getWindow().setAttributes(params);

        ImageButton btn =(ImageButton) findViewById(R.id.popisuerBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Pop_issuer.this,Pop_acknowledgement.class);
                startActivity(intent);
            }
        });


    }
}