package com.example.assignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    private static final String REGEX = "^([a-zA-Z0-9_\\-.]+)"
            +"@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)"
            +"|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(]?)$";

    EditText loginEmailAddress;
    TextInputEditText loginPasswordText;
    TextView loginRegisterText;
    Button buttonLogin;
    CoordinatorLayout coordinatorLayoutLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginEmailAddress = (EditText) findViewById(R.id.editText_login_EmailAddress);
        loginPasswordText = (TextInputEditText) findViewById(R.id.textInputEditText_login_password);
        buttonLogin = (Button) findViewById(R.id.button_login);
        coordinatorLayoutLogin = (CoordinatorLayout) findViewById(R.id.coordinatorLayout_login);
        loginRegisterText = (TextView) findViewById(R.id.text_login_to_register);

        SpannableString spannableString = new SpannableString(loginRegisterText.getText().toString());
        ForegroundColorSpan foregroundColorSpanGrey = new ForegroundColorSpan(Color.rgb(148,148,148));
        ForegroundColorSpan foregroundColorSpanGreen = new ForegroundColorSpan(Color.rgb(21,109,0));
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Intent registrationIntent= new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(registrationIntent);
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                ds.setColor(Color.rgb(21,109,0));
                ds.setUnderlineText(false);
            }
        };

        spannableString.setSpan(foregroundColorSpanGrey,0,22, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(foregroundColorSpanGreen,23,31,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(clickableSpan,23,31,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        loginRegisterText.setText(spannableString);
        loginRegisterText.setMovementMethod(LinkMovementMethod.getInstance());

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @SuppressWarnings("SpellCheckingInspection")
            @Override
            public void onClick(View v) {
                if(loginEmailAddress.getText().toString().isEmpty()){
                    Snackbar snackbar  = Snackbar.make(coordinatorLayoutLogin,
                            "Please enter an Email Address",Snackbar.LENGTH_LONG);
                    snackbar.show();

                }
                else if(Objects.requireNonNull(loginPasswordText.getText()).toString().isEmpty()){
                    Snackbar snackbar  = Snackbar.make(coordinatorLayoutLogin,
                            "Please enter a password",Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
                else if(!loginEmailAddress.getText().toString().matches(REGEX)){
                    Snackbar snackbar  = Snackbar.make(coordinatorLayoutLogin,
                            "Please enter a valid Email Address",Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
                else if(!(loginEmailAddress.getText().toString().equals("admin@gmail.com") &&
                        loginPasswordText.getText().toString().equals("qwertyui"))){
                    Snackbar snackbar  = Snackbar.make(coordinatorLayoutLogin,
                            "Incorrect Email or Password",Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
                else{
                    Intent welcomeIntent = new Intent(LoginActivity.this,WelcomeActivity.class);
                    startActivity(welcomeIntent);
                }


            }
        });

    }


}