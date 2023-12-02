package com.study.fooddeliveryapplication.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.study.fooddeliveryapplication.R;
import com.study.fooddeliveryapplication.adapter.ListCardAdapter;
import com.study.fooddeliveryapplication.adapter.ListCardAddAdapter;
import com.study.fooddeliveryapplication.model.ModelCard;
import com.study.fooddeliveryapplication.model.ModelCardPayment;

public class PaymentActivity extends AppCompatActivity {
    private RecyclerView rvListCardItem, rvListCardAdd;
    private Button btnAddNewCard;
    private ListCardAdapter listCardAdapter;
    private ListCardAddAdapter listCardAddAdapter;
    private TextView txPayable;
    private ImageView btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
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
        // Listener

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