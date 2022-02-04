package com.example.digi_yatra_12.fragments;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.digi_yatra_12.R;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class Update_your_travel extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_your_travel);


        ImageButton popup = (ImageButton) findViewById(R.id.imageView7);
        popup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator intentIntegrator = new IntentIntegrator(Update_your_travel.this);
                intentIntegrator.setPrompt("Scan a barcode or QR Code");
                intentIntegrator.setOrientationLocked(false);

                intentIntegrator.initiateScan();

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
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        // if the intentResult is null then
        // toast a message as "cancelled"
        if (intentResult != null) {
            if (intentResult.getContents() == null) {
                Toast.makeText(getBaseContext(), "Cancelled", Toast.LENGTH_SHORT).show();
            } else {

                String scan_result = intentResult.getContents();
                String scan_format = intentResult.getFormatName();
//                messageText.setText(scan_result);
//                messageFormat.setText(scan_format);
                System.out.println(scan_result);
//                String valuesContent = intentResult.getContents();
//                message.setText(valuesContent);
                Intent intent =new Intent(Update_your_travel.this, Scan_bording_pass.class);
                intent.putExtra("values", scan_result);

                startActivity(intent);




            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }


    }
}