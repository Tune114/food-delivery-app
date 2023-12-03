package com.study.fooddeliveryapplication.ui;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.study.fooddeliveryapplication.R;
import com.study.fooddeliveryapplication.adapter.ListCartItemAdapter;
import com.study.fooddeliveryapplication.model.ModelCart;
import com.study.fooddeliveryapplication.model.ModelOrder;
import java.util.ArrayList;
import java.util.List;

public class AddCartActivity extends AppCompatActivity {

    private RecyclerView rvListItem;
    private Button btnPlaceOrder;
    ConstraintLayout constraintLayout;
    private EditText edAddress;
    private TextView txtAddPayment, txPayable, txtCardNumberUsed;
    private ListCartItemAdapter listCartItemAdapter;
    DrawerLayout drawerLayout;
    LinearLayout lnHome, lnCart, lnRestaurant, lnProfile;
    private ImageView btnBack, show_more_btn;
    private  List<ModelCart> listOrder;
    String phone;
    private ModelOrder modelOrder;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycart);
        //
        SharedPreferences sharedPreferences = getSharedPreferences("my_app_preferences", Context.MODE_PRIVATE);
        phone = sharedPreferences.getString("phone", "");
        if(phone.isEmpty()){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Error");
            builder.setMessage("Please login before using the card");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(AddCartActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            });
            builder.show();
        }
        // listCartItemAdapter
        rvListItem = findViewById(R.id.listCartItem);
        rvListItem.setLayoutManager(new LinearLayoutManager(this ));
        FirebaseRecyclerOptions<ModelCart> order =
                new FirebaseRecyclerOptions.Builder<ModelCart>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("ItemCart").orderByChild("userPhone").equalTo(phone), ModelCart.class)
                        .build();

        listCartItemAdapter = new ListCartItemAdapter(order);
        rvListItem.setAdapter(listCartItemAdapter);

        // txPayable and ModelOrder modelOrder
        listOrder = new ArrayList<>();
        modelOrder = new ModelOrder();
        txPayable = findViewById(R.id.txtPayable);
        DatabaseReference data = FirebaseDatabase.getInstance().getReference("ItemCart");
        data.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int payable =0;
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    ModelCart modelCart  = dataSnapshot.getValue(ModelCart.class);
                    if(!modelCart.getUserPhone().isEmpty() && modelCart.getUserPhone().equals(phone)){
                        payable = payable + Integer.parseInt(modelCart .getPrice())*Integer.parseInt(modelCart .getQuantity());
                        listOrder.add(modelCart );
                    }
                }
                txPayable.setText(String.valueOf(payable));
                modelOrder.setListFood(listOrder);
                modelOrder.setPayable(String.valueOf(payable));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Intent intent = getIntent();
        String cardNumber = intent.getStringExtra("cardNumber");
        txtCardNumberUsed = findViewById(R.id.txtCardNumberUsed);
        if(cardNumber ==null ){
            txtCardNumberUsed.setText("card number is empty");
        } else{
            modelOrder.setCardNumber(cardNumber);
            txtCardNumberUsed.setText(modelOrder.getCardNumber());
        }
        // btnPlaceOrder
        btnPlaceOrder = (Button)findViewById(R.id.btnPlaceOrder);
        btnPlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(modelOrder.getCardNumber()==null){
                    Toast.makeText(AddCartActivity.this, "Please choose card before order", Toast.LENGTH_SHORT).show();
                    return;
                }
                edAddress = findViewById(R.id.edAddress);
                modelOrder.setAddress(edAddress.getText().toString());
                if(modelOrder.getAddress().isEmpty() ){
                    Toast.makeText(AddCartActivity.this, "Address is empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                FirebaseDatabase.getInstance().getReference().child("Order")
                        .push().setValue(modelOrder.toMap()).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(AddCartActivity.this, "Failse to order", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                // FirebaseDatabase.getInstance().getReference().child("ItemCart").removeValue();
                                Toast.makeText(AddCartActivity.this, "Success to add", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        // txtAddPayment
        txtAddPayment = findViewById(R.id.txtEditPayment);
        txtAddPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), PaymentActivity.class);
                startActivity(intent);
            }
        });

        // btnBack
        btnBack= (ImageView)findViewById(R.id.btn_back);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(AddCartActivity.this, HomePageActivity.class);
                startActivity(intent);

            }
        });
        // Drawer Layout
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
                HomePageActivity.redirectActitvity(AddCartActivity.this, HomePageActivity.class);
            }
        });
        lnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recreate();

            }
        });
        lnRestaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomePageActivity.redirectActitvity(AddCartActivity.this, RestaurantList.class);
            }
        });
        lnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomePageActivity.redirectActitvity(AddCartActivity.this, UserProflie.class);
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


    @Override
    protected void onStart() {
        super.onStart();
        listCartItemAdapter.startListening();

    }

    @Override
    protected void onStop() {
        super.onStop();
        listCartItemAdapter.stopListening();
    }

}