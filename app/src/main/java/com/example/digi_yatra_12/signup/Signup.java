package com.example.digi_yatra_12.signup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.ColorSpace;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.digi_yatra_12.MainActivity;
import com.example.digi_yatra_12.R;

public class Signup extends AppCompatActivity {
    Button otpbtn;
    EditText enternumber;
    Button getotpbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        otpbtn =findViewById(R.id.RegisterBtn);
        otpbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Signup.this, Otp_page.class);
                startActivity(intent);
            }
        });

        enternumber = findViewById(R.id.editTextTextPassword);
        getotpbutton = findViewById(R.id.RegisterBtn);
//        ProgressBar progressBar = findViewById(R.id.progressbar);
        getotpbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent =new Intent(Signin_Mobile.this, Password_otp.class);
//                startActivity(intent);
//                saveUser(createRequest());

                if (!enternumber.getText().toString().trim().isEmpty()) {
                    if ((enternumber.getText().toString().trim().length() == 10)) {

//                        otpbtn.setBackgroundColor(Color.GREEN);



                        Intent intent =new Intent(Signup.this, Otp_page.class);
                        intent.putExtra("mobile", enternumber.getText().toString());
                        startActivity(intent);

//                        progressBar.setVisibility(View.VISIBLE);
//                        getotpbutton.setVisibility(View.INVISIBLE);
//                        cognito.signUpInBackground("+91" + binding.mobileEdit.getText().toString(), "Test@123", signUpCallback);

//                        phoneAuthProvider.getInstance().verifyPhoneNumber(
//                                "+91" + enternumber.getText().toString(),
//                                60,
//                                TimeUnit.SECONDS,
//                                Signin_Mobile.this,
//                                new phoneAuthProvider.OnverificationStateChangedCallbacks() {

//                                        Intent intent = new Intent(getApplicationContext(), Password_otp.class);
//                                        intent.putExtra("mobile", enternumber.getText().toString());
//                                        intent.putExtra("mobile",enternumber.getText().toString());
////                                        intent.putExtra("backendotp",backendotp);
//                                        startActivity(intent);

                    } else {
                        Toast.makeText(Signup.this, "Please enter Correct mobile number", Toast.LENGTH_LONG);

                    }

                } else {
                    Toast.makeText(Signup.this, "Enter Mobile Number", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}