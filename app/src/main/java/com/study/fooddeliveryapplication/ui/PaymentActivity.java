package com.study.fooddeliveryapplication.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.study.fooddeliveryapplication.R;
import com.study.fooddeliveryapplication.adapter.ListCardAdapter;
import com.study.fooddeliveryapplication.adapter.ListCardAddAdapter;
import com.study.fooddeliveryapplication.model.CardForPayment;

import java.util.ArrayList;
import java.util.List;

public class PaymentActivity extends AppCompatActivity {
    private RecyclerView rvListCardItem, rvListCardAdd;
    private Button btnAddNewCard;

    private ImageView btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        SeedData();

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
    public void SeedData(){
        // ListCardAdapter
        List<CardForPayment> listCard = new ArrayList<>();
        listCard.add(new CardForPayment("card", R.drawable.img_payment));
        listCard.add(new CardForPayment("mastercard", R.drawable.img_mastercard));
        listCard.add(new CardForPayment("visa", R.drawable.img_visa));
        listCard.add(new CardForPayment("paypal", R.drawable.img_paypal));
        listCard.add(new CardForPayment("bidv", R.drawable.img_bidv));
        listCard.add(new CardForPayment("viettinbank", R.drawable.img_viettinbank));

        rvListCardItem = findViewById(R.id.listCardItem);
        rvListCardItem.setHasFixedSize(true);
        rvListCardItem.setLayoutManager(new LinearLayoutManager(this , LinearLayoutManager.HORIZONTAL , false));
        ListCardAdapter listCardAdapter = new ListCardAdapter(listCard);
        rvListCardItem.setAdapter(listCardAdapter);

        //
        List<CardForPayment> listCardAdd = new ArrayList<>();
        for(int i = 1; i<4; i++){
            listCard.get(i).setCardNumber("***************123");
        }
        listCard.get(1).setIcon(R.drawable.icon_mastercard);
        listCard.get(2).setIcon(R.drawable.icon_visa);
        listCard.get(3).setIcon(R.drawable.icon_paypal);

        listCardAdd.add(listCard.get(1));
        listCardAdd.add(listCard.get(2));
        listCardAdd.add(listCard.get(3));

        rvListCardAdd = findViewById(R.id.listCardAdd);
        rvListCardAdd.setLayoutManager(new LinearLayoutManager(this ));
        ListCardAddAdapter listCardAddAdapter = new ListCardAddAdapter(listCardAdd);
        rvListCardAdd.setAdapter(listCardAddAdapter);
    }
}