package com.example.digi_yatra_12.fragments;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.digi_yatra_12.R;

public class Pop_acknowledgement extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_acknowledgement);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width * .91), (int) (height * .5));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER_HORIZONTAL;
        params.x = 0;
        params.y = 50;

        getWindow().setAttributes(params);

        Button btn=(Button) findViewById(R.id.OkBtn2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(Pop_acknowledgement.this,Add_data_url.class);
                startActivity(i);
            }
        });
        Button btn1=(Button) findViewById(R.id.cancel_action);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(Pop_acknowledgement.this,Credential_Choose.class);
                startActivity(i);
            }
        });

    }
}