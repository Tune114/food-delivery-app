package com.study.fooddeliveryapplication.ui;

import static android.provider.MediaStore.ACTION_IMAGE_CAPTURE;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.study.fooddeliveryapplication.R;

public class UserProflie extends AppCompatActivity {
    EditText phone,address,pass;
    TextView name,email,setting;
    Button home, logout,addimage;
    ImageView anh;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = database.getReference("users");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_proflie);

        setting = (TextView) findViewById(R.id.tvsetting);
        name = (TextView) findViewById(R.id.tvname);
        email = (TextView) findViewById(R.id.tvemail);
        phone = (EditText) findViewById(R.id.tvphone);
        address = (EditText) findViewById(R.id.tvAddress);
        pass = (EditText) findViewById(R.id.tvpass);
        home = (Button) findViewById(R.id.btnhome);
        logout = (Button) findViewById(R.id.btnlogout);
        addimage = (Button) findViewById(R.id.btnaddimage);
        anh = (ImageView) findViewById(R.id.anh);

        //phone.setText(String.format("Phone Number:          "+getIntent().getStringExtra("phone")));
        //email.setText(getIntent().getStringExtra("email"));
        //pass.setText("Your Password:           "+getIntent().getStringExtra("password"));
        //name.setText(getIntent().getStringExtra("name"));
        //address.setText("Address:                      "+getIntent().getStringExtra("address"));
        SharedPreferences sharedPreferences = getSharedPreferences("my_app_preferences", Context.MODE_PRIVATE);
        String phonenumber = sharedPreferences.getString("phone", null);
        String Name = sharedPreferences.getString("name", null);
        String Email = sharedPreferences.getString("email", null);
        String password = sharedPreferences.getString("password", null);
        String Address = sharedPreferences.getString("address", null);

        phone.setText("Phone Number:          "+phonenumber);
        email.setText(Email);
        pass.setText("Your Password:           "+password);
        address.setText("Address:                      "+Address);
        name.setText(Name);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(UserProflie.this, HomePageActivity.class);
                startActivity(i);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(UserProflie.this,LoginActivity.class);
                startActivity(i);
            }
        });

        addimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cam = new Intent(ACTION_IMAGE_CAPTURE);

                if(ActivityCompat.checkSelfPermission(UserProflie.this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(UserProflie.this,new String[]{Manifest.permission.CAMERA},1);
                    return;
                }

                startActivityForResult(cam,99);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==99 && resultCode == Activity.RESULT_OK){
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            anh.setImageBitmap(photo);
        }
    }

}