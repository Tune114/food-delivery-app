package com.study.fooddeliveryapplication.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.study.fooddeliveryapplication.R;
import com.study.fooddeliveryapplication.adapter.SearchPageData_ItemsApdapter;
import com.study.fooddeliveryapplication.model.SearchPage_PopularFoodItem;

public class SearchPageActivity_Data extends AppCompatActivity {
    SearchPageData_ItemsApdapter adapter;
    ImageView backbtn;
    RecyclerView recyclerView1;
    SearchView searchView;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_page_data);

        backbtn = (ImageView) findViewById(R.id.iv_spd_left_arrow);
        backbtn.setOnClickListener(view -> {
            finish();
            Intent intent = new Intent(SearchPageActivity_Data.this, SearchPageActivity.class);
            startActivity(intent);
        });

        searchView = findViewById(R.id.sv_search);

        searchView.setOnClickListener(view -> {
            if (searchView.isIconified()) {
                searchView.setIconified(false);
                Log.d("SearchView", "dùng được");
            } else {
                Log.d("SearchView", "đang dùng");
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                txtSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                txtSearch(query);
                return false;
            }
        });

        recyclerView1 = findViewById(R.id.rv_spd);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView1.setLayoutManager(layoutManager1);
        FirebaseRecyclerOptions<SearchPage_PopularFoodItem> options1 =
                new FirebaseRecyclerOptions.Builder<SearchPage_PopularFoodItem>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("/SearchPage/SearchPage_PopularFoodItems"),SearchPage_PopularFoodItem.class)
                        .build();
        adapter = new SearchPageData_ItemsApdapter(options1);
        recyclerView1.setAdapter(adapter);
    }

    private void txtSearch(String str){
        FirebaseRecyclerOptions<SearchPage_PopularFoodItem> options1 =
                new FirebaseRecyclerOptions.Builder<SearchPage_PopularFoodItem>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("/SearchPage/SearchPage_PopularFoodItems").orderByChild("PopularFoodName").startAt(str).endAt(str+"~"),SearchPage_PopularFoodItem.class)
                        .build();
        adapter = new SearchPageData_ItemsApdapter(options1);
        adapter.startListening();
        recyclerView1.setAdapter(adapter);
    }


    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}