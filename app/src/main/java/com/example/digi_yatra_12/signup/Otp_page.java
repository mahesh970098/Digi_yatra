package com.example.digi_yatra_12.signup;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.digi_yatra_12.R;
import com.example.digi_yatra_12.navbar.Navbar_main;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

@SuppressWarnings("deprecation")
public class Otp_page extends AppCompatActivity {
    Button home;
    EditText inputNumber1, inputNumber2, inputNumber3, inputNumber4,inputNumber5,inputNumber6;
    String getotpbackend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_otp_page );

//        home =findViewById(R.id.SubmitBtn);
//        home.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent =new Intent(Otp_page.this, Navbar_main.class);
//                startActivity(intent);
//            }
//        });

        final Button verifybuttonclick = findViewById( R.id.SubmitBtn );

        inputNumber1 = findViewById( R.id.inputCode1 );
        inputNumber2 = findViewById( R.id.inputCode2 );
        inputNumber3 = findViewById( R.id.inputCode3 );
        inputNumber4 = findViewById( R.id.inputCode4 );
        inputNumber5 = findViewById( R.id.inputCode5 );
        inputNumber6 = findViewById( R.id.inputCode6 );


        TextView textView = findViewById( R.id.textView7 );
        textView.setText( String.format(
                "+91-%s", getIntent().getStringExtra( "mobile" )
        ) );


        getotpbackend = getIntent().getStringExtra( "backendotp" );


        verifybuttonclick.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (!inputNumber1.getText().toString().trim().isEmpty() && !inputNumber2.getText().toString().trim().isEmpty() && !inputNumber3.getText().toString().trim().isEmpty() && !inputNumber4.getText().toString().trim().isEmpty()) {

                    String entercodeotp = inputNumber1.getText().toString() +
                            inputNumber2.getText().toString() +
                            inputNumber3.getText().toString() +
                            inputNumber4.getText().toString() +
                            inputNumber5.getText().toString() +
                            inputNumber6.getText().toString();
                    if (getotpbackend != null) {

                        verifybuttonclick.setVisibility( View.INVISIBLE );

                        PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(
                                getotpbackend, entercodeotp
                        );
                        FirebaseAuth.getInstance().signInWithCredential( phoneAuthCredential )
                                .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        verifybuttonclick.setVisibility( View.VISIBLE );


                                        if (task.isSuccessful()) {
                                            Intent intent = new Intent( getApplicationContext(), Navbar_main.class );
                                            intent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK );
                                            startActivity( intent );
                                        } else {
                                            Toast.makeText( Otp_page.this, "Enter the correct OTP", Toast.LENGTH_SHORT ).show();
                                        }

                                    }
                                } );

                    } else {
                        Toast.makeText( Otp_page.this, "please check internet connection", Toast.LENGTH_SHORT ).show();

                    }


//                    Toast.makeText(Otp_page.this,"OTP Sucessufully",Toast.LENGTH_SHORT).show();

//                    LayoutInflater inflater =getLayoutInflater();
//                    View layout = inflater.inflate(R.layout.activity_pop_sucess_register,
//                            (ViewGroup) findViewById(R.id.toast_message));
//
//                    Toast toast = new Toast(getApplicationContext());
//                    toast.setGravity( Gravity.CENTER_VERTICAL, 0, 0);
//
//                    toast.setDuration(Toast.LENGTH_LONG);
//                    toast.setView(layout);
//                    toast.show();

//                    Toast.makeText(Otp_page.this,"otp verify",Toast.LENGTH_SHORT ).show();
//
//                    Intent intent = new Intent( getApplicationContext(), Navbar_main.class );
//                    startActivity( intent );


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


                } else {
                    Toast.makeText( Otp_page.this, "Enter OTP", Toast.LENGTH_SHORT ).show();
                }


            }
        } );
        numberotpmove();


        TextView resend = findViewById( R.id. resendotp);

        resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //noinspection deprecation
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+91" + getIntent().getStringExtra( "mobile" ),
                        60,
                        TimeUnit.SECONDS,
                        Otp_page.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {


                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {


                                Toast.makeText( Otp_page.this, e.getMessage(), Toast.LENGTH_SHORT ).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String newbackendotp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {

                                getotpbackend = newbackendotp;
                                Toast.makeText( Otp_page.this, "otp Send sucessufully", Toast.LENGTH_SHORT ).show();

                            }
                        }
                );


            }
        } );

    }


    private void numberotpmove() {
        inputNumber1.addTextChangedListener( new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (!s.toString().trim().isEmpty()) {
                    inputNumber2.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        } );
        inputNumber2.addTextChangedListener( new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (!s.toString().trim().isEmpty()) {
                    inputNumber3.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        } );

        inputNumber3.addTextChangedListener( new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (!s.toString().trim().isEmpty()) {
                    inputNumber4.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        } );
        inputNumber4.addTextChangedListener( new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (!s.toString().trim().isEmpty()) {
                    inputNumber5.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        } );
        inputNumber5.addTextChangedListener( new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (!s.toString().trim().isEmpty()) {
                    inputNumber6.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        } );


    }


}