package com.example.digi_yatra_12.fragments;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.digi_yatra_12.R;
import com.example.digi_yatra_12.navbar.Navbar_main;

public class Id_wallet extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_id_wallet);


       ImageButton pop = (ImageButton) findViewById(R.id.imageButton5);
        pop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent =new Intent(Id_wallet.this,Credentital_details.class);
                startActivity(intent);


            }
        });
        Button home = (Button) findViewById(R.id.HomeBtn);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent =new Intent(Id_wallet.this, Navbar_main.class);
                startActivity(intent);


            }
        });
    }
}