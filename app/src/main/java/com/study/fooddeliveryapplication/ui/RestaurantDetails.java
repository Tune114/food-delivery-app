package com.study.fooddeliveryapplication.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.study.fooddeliveryapplication.R;
import com.study.fooddeliveryapplication.adapter.RestCateItemsAdapter;
import com.study.fooddeliveryapplication.adapter.RestFoodItemsAdapter;
import com.study.fooddeliveryapplication.adapter.UpdateRestFoodItems;
import com.study.fooddeliveryapplication.model.Category;
import com.study.fooddeliveryapplication.model.Food;
import java.util.ArrayList;
import java.util.List;

public class RestaurantDetails extends AppCompatActivity implements UpdateRestFoodItems{

    private RecyclerView categoriesRecyclerView;
    private RecyclerView foodRecyclerView;
    ImageButton back_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_details);

       back_btn = (ImageButton) findViewById(R.id.back_button);
       back_btn.setOnClickListener(view -> {
           finish();
           Intent intent = new Intent(RestaurantDetails.this, HomePageActivity.class);
           startActivity(intent);
       });


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
        List<Food> foods = new ArrayList<>();
        foods.add(new Food("Burger king", "Spicy", "40$", R.drawable.burgers));
        foods.add(new Food("Burger king", "Spicy", "40$", R.drawable.burgers));
        foods.add(new Food("Burger king", "Spicy", "40$", R.drawable.burgers));
        foods.add(new Food("Burger king", "Spicy", "40$", R.drawable.burgers));
        callBack(0, foods);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void callBack(int position, List<Food> foods) {
        RestFoodItemsAdapter foodAdapter = new RestFoodItemsAdapter(foods);
        foodAdapter.notifyDataSetChanged();
        foodRecyclerView.setAdapter(foodAdapter);
        foodRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
    }



}