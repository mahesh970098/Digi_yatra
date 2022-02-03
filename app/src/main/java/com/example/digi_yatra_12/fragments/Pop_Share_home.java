package com.example.digi_yatra_12.fragments;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

import com.example.digi_yatra_12.R;

public class Pop_Share_home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_share_home);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width * .91), (int) (height * .75));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER_HORIZONTAL;
        params.x = 0;
        params.y = 50;

        getWindow().setAttributes(params);
        ImageButton btn =(ImageButton) findViewById(R.id.popisuerBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Pop_Share_home.this,Pop_acknowledgement2.class);
                startActivity(intent);
            }
        });
    }
}