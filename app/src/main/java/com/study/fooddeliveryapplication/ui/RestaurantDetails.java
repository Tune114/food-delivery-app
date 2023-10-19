package com.study.fooddeliveryapplication.ui;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.study.fooddeliveryapplication.R;
import com.study.fooddeliveryapplication.adapters.RestCateItemsAdapter;
import com.study.fooddeliveryapplication.adapters.RestFoodItemsAdapter;
import com.study.fooddeliveryapplication.adapters.UpdateRestFoodItems;
import com.study.fooddeliveryapplication.model.Category;
import com.study.fooddeliveryapplication.model.Food;

import java.util.ArrayList;
import java.util.List;

public class RestaurantDetails extends AppCompatActivity implements UpdateRestFoodItems {

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

        categoriesRecyclerView=findViewById(R.id.rest_cate_items);

        RestCateItemsAdapter categoryAdapter=new RestCateItemsAdapter(this,categories);
        categoriesRecyclerView.setAdapter(categoryAdapter);
        categoriesRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        // Restaurant Foods
        foodRecyclerView=findViewById(R.id.rest_cate_foods);


    }

    @Override
    public void callBack(int position, List<Food> foods) {
        RestFoodItemsAdapter foodAdapter = new RestFoodItemsAdapter(foods);
        foodAdapter.notifyDataSetChanged();
        foodRecyclerView.setAdapter(foodAdapter);
        foodRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
    }
}