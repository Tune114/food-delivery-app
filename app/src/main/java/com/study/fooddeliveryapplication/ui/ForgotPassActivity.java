package com.study.fooddeliveryapplication.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.study.fooddeliveryapplication.R;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ForgotPassActivity extends AppCompatActivity {
    String mess = "is your OTP code ";
    ImageView backpage;
    Button btnsendcode;
    EditText inputphone;
    int randomnumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);

        btnsendcode = (Button) findViewById(R.id.btnsendcode);
        backpage = (ImageView) findViewById(R.id.backpage);
        inputphone = (EditText) findViewById(R.id.inputphone);

        backpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ForgotPassActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });

        btnsendcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(ForgotPassActivity.this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
                    sendOTP();
                } else {
                    ActivityCompat.requestPermissions(ForgotPassActivity.this, new String[]{Manifest.permission.SEND_SMS}, 100);
                }
            }
        });
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode==100){
            if (grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED);
            sendOTP();
        }else {
            Toast.makeText(ForgotPassActivity.this, "OTP not send!", Toast.LENGTH_SHORT).show();

        }
    }

    private String createRandomNumber() {
        int randomNumber = new Random().nextInt(999999);
        String formattedNumber = String.format("%06d", randomNumber);

        return formattedNumber;
    }
    private void sendOTP() {
        String phone = inputphone.getText().toString();
        if (TextUtils.isEmpty(phone)){
            Toast.makeText(this,"Please enter your phone number!",Toast.LENGTH_SHORT).show();
            return;
        }
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference();
        databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChild(phone)){

                    String getPass = snapshot.child(phone).child("password").getValue(String.class);

                    String otp = createRandomNumber();
                    SmsManager smsManager = SmsManager.getDefault();
                    ArrayList<String> parts = smsManager.divideMessage(otp+": "+mess);
                    String phoneNumber = inputphone.getText().toString();
                    smsManager.sendMultipartTextMessage(phoneNumber,null,parts,null,null);
                    
                    Intent i = new Intent(ForgotPassActivity.this, VerificationActivity.class);
                    i.putExtra("OTP",otp);
                    i.putExtra("phone",phone);
                    i.putExtra("yourpass",getPass);
                    startActivity(i);



                }else{
                    Toast.makeText(getApplicationContext(),"Phone number information has not been signup!",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}
