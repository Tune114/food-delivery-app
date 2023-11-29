package com.study.fooddeliveryapplication.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.study.fooddeliveryapplication.R;

public class ForgotPassActivity extends AppCompatActivity {
    ImageView backpage;
    Button btnsendcode;
    EditText inputemail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);

        btnsendcode = (Button) findViewById(R.id.btnsendcode);
        backpage = (ImageView) findViewById(R.id.backpage);
        inputemail = (EditText) findViewById(R.id.inputemail);

        btnsendcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (inputemail.getText().toString().trim().isEmpty()){
                    Toast.makeText(ForgotPassActivity.this, "Nhập email", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(ForgotPassActivity.this, VerificationActivity.class);
                startActivity(intent);
            }
        });

        backpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ForgotPassActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}