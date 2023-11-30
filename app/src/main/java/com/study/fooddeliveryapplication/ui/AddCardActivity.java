package com.study.fooddeliveryapplication.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.study.fooddeliveryapplication.R;

public class AddCardActivity extends AppCompatActivity {

    private ImageView btnBack;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);

        btnBack= (ImageView)findViewById(R.id.btn_back);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(AddCardActivity.this, PaymentActivity.class);
                startActivity(intent);

            }
        });
    }
}