package com.example.digi_yatra_12.fragments;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.digi_yatra_12.R;

public class Credential_Choose extends AppCompatActivity {
    boolean check = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credential_choose);

        ImageButton popup = (ImageButton) findViewById(R.id.identitialBtn);
        popup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check = true;
                popup.setColorFilter(Color.rgb(
                        234, 243, 252
                ));
                Intent i = new Intent(Credential_Choose.this, Pop_issuer.class);
                startActivity(i);

            }
        });

        if (check == true) {
            popup.setBackgroundColor(Color.BLACK);

        }
//        Intent i = new Intent(Credential_Choose.this, Pop_issuer.class);
//        startActivity(i);

//        final Button button = (Button) findViewById(R.id.button_id);
//        button.setOnTouchListener(new View.OnTouchListener() {
//
//            @Override
//            public boolean onTouch(View view, MotionEvent event) {
//                if(event.getAction() == MotionEvent.ACTION_UP) {
//                    button.setBackgroundColor(Color.RED);
//                } else if(event.getAction() == MotionEvent.ACTION_DOWN) {
//                    button.setBackgroundColor(Color.BLUE);
//                }
//                return false;
//            }
//
//        });
    }
}