package com.study.fooddeliveryapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

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


    }
}