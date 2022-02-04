package com.example.digi_yatra_12.signup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.digi_yatra_12.Pop_sucess_register;
import com.example.digi_yatra_12.R;
import com.example.digi_yatra_12.fragments.Pop_acknowledgement;
import com.example.digi_yatra_12.navbar.Navbar_main;

public class Otp_page extends AppCompatActivity {
Button home;
    EditText inputNumber1,inputNumber2,inputNumber3,inputNumber4;
    String getotpbackend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_page);
//        home =findViewById(R.id.SubmitBtn);
//        home.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent =new Intent(Otp_page.this, Navbar_main.class);
//                startActivity(intent);
//            }
//        });

        Button verifybuttonclick =findViewById(R.id.SubmitBtn);

        inputNumber1 =findViewById(R.id.inputCode1);
        inputNumber2 =findViewById(R.id.inputCode2);
        inputNumber3 =findViewById(R.id.inputCode3);
        inputNumber4 =findViewById(R.id.inputCode4);


        TextView textView =findViewById(R.id.textView7);
        textView .setText(String.format(
                "+91-%s",getIntent().getStringExtra("mobile")
        ));


        getotpbackend =getIntent().getStringExtra("backendotp");




        verifybuttonclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(!inputNumber1.getText().toString().trim().isEmpty()&&!inputNumber2.getText().toString().trim().isEmpty()&&!inputNumber3.getText().toString().trim().isEmpty()&&!inputNumber4.getText().toString().trim().isEmpty()){



                    LayoutInflater inflater =getLayoutInflater();
                    View layout =inflater.inflate(R.layout.custom_toast,(ViewGroup) findViewById(R.id.toast_layout));
                    final Toast toast = new Toast(getApplicationContext());
                    toast.setGravity(Gravity.CENTER_VERTICAL,0,0);
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.setView(layout);
                    toast.show();

//                    Toast.makeText(Otp_page.this,"otp verify",Toast.LENGTH_SHORT ).show();

                    Intent intent = new Intent(getApplicationContext(), Navbar_main.class);
                    startActivity(intent);



                    String entercodeotp = inputNumber1.getText().toString()+
                            inputNumber2.getText().toString()+
                            inputNumber3.getText().toString()+
                            inputNumber4.getText().toString();

//                if(getotpbackend!=null){
//
//                    phoneAuthCredntial phoneAuthCrenditial =PhoneAuthProvider.getCredential(
//                            getotpbackend,entercodeotp;
//                    )
//
//
//                }else{
//                    Toast.makeText(Password_otp.this,"no"Tost.)
//                }


                }else {
                    Toast.makeText(Otp_page.this,"Enter OTP",Toast.LENGTH_SHORT).show();
                }


            }
        });
        numberotpmove();



    }

    private void numberotpmove() {
        inputNumber1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(!s.toString().trim().isEmpty()){
                    inputNumber2.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        inputNumber2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(!s.toString().trim().isEmpty()){
                    inputNumber3.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        inputNumber3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(!s.toString().trim().isEmpty()){
                    inputNumber4.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }
}