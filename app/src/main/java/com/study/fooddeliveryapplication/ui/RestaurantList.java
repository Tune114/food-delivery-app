package com.study.fooddeliveryapplication.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.study.fooddeliveryapplication.R;
import com.study.fooddeliveryapplication.adapter.RestaurantListAdapter;
import com.study.fooddeliveryapplication.model.Category;
import com.study.fooddeliveryapplication.model.Food;
import com.study.fooddeliveryapplication.model.RestauItem;

import java.util.ArrayList;
import java.util.List;

public class RestaurantList extends AppCompatActivity {
    private List<RestauItem> restauItemList;
    private RestaurantListAdapter restaurantListAdapter;
    private DatabaseReference databaseReference;
    private RecyclerView restaurantRecyclerView;
    ImageButton backbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_list);

        restauItemList=new ArrayList<>();
        restaurantListAdapter=new RestaurantListAdapter(restauItemList);
        restaurantRecyclerView=findViewById(R.id.restaurant_list);
        restaurantRecyclerView.setAdapter(restaurantListAdapter);
        restaurantRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        databaseReference=FirebaseDatabase.getInstance().getReference().child("restaurants");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds:snapshot.getChildren()){
                    String restName=ds.child("RestName").getValue(String.class);
                    String restDescription=ds.child("RestDescrip").getValue(String.class);
                    String restImage=ds.child("RestImage").getValue(String.class);
                    restauItemList.add(new RestauItem(restImage,restName,restDescription));
                }
                restaurantListAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        backbtn = (ImageButton) findViewById(R.id.btnback_reslist);
        backbtn.setOnClickListener(view -> {
            Intent intent = new Intent(RestaurantList.this,HomePageActivity.class);
            startActivity(intent);
            finish();
        });

    }
}