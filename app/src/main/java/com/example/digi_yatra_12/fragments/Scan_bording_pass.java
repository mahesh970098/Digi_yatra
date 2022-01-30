package com.example.digi_yatra_12.fragments;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.digi_yatra_12.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Scan_bording_pass extends AppCompatActivity {
    TextView details;
    Button Sucess;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_bording_pass);
        Intent intent = getIntent();
        String text = intent.getStringExtra("values");

        String[] newStr = text.split("\\s+");
        String x = "";
        for (int i = 0; i < newStr.length; i++) {
            System.out.println(newStr[i]);
            x=x+newStr[i]+" ";
        }

        details=findViewById(R.id.textView38);
        String[] newStr1 = newStr[0].split("/");
        details.setText(newStr1[0].substring(2));


        details=findViewById(R.id.textView22);
        String[] newStr2 = newStr[0].split("/");
        details.setText(newStr2[1]);

//flight no
        details=findViewById(R.id.textView39);
        details.setText(newStr[2].substring(6)+newStr[3]);

//From
        details=findViewById(R.id.textView27);

        details.setText(newStr[2].substring(0,newStr.length -5));
//TO
        details=findViewById(R.id.textView30);
        details.setText(newStr[2].substring(3,newStr.length -2));
//AirlineCode
//        details=findViewById(R.id.textView42);
//        details.setText(newStr[3]);
// Date

        details=findViewById(R.id.textView43);

        String dayOfYear = newStr[4].substring(0,newStr.length -5);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, Integer.parseInt(dayOfYear));
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM");
//      System.out.println("Day of year " + dayOfYear + " = " + sdf.format(calendar.getTime()));

        details.setText(sdf.format(calendar.getTime()));
//PNR

        details=findViewById(R.id.textView46);
        details.setText(newStr[1]);
//SEQUNCE

        details=findViewById(R.id.textView48);
        details.setText(newStr[4].substring(8));
//SEAT
        details=findViewById(R.id.textView50);
        details.setText(newStr[4].substring(4,newStr.length -0));







        Sucess =findViewById(R.id.AceptBtn);
        Sucess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Scan_bording_pass.this, Sucessufully_reg2.class);
                startActivity(intent);
            }
        });




    }

}