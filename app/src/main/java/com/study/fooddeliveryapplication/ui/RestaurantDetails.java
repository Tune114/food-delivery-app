package com.study.fooddeliveryapplication.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.study.fooddeliveryapplication.R;
import com.study.fooddeliveryapplication.adapters.RestCateItemsAdapter;
import com.study.fooddeliveryapplication.adapters.RestFoodItemsAdapter;
import com.study.fooddeliveryapplication.model.Category;
import com.study.fooddeliveryapplication.model.Food;

import java.util.ArrayList;
import java.util.List;

public class RestaurantDetails extends AppCompatActivity {

    private RecyclerView categoriesRecyclerView;

    private RecyclerView foodRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_details);

        // Restaurant categories
        List<Category> categories=new ArrayList<>();
        categories.add(new Category("Burger"));
        categories.add(new Category("Pizza"));
        categories.add(new Category("BBQ"));
        categories.add(new Category("Pasta"));
        categories.add(new Category("Noodle"));
        categories.add(new Category("Beef steak"));
        categories.add(new Category("Chicken soup"));

        categoriesRecyclerView=findViewById(R.id.rest_cate_items);

        RestCateItemsAdapter categoryAdapter=new RestCateItemsAdapter(categories);
        categoriesRecyclerView.setAdapter(categoryAdapter);
        categoriesRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        // Restaurant Foods

        List<Food> foods=new ArrayList<>();
        foods.add(new Food("Burger king","Spicy","40$",R.drawable.burgers));
        foods.add(new Food("Seafood Pizza","Perfect","99$",R.drawable.pizza));
        foods.add(new Food("Medium BBQ","For family","399$",R.drawable.bbq));
        foods.add(new Food("Hot pot","So hot!!","199$",R.drawable.hotpot));

        foodRecyclerView=findViewById(R.id.rest_cate_foods);

        RestFoodItemsAdapter foodAdapter=new RestFoodItemsAdapter(foods);
        foodRecyclerView.setAdapter(foodAdapter);
        foodRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
    }
}