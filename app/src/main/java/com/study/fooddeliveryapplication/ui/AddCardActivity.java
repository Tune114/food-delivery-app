package com.study.fooddeliveryapplication.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.study.fooddeliveryapplication.R;
import com.study.fooddeliveryapplication.model.ModelCardPayment;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddCardActivity extends AppCompatActivity {
    private EditText edCardHolderName, edCardNumber, edCvc, edExDate;
    private Button btnAddNewCard;
    DrawerLayout drawerLayout;
    ConstraintLayout constraintLayout;
    LinearLayout lnHome, lnCart, lnRestaurant, lnProfile;
    private ImageView btnBack,show_more_btn;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);

        // Take text
        edCardHolderName = (EditText)findViewById(R.id.inputCardHolderName);
        edCardNumber =(EditText)findViewById(R.id.inputCardNumber);
        edCvc = (EditText)findViewById(R.id.inputCvc);
        edExDate = (EditText)findViewById(R.id.inputExDate);
        // btnAddNewCard
        btnAddNewCard = (Button)findViewById(R.id.btnComfirmNewCard);
        btnAddNewCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModelCardPayment modelCardPayment = new ModelCardPayment();
                modelCardPayment.setCardName("visa");
                modelCardPayment.setCardIcon("https://2.bp.blogspot.com/-JtxLIr8Jhw4/XDQRfkIGZMI/AAAAAAAAABQ/q9m6GgAas-Q74T1kKuBRhf1Puk9ao-1HgCLcBGAs/w1200-h630-p-k-no-nu/Paypal-logo-pp-2014.png");
                modelCardPayment.setCardNumber(edCardNumber.getText().toString());
                modelCardPayment.setCardHolderName(edCardHolderName.getText().toString());
                modelCardPayment.setCvc(edCvc.getText().toString());
                modelCardPayment.setExpireDate(edExDate.getText().toString());
                if(modelCardPayment.getCardHolderName().equals("")){
                    Toast.makeText(AddCardActivity.this, "CardHolderName is empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(modelCardPayment.getCvc().equals("")){
                    Toast.makeText(AddCardActivity.this, "Cvc is empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(modelCardPayment.getExpireDate().equals("")){
                    Toast.makeText(AddCardActivity.this, "ExpireDate is empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(modelCardPayment.getCardNumber().equals("")){
                    Toast.makeText(AddCardActivity.this, "CardNumber is empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(isValidDateFormat(modelCardPayment.getExpireDate())){

                    FirebaseDatabase.getInstance().getReference().child("CardForPayment")
                            .push().setValue(modelCardPayment.toMap()).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(AddCardActivity.this, "Failse to add", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(AddCardActivity.this, "Success to add", Toast.LENGTH_SHORT).show();
                                    finish();
                                    Intent intent = new Intent(AddCardActivity.this, PaymentActivity.class);
                                    startActivity(intent);
                                }
                            });

                }
                else{
                    Toast.makeText(AddCardActivity.this, "Invalid expiry date format. Use MM/YY ", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });

        // btnBack
        btnBack= (ImageView)findViewById(R.id.btn_back);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(AddCardActivity.this, PaymentActivity.class);
                startActivity(intent);

            }
        });
        show_more_btn = findViewById(R.id.show_more_btn);
        drawerLayout = findViewById(R.id.drawLayout);
        lnHome = findViewById(R.id.home);
        lnCart = findViewById(R.id.cart);
        lnProfile = findViewById(R.id.profile);
        lnRestaurant = findViewById(R.id.restaurant);
        show_more_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomePageActivity.openDrawer(drawerLayout);
            }
        });
        lnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomePageActivity.redirectActitvity(AddCardActivity.this, HomePageActivity.class);
            }
        });
        lnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomePageActivity.redirectActitvity(AddCardActivity.this, AddCartActivity.class);

            }
        });
        lnRestaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomePageActivity.redirectActitvity(AddCardActivity.this, RestaurantList.class);
            }
        });
        lnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomePageActivity.redirectActitvity(AddCardActivity.this, UserProflie.class);
            }
        });
        constraintLayout = findViewById(R.id.dontknow);
        constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("MyApp", "is oke");
            }
        });
    }
    @Override
    protected void onPause() {
        super.onPause();
        HomePageActivity.closeDrawer(drawerLayout);
    }
    private static boolean isValidDateFormat(String input) {
        String regex = "^(0[1-9]|1[0-2])/(\\d{2})$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        return matcher.matches();
    }
}