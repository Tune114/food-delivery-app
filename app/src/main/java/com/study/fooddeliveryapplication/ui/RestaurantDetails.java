package com.study.fooddeliveryapplication.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.study.fooddeliveryapplication.R;
import com.study.fooddeliveryapplication.adapters.RestCateItemsAdapter;
import com.study.fooddeliveryapplication.model.Category;

import java.util.ArrayList;
import java.util.List;

public class RestaurantDetails extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_details);


        List<Category> categories=new ArrayList<>();
        categories.add(new Category("Burger"));
        categories.add(new Category("Pizza"));
        categories.add(new Category("BBQ"));
        categories.add(new Category("Pasta"));
        categories.add(new Category("Noodle"));

        recyclerView=findViewById(R.id.rest_cate_items);

        RestCateItemsAdapter adapter=new RestCateItemsAdapter(categories);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        

    }
}