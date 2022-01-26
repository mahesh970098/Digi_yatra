package com.example.digi_yatra_12.fragments;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
    }
}