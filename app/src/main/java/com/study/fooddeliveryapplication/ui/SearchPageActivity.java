package com.study.fooddeliveryapplication.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.study.fooddeliveryapplication.R;
import com.study.fooddeliveryapplication.adapter.SearchPage_RecentitemAdapter;
import com.study.fooddeliveryapplication.adapter.SearchPage_SuggestRestauItemAdapter;
import com.study.fooddeliveryapplication.adapter.SearchPage_PopularFoodItemAdapter;
import com.study.fooddeliveryapplication.model.SearchPage_PopularFoodItem;
import com.study.fooddeliveryapplication.model.SearchPage_RecentItem;
import com.study.fooddeliveryapplication.model.SearchPage_SuggestRestauItem;

public class SearchPageActivity extends AppCompatActivity {

    ImageView backbtn;
    ImageView cart;
    TextView tv_search;

    SearchPage_RecentitemAdapter adapter1;
    SearchPage_SuggestRestauItemAdapter adapter2;
    SearchPage_PopularFoodItemAdapter adapter3;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_page);

        backbtn = (ImageView) findViewById(R.id.iv_left_arrow);
        backbtn.setOnClickListener(view -> {
            finish();
            Intent intent = new Intent(SearchPageActivity.this, HomePageActivity.class);
            startActivity(intent);
        });

        tv_search = (TextView) findViewById(R.id.tv_seachBar);
        tv_search.setOnClickListener(view -> {
            Intent intent = new Intent(SearchPageActivity.this, SearchPageActivity_Data.class);
            startActivity(intent);
        });

        cart = (ImageView)findViewById(R.id.iv_bag);
        cart.setOnClickListener(view -> {
            Intent intent = new Intent(SearchPageActivity.this, AddCartActivity.class);
            startActivity(intent);
        });

        //Custom Recent Items
        RecyclerView recyclerView1 = findViewById(R.id.recyclerView1);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView1.setLayoutManager(layoutManager1);
        FirebaseRecyclerOptions<SearchPage_RecentItem> options1 =
                new FirebaseRecyclerOptions.Builder<SearchPage_RecentItem>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("/SearchPage/SearchPage_RecentItems"),SearchPage_RecentItem.class)
                        .build();
        adapter1 = new SearchPage_RecentitemAdapter(options1);
        recyclerView1.setAdapter(adapter1);

        //Custom Suggest Res Items
        RecyclerView recyclerView2 = findViewById(R.id.recyclerView2);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView2.setLayoutManager(layoutManager2);
        FirebaseRecyclerOptions<SearchPage_SuggestRestauItem> options2 =
                new FirebaseRecyclerOptions.Builder<SearchPage_SuggestRestauItem>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("/SearchPage/SearchPage_SuggestRestItems"),SearchPage_SuggestRestauItem.class)
                        .build();
        adapter2 = new SearchPage_SuggestRestauItemAdapter(options2);
        recyclerView2.setAdapter(adapter2);

        //Custom Popular Food Items
        RecyclerView recyclerView3 = findViewById(R.id.recyclerView3);
        LinearLayoutManager layoutManager3 = new GridLayoutManager(this, 2);
        recyclerView3.setLayoutManager(layoutManager3);
        FirebaseRecyclerOptions<SearchPage_PopularFoodItem> options3 =
                new FirebaseRecyclerOptions.Builder<SearchPage_PopularFoodItem>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("/SearchPage/SearchPage_PopularFoodItems"),SearchPage_PopularFoodItem.class)
                        .build();
        adapter3 = new SearchPage_PopularFoodItemAdapter(options3);
        recyclerView3.setAdapter(adapter3);
    }
    @Override
    protected void onStart() {
        super.onStart();
        adapter1.startListening();
        adapter2.startListening();
        adapter3.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter1.stopListening();
        adapter2.stopListening();
        adapter3.stopListening();
    }
}