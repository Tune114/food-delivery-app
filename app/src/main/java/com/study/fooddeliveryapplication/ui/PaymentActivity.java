package com.study.fooddeliveryapplication.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.study.fooddeliveryapplication.R;
import com.study.fooddeliveryapplication.adapter.ListCardAdapter;
import com.study.fooddeliveryapplication.adapter.ListCardAddAdapter;
import com.study.fooddeliveryapplication.model.ModelCard;
import com.study.fooddeliveryapplication.model.ModelCardPayment;
import com.study.fooddeliveryapplication.model.ModelOrder;
import com.study.fooddeliveryapplication.model.ModelOrderPaid;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;

public class PaymentActivity extends AppCompatActivity {
    private RecyclerView rvListCardItem, rvListCardAdd;
    private Button btnAddNewCard, btnPayAndConfirm;
    private ListCardAdapter listCardAdapter;
    private ListCardAddAdapter listCardAddAdapter;
    DrawerLayout drawerLayout;
    LinearLayout lnHome, lnCart, lnRestaurant, lnProfile;
    private TextView txPayable, txtCardUseForPay;
    private ModelOrderPaid modelOrderPaid;
    ConstraintLayout constraintLayout;
    private ImageView btnBack, show_more_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        //


        // listCardItem
        rvListCardItem = findViewById(R.id.listCardItem);
        rvListCardItem.setHasFixedSize(true);
        rvListCardItem.setLayoutManager(new LinearLayoutManager(this , LinearLayoutManager.HORIZONTAL , false));
        FirebaseRecyclerOptions<ModelCard> card =
                new FirebaseRecyclerOptions.Builder<ModelCard>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Card"), ModelCard.class)
                        .build();
        listCardAdapter = new ListCardAdapter(card);
        rvListCardItem.setAdapter(listCardAdapter);
        // listCardAdd
        rvListCardAdd = findViewById(R.id.listCardAdd);
        rvListCardAdd.setLayoutManager(new LinearLayoutManager(this ));
        FirebaseRecyclerOptions<ModelCardPayment> cardAdded =
                new FirebaseRecyclerOptions.Builder<ModelCardPayment>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("CardForPayment"),ModelCardPayment.class)
                        .build();
        listCardAddAdapter = new ListCardAddAdapter(cardAdded);
        rvListCardAdd.setAdapter(listCardAddAdapter);

        txtCardUseForPay = findViewById(R.id.txtCardNumberUsed);
        btnAddNewCard = findViewById(R.id.btnAddNewCard);
        btnAddNewCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), AddCardActivity.class);
                startActivity(intent);
            }
        });

        btnBack= (ImageView)findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(PaymentActivity.this, AddCartActivity.class);
                startActivity(intent);

            }
        });
        // Draw
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
                HomePageActivity.redirectActitvity(PaymentActivity.this, HomePageActivity.class);
            }
        });
        lnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomePageActivity.redirectActitvity(PaymentActivity.this, AddCartActivity.class);

            }
        });
        lnRestaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomePageActivity.redirectActitvity(PaymentActivity.this, RestaurantList.class);
            }
        });
        lnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomePageActivity.redirectActitvity(PaymentActivity.this, RestaurantList.class);
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
        listCardAdapter.startListening();
        listCardAddAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        listCardAdapter.stopListening();
        listCardAddAdapter.stopListening();
    }




}