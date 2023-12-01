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

public class LoginActivity extends AppCompatActivity {
    TextView tvforgotpass,tvsignup;
    EditText phoneedit,passwordedit;
    Button btnlogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tvforgotpass = (TextView) findViewById(R.id.tvforgotpass);
        tvsignup = (TextView) findViewById(R.id.tvsignup);
        phoneedit = (EditText) findViewById(R.id.phone);
        passwordedit = (EditText) findViewById(R.id.password);
        btnlogin = (Button) findViewById(R.id.btnlogin) ;

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login();
            }
        });

        tvforgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, ForgotPassActivity.class);
                startActivity(intent);
            }
        });

        tvsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });
    }

    private void Login(){
        String phone,password;
        phone = phoneedit.getText().toString();
        password = passwordedit.getText().toString();
        if (TextUtils.isEmpty(phone)){
            Toast.makeText(this,"Xin hãy nhập số điện thoại!",Toast.LENGTH_SHORT).show();
            return;
        }
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference();


        if (phone.isEmpty() || password.isEmpty() ){
            Toast.makeText(LoginActivity.this,"Xin hãy nhập đủ thông tin!",Toast.LENGTH_SHORT).show();
        }
        else {
            databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.hasChild(phone)){

                        String getPass = snapshot.child(phone).child("password").getValue(String.class);
                        String getmail = snapshot.child(phone).child("email").getValue(String.class);
                        String getname = snapshot.child(phone).child("name").getValue(String.class);
                        String getaddress = snapshot.child(phone).child("address").getValue(String.class);

                        if (getPass.equals(password)){
                            Toast.makeText(LoginActivity.this,"Đăng nhập thành công",Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(LoginActivity.this, UserProflie.class);
                            intent.putExtra("phone",phone);
                            intent.putExtra("password",getPass);
                            intent.putExtra("email",getmail);
                            intent.putExtra("name",getname);
                            intent.putExtra("address",getaddress);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(LoginActivity.this,"Đăng nhập thất bại!",Toast.LENGTH_SHORT).show();

                        }
                    }else{
                        Toast.makeText(LoginActivity.this,"Thông tin số điện thoại hoặc mật khẩu sai!!",Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }
}