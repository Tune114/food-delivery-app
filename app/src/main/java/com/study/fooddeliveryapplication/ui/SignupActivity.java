package com.study.fooddeliveryapplication.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.study.fooddeliveryapplication.R;

public class SignupActivity extends AppCompatActivity {
    TextView tvHaveacc;
    EditText editphone,editemail,epassword,editrepass;
    Button btnsignup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        tvHaveacc = (TextView) findViewById(R.id.tvHaveacc);
        editphone = (EditText) findViewById(R.id.editTextPhone);
        epassword = (EditText) findViewById(R.id.editTextPassword);
        editemail = (EditText) findViewById(R.id.editTextEmail);
        editrepass = (EditText) findViewById(R.id.editTextRePassword);
        btnsignup = (Button) findViewById(R.id.btnsignup) ;

        tvHaveacc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Signup();
            }
        });
    }
    private void Signup(){
        String phone,email,password,repass;
        email = editemail.getText().toString();
        password = epassword.getText().toString();
        phone = editphone.getText().toString();
        repass = editrepass.getText().toString();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference();

        if (email.isEmpty() || password.isEmpty() || phone.isEmpty() || repass.isEmpty()){
            Toast.makeText(SignupActivity.this,"Xin điền đầy đủ thông tin!",Toast.LENGTH_SHORT).show();
        }
        else {
            databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.hasChild(phone)){
                        Toast.makeText(SignupActivity.this,"Số điện thoại đã tồn tại",Toast.LENGTH_SHORT).show();
                    } else if (!password.equals(repass)) {
                        Toast.makeText(SignupActivity.this,"Mật khẩu không trùng khớp",Toast.LENGTH_SHORT).show();
                    } else {
                        databaseReference.child("users").child(phone).child("email").setValue(email);
                        databaseReference.child("users").child(phone).child("password").setValue(password);

                        Toast.makeText(SignupActivity.this,"Đăng ký thành công",Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(SignupActivity.this,LoginActivity.class);
                        startActivity(i);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

    }

}