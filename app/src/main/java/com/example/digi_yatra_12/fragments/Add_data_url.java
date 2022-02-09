package com.example.digi_yatra_12.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.digi_yatra_12.R;

public class Add_data_url extends AppCompatActivity {
Button urlBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data_url);
        urlBtn = (Button)findViewById(R.id.UrlBtn);

        urlBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String url = "https://api.digitallocker.gov.in/public/oauth2/1/authorize?client_id=B3F1B8A6&redirect_uri=https://www.DigiYatraFoundation.com/&state=1234a&response_type=code";
                Uri uri =Uri.parse(url);
                Intent intent =new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);

                finish();


            }
        });

        ImageButton popup = (ImageButton) findViewById(R.id.imageBtn);
        popup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Add_data_url.this, retrieved_addheerdata.class);
                startActivity(i);
                finish();

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