package com.example.digi_yatra_12.fragments;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.digi_yatra_12.R;

public class retrieved_addheerdata extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieved_addheerdata);
        Button popup = (Button) findViewById(R.id.ProcedBtn);
        popup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(retrieved_addheerdata.this, Alomst_done.class);
                startActivity(i);

            }
        });
    }
}