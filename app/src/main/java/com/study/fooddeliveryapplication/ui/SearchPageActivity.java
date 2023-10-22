package com.study.fooddeliveryapplication.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.study.fooddeliveryapplication.R;
import com.study.fooddeliveryapplication.adapter.SearchPage_RecentitemAdapter;
import com.study.fooddeliveryapplication.adapter.SearchPage_SuggestRestauItemAdapter;
import com.study.fooddeliveryapplication.adapter.SearchPage_PopularFoodItemAdapter;
import com.study.fooddeliveryapplication.model.SearchPage_PopularFoodItem;
import com.study.fooddeliveryapplication.model.SearchPage_RecentItem;
import com.study.fooddeliveryapplication.model.SearchPage_SuggestRestauItem;

import java.util.ArrayList;
import java.util.List;

public class SearchPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_page);

        //Custom Recent Items
        RecyclerView recyclerView1 = findViewById(R.id.recyclerView1);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView1.setLayoutManager(layoutManager1);
        List<SearchPage_RecentItem> itemList1 = new ArrayList<>();
        itemList1.add(new SearchPage_RecentItem("Text 1"));
        itemList1.add(new SearchPage_RecentItem("Text 2"));
        itemList1.add(new SearchPage_RecentItem("Text 3"));
        itemList1.add(new SearchPage_RecentItem("Text 4"));
        itemList1.add(new SearchPage_RecentItem("Text 5"));
        itemList1.add(new SearchPage_RecentItem("Text 6"));
        SearchPage_RecentitemAdapter adapter1 = new SearchPage_RecentitemAdapter(itemList1);
        recyclerView1.setAdapter(adapter1);

        //Custom Suggest Res Items
        RecyclerView recyclerView2 = findViewById(R.id.recyclerView2);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView2.setLayoutManager(layoutManager2);
        List<SearchPage_SuggestRestauItem> itemList2 = new ArrayList<>();
        itemList2.add(new SearchPage_SuggestRestauItem(R.drawable.pho,"Text 1","4.5"));
        itemList2.add(new SearchPage_SuggestRestauItem(R.drawable.buncha,"Text 2","5"));
        itemList2.add(new SearchPage_SuggestRestauItem(R.drawable.banhmi,"Text 3","4"));
        itemList2.add(new SearchPage_SuggestRestauItem(R.drawable.banhmi,"Text 3","3.9"));
        itemList2.add(new SearchPage_SuggestRestauItem(R.drawable.banhmi,"Text 3","3.5"));
        SearchPage_SuggestRestauItemAdapter adapter2 = new SearchPage_SuggestRestauItemAdapter(itemList2);
        recyclerView2.setAdapter(adapter2);

        //Custom Popular Food Items
        RecyclerView recyclerView3 = findViewById(R.id.recyclerView3);
        LinearLayoutManager layoutManager3 = new GridLayoutManager(this, 2);
        recyclerView3.setLayoutManager(layoutManager3);
        List<SearchPage_PopularFoodItem> itemList3 = new ArrayList<>();
        itemList3.add(new SearchPage_PopularFoodItem(R.drawable.pho,"Text 1","Text 1"));
        itemList3.add(new SearchPage_PopularFoodItem(R.drawable.banhmi,"Text 2","Text 2"));
        itemList3.add(new SearchPage_PopularFoodItem(R.drawable.buncha,"Text 3","Text 3"));
        itemList3.add(new SearchPage_PopularFoodItem(R.drawable.pho,"Text 4","Text 4"));
        SearchPage_PopularFoodItemAdapter adapter3 = new SearchPage_PopularFoodItemAdapter(itemList3);
        recyclerView3.setAdapter(adapter3);

    }
}