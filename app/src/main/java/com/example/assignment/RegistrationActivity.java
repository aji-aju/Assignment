package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class RegistrationActivity extends AppCompatActivity {

    private static final String REGEX = "^([a-zA-Z0-9_\\-.]+)"
            +"@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)"
            +"|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(]?)$";

    EditText registrationName;
    EditText registrationEmail;
    TextInputEditText registrationPassword;
    ImageView registrationBack;
    TextView registrationSignInText;
    Button buttonSubmit;
    CheckBox checkBox;
    CoordinatorLayout coordinatorLayoutRegistration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        registrationName = (EditText) findViewById(R.id.editText_registration_PersonName);
        registrationEmail = (EditText) findViewById(R.id.editText_registration_EmailAddress);
        registrationPassword = (TextInputEditText)  findViewById(R.id.textInputEditText_registration_password);
        registrationBack = (ImageView) findViewById(R.id.image_registration_back);
        registrationSignInText = (TextView) findViewById(R.id.text_registration_signIn);
        buttonSubmit = (Button) findViewById(R.id.button_registration_submit);
        checkBox = (CheckBox) findViewById(R.id.checkBox_registration_termsAndConditions);
        coordinatorLayoutRegistration = (CoordinatorLayout) findViewById(R.id.coordinatorLayout_registration);

        SpannableString spannableString = new SpannableString(checkBox.getText().toString());
        ForegroundColorSpan foregroundColorSpanGreen = new ForegroundColorSpan(Color.rgb(21,109,0));
        spannableString.setSpan(foregroundColorSpanGreen,15,35, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        checkBox.setText(spannableString);

        registrationBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(RegistrationActivity.this,LoginActivity.class);
                startActivity(loginIntent);
            }
        });

        registrationSignInText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(RegistrationActivity.this,LoginActivity.class);
                startActivity(loginIntent);
            }
        });

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(registrationName.getText().toString().isEmpty()){
                    Snackbar snackbar  = Snackbar.make(coordinatorLayoutRegistration,
                            "Please enter full Name",Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
                else if(registrationEmail.getText().toString().isEmpty()){
                    Snackbar snackbar  = Snackbar.make(coordinatorLayoutRegistration,
                            "Please enter an Email Address",Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
                else if(Objects.requireNonNull(registrationPassword.getText()).toString().isEmpty()){
                    Snackbar snackbar  = Snackbar.make(coordinatorLayoutRegistration,
                            "Please enter a Password",Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
                else if(!registrationEmail.getText().toString().matches(REGEX)){
                    Snackbar snackbar  = Snackbar.make(coordinatorLayoutRegistration,
                            "Please enter a valid Email Address",Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
                else if(!checkBox.isChecked()){
                    Snackbar snackbar  = Snackbar.make(coordinatorLayoutRegistration,
                            "Please agree  to our terms and conditions",Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
                else //noinspection SpellCheckingInspection
                    if(!(registrationEmail.getText().toString().equals("admin@gmail.com") &&
                        registrationPassword.getText().toString().equals("qwertyui"))){
                    Snackbar snackbar  = Snackbar.make(coordinatorLayoutRegistration,
                            "Incorrect Email or Password",Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
                else{
                    Intent otpIntent = new Intent(RegistrationActivity.this,OtpActivity.class);
                    startActivity(otpIntent);
                }

            }
        });
    }
}