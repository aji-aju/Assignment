package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.material.snackbar.Snackbar;

public class OtpActivity extends AppCompatActivity {

    EditText editOtp1;
    EditText editOtp2;
    EditText editOtp3;
    EditText editOtp4;
    Button buttonResend;
    Button buttonSubmit;
    ImageView otpBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        editOtp1 = (EditText) findViewById(R.id.editTextNumber_otp_otp1);
        editOtp2 = (EditText) findViewById(R.id.editTextNumber_otp_otp2);
        editOtp3 = (EditText) findViewById(R.id.editTextNumber_otp_otp3);
        editOtp4 = (EditText) findViewById(R.id.editTextNumber_otp_otp4);
        buttonResend = (Button) findViewById(R.id.button_otp_resend);
        buttonSubmit = (Button) findViewById(R.id.button_otp_submit);
        otpBack = (ImageView) findViewById(R.id.image_otp_back);

        editOtp1.requestFocus();

        editOtp1.addTextChangedListener(new GenericTextWatcher(editOtp2, editOtp1));
        editOtp2.addTextChangedListener(new GenericTextWatcher(editOtp3, editOtp1));
        editOtp3.addTextChangedListener(new GenericTextWatcher(editOtp4, editOtp2));
        editOtp4.addTextChangedListener(new GenericTextWatcher(editOtp4, editOtp3));

        editOtp1.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(editOtp1.getText().toString().isEmpty() && keyCode == KeyEvent.KEYCODE_DEL){
                    editOtp1.requestFocus();

                }
                if(!editOtp1.getText().toString().isEmpty() && keyCode >= 7 && keyCode<=16){
                    editOtp2.requestFocus();
                    editOtp2.dispatchKeyEvent(event);
                }
                return false;

            }
        });

        editOtp2.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(editOtp2.getText().toString().isEmpty() && keyCode == KeyEvent.KEYCODE_DEL){
                    editOtp1.requestFocus();

                }
                if(!editOtp2.getText().toString().isEmpty() && keyCode >= 7 && keyCode<=16){
                    editOtp3.requestFocus();
                    editOtp3.dispatchKeyEvent(event);

                }
                return false;
            }
        });

        editOtp3.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(editOtp3.getText().toString().isEmpty() && keyCode == KeyEvent.KEYCODE_DEL){
                    editOtp2.requestFocus();

                }
                if(!editOtp3.getText().toString().isEmpty() && keyCode >= 7 && keyCode<=16){
                    editOtp4.requestFocus();
                    editOtp4.dispatchKeyEvent(event);

                }
                return false;
            }
        });

        editOtp4.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(editOtp4.getText().toString().isEmpty() && keyCode == KeyEvent.KEYCODE_DEL){
                    editOtp3.requestFocus();

                }
                if(!editOtp4.getText().toString().isEmpty() && keyCode >= 7 && keyCode<=16){
                    editOtp4.requestFocus();

                }
                return false;
            }
        });

        buttonResend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editOtp1.setText("");
                editOtp2.setText("");
                editOtp3.setText("");
                editOtp4.setText("");
                editOtp1.requestFocus();
                Snackbar snackbar  = Snackbar.make(buttonResend, "OTP is send again",Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        });

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder otp = new StringBuilder();
                otp.append(editOtp1.getText().toString());
                otp.append(editOtp2.getText().toString());
                otp.append(editOtp3.getText().toString());
                otp.append(editOtp4.getText().toString());
                if(editOtp1.getText().toString().isEmpty() || editOtp2.getText().toString().isEmpty()||
                editOtp3.getText().toString().isEmpty() || editOtp4.getText().toString().isEmpty()){
                    Snackbar snackbar  = Snackbar.make(buttonSubmit, "Please enter an OTP",Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
                else if(otp.toString().equals("1234")){
                    Intent welcomeIntent = new Intent(OtpActivity.this,WelcomeActivity.class);
                    startActivity(welcomeIntent);
                }
                else{
                    Snackbar snackbar  = Snackbar.make(buttonSubmit, "Invalid OTP",Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
            }
        });

        otpBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registrationIntent = new Intent(OtpActivity.this,RegistrationActivity.class);
                startActivity(registrationIntent);
            }
        });



    }

}

