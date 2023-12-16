package com.study.fooddeliveryapplication.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.study.fooddeliveryapplication.R;

import java.util.ArrayList;
import java.util.Random;

import com.study.fooddeliveryapplication.R;

public class VerificationActivity extends AppCompatActivity {
    EditText inputcode1,inputcode2,inputcode3,inputcode4,inputcode5,inputcode6;
    ImageView backpagefgpass;
    TextView getphone , resend;
    String mess = "is your password ";
    String otp = createRandomNumber();
    String yourpass;
    Button btnxacnhan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        backpagefgpass = (ImageView) findViewById(R.id.backpagefgpass);
        resend = (TextView) findViewById(R.id.Resend);
        inputcode1 = (EditText) findViewById(R.id.inputcode1);
        inputcode2 = (EditText) findViewById(R.id.inputcode2);
        inputcode3 = (EditText) findViewById(R.id.inputcode3);
        inputcode4 = (EditText) findViewById(R.id.inputcode4);
        inputcode5 = (EditText) findViewById(R.id.inputcode5);
        inputcode6 = (EditText) findViewById(R.id.inputcode6);
        btnxacnhan = (Button) findViewById(R.id.btnxn);
        getphone = (TextView) findViewById(R.id.getphone);
        getphone.setText(String.format(getIntent().getStringExtra("phone")));
        String phone = String.valueOf(getphone);
        yourpass = getIntent().getStringExtra("yourpass");
        String OTP = getIntent().getStringExtra("OTP");
        setUpOTPInput();

        btnxacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inputcode1.getText().toString().trim().isEmpty() ||
                        inputcode2.getText().toString().trim().isEmpty() ||
                        inputcode3.getText().toString().trim().isEmpty() ||
                        inputcode4.getText().toString().trim().isEmpty() ||
                        inputcode5.getText().toString().trim().isEmpty() ||
                        inputcode6.getText().toString().trim().isEmpty()){
                    Toast.makeText(VerificationActivity.this, "Please enter complete information!", Toast.LENGTH_SHORT).show();
                    return;
                }
                String code = inputcode1.getText().toString() + inputcode2.getText().toString() + inputcode3.getText().toString() +
                        inputcode4.getText().toString() + inputcode5.getText().toString() + inputcode6.getText().toString() ;

                if(code.equals(OTP) || code.equals(otp)){
                    SmsManager smsManager = SmsManager.getDefault();
                    ArrayList<String> parts = smsManager.divideMessage(yourpass+": "+mess);

                    smsManager.sendMultipartTextMessage(phone,null,parts,null,null);
                    Toast.makeText(getApplicationContext(),"Your password has send to your phone!",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(VerificationActivity.this, LoginActivity.class);
                    startActivity(i);
                }else{
                    Toast.makeText(getApplicationContext(),"OTP is incorrect!",Toast.LENGTH_SHORT).show();
                }
            }
        });

        backpagefgpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VerificationActivity.this, ForgotPassActivity.class);
                startActivity(intent);
            }
        });

        resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SmsManager smsManager = SmsManager.getDefault();
                ArrayList<String> parts = smsManager.divideMessage(otp+": "+mess);
                String phoneNumber = phone;
                smsManager.sendMultipartTextMessage(phoneNumber,null,parts,null,null);
                Toast.makeText(getApplicationContext(),"OTP has resend!",Toast.LENGTH_SHORT).show();

            }
        });
    }

    private String createRandomNumber() {
        int randomNumber = new Random().nextInt(999999);
        String formattedNumber = String.format("%06d", randomNumber);

        return formattedNumber;
    }

    private void setUpOTPInput(){
        inputcode1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().trim().isEmpty()){
                    inputcode2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        inputcode2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().trim().isEmpty()){
                    inputcode3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        inputcode3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().trim().isEmpty()){
                    inputcode4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        inputcode4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().trim().isEmpty()){
                    inputcode5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        inputcode5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().trim().isEmpty()){
                    inputcode6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}
