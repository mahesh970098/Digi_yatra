package com.example.digi_yatra_12;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.digi_yatra_12.navbar.Navbar_main;

public class Pop_sucess_register extends AppCompatActivity {
    Button popup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_sucess_register);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width * .99), (int) (height * .4));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER_HORIZONTAL;
        params.x = 0;
        params.y = 100;

        getWindow().setAttributes(params);


        Button i =(Button) findViewById( R.id.OkBtn2 );
        i.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in =new Intent(Pop_sucess_register.this, Navbar_main.class );
                startActivity( in );
            }
        } );


    }
}