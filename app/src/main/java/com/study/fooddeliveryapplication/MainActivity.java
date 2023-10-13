package com.study.fooddeliveryapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.study.fooddeliveryapplication.adapter.ListCardAdapter;

import com.study.fooddeliveryapplication.adapter.ListCardAddAdapter;
import com.study.fooddeliveryapplication.adapter.ListCartItemAdapter;
import com.study.fooddeliveryapplication.model.CardForPayment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvListItem;
    private TextView txtAddPayment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycart);

        SeedData();

        txtAddPayment = findViewById(R.id.txtEditPayment);
        txtAddPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),PaymentActivity.class);
                startActivity(intent);
            }
        });


    }
    public void SeedData(){
        List<String> list = new ArrayList<>();

        list.add("Margherita");
        list.add("Pepperoni");
        list.add("Mushroom");
        rvListItem = findViewById(R.id.listCartItem);
        rvListItem.setLayoutManager(new LinearLayoutManager(this ));
        ListCartItemAdapter listCartItemAdapter = new ListCartItemAdapter(list);
        rvListItem.setAdapter(listCartItemAdapter);
    }
}