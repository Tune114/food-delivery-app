package com.study.fooddeliveryapplication.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.study.fooddeliveryapplication.R;
import com.study.fooddeliveryapplication.adapter.RestCateItemsAdapter;
import com.study.fooddeliveryapplication.adapter.RestFoodItemsAdapter;
import com.study.fooddeliveryapplication.adapter.UpdateRestInfor;
import com.study.fooddeliveryapplication.model.Category;
import com.study.fooddeliveryapplication.model.Food;

import java.util.ArrayList;
import java.util.List;

public class RestaurantDetails extends AppCompatActivity implements UpdateRestInfor {

    private RecyclerView categoriesRecyclerView;
    private RecyclerView foodRecyclerView;
    private ImageButton back_btn;
    private DatabaseReference databaseReference;
    private List<Category> categories;
    private List<Food> foods;
    private RestCateItemsAdapter categoryAdapter;
    private TextView categoryLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_details);


        back_btn = (ImageButton) findViewById(R.id.back_button);
        back_btn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               finish();
               Intent intent = new Intent(RestaurantDetails.this, HomePageActivity.class);
               startActivity(intent);
           }
        });

        categoryLabel = findViewById(R.id.category_label);

        categories=new ArrayList<>();

        categoriesRecyclerView=findViewById(R.id.rest_cate_items);
        foodRecyclerView=findViewById(R.id.rest_cate_foods);
        categoryAdapter=new RestCateItemsAdapter(this,categories);
        categoriesRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        categoriesRecyclerView.setAdapter(categoryAdapter);

        databaseReference=FirebaseDatabase.getInstance().getReference().child("restaurants");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                categories.clear();
                for(DataSnapshot ds:snapshot.getChildren()){
                    String restaurantName=ds.child("name").getValue(String.class);

                    DataSnapshot categoriesSnapshot = ds.child("categories");
                    for(DataSnapshot cateDs:categoriesSnapshot.getChildren()){
                        foods=new ArrayList<>();
                        String categoryName=cateDs.child("category_name").getValue(String.class);
                        Category category=new Category(categoryName);

                        DataSnapshot foodsSnapshot = cateDs.child("foods");
                        for(DataSnapshot foodDs:foodsSnapshot.getChildren()){
                            Food food=foodDs.getValue(Food.class);
                            foods.add(food);
                        }
                        category.setFoods(foods);
                        categories.add(category);
                    }
                }
                categories.get(0).setSelected(true);
                categoryAdapter.notifyDataSetChanged();
                callBack(categories.get(0).getFoods());
                updateCategoryLabel(categories.get(0).getFoods().size(),categories.get(0).getName());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void callBack(List<Food> foods) {
        RestFoodItemsAdapter foodAdapter = new RestFoodItemsAdapter(foods);
        foodAdapter.notifyDataSetChanged();
        foodRecyclerView.setAdapter(foodAdapter);
        foodRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
    }

    @Override
    public void updateCategoryLabel(int size,String categoryName) {
        categoryLabel.setText(categoryName+" ("+size+")");
    }

}