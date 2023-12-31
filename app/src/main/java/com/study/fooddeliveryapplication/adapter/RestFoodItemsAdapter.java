package com.study.fooddeliveryapplication.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.study.fooddeliveryapplication.R;
import com.study.fooddeliveryapplication.model.Food;
import com.study.fooddeliveryapplication.ui.AddCartActivity;
import com.study.fooddeliveryapplication.ui.FoodDetailsActivity;

import java.util.List;

public class RestFoodItemsAdapter extends RecyclerView.Adapter<RestFoodItemsAdapter.RestFoodItemsViewHolder> {

    private List<Food> foods;

    public RestFoodItemsAdapter(List<Food> foods) {
        this.foods = foods;
    }
    private DatabaseReference databaseReference;

    @NonNull
    @Override
    public RestFoodItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RestFoodItemsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.rest_food_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RestFoodItemsViewHolder holder, int position) {

        String name = foods.get(position).getName();
        String description = foods.get(position).getDescription();
        String price=foods.get(position).getPrice();
        String image=foods.get(position).getImage();
        String restaurantName=foods.get(position).getRestaurantName();
        String categoryName=foods.get(position).getCategoryName();

        holder.name.setText(name);
        holder.description.setText(description);
        holder.price.setText(price+"$");
        Glide.with(holder.image.getContext())
                .load(image)
                .placeholder(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                .error(com.google.firebase.database.R.drawable.common_google_signin_btn_icon_dark)
                .into(holder.image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), FoodDetailsActivity.class);
                intent.putExtra("restaurantName",restaurantName);
                intent.putExtra("categoryName",categoryName);
                intent.putExtra("foodName",name);
                Context context = view.getContext();
                if (context instanceof Activity) {
                    ((Activity)context).startActivity(intent);
                }
            }
        });
        holder.addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = v.getContext().getSharedPreferences("my_app_preferences", Context.MODE_PRIVATE);
                String phoneNumbers = sharedPreferences.getString("phone", "");
                Food food=new Food(name,description,price,image,restaurantName,phoneNumbers);
                checkExistingOrder(name, restaurantName, phoneNumbers, new OrderCheckCallback() {
                    @Override
                    public void onResult(boolean result) {
                        if (result) {
                            Intent intent = new Intent(holder.name.getContext(), AddCartActivity.class);
                            holder.name.getContext().startActivity(intent);
                        } else {
                            FirebaseDatabase.getInstance().getReference().child("ItemCart")
                                    .push().setValue(food.toMap()).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(holder.name.getContext(), "Error", Toast.LENGTH_SHORT).show();
                                        }
                                    }).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Toast.makeText(holder.name.getContext(), "Order Success", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        }
                    }
                });
            }
        });

    }

    private interface OrderCheckCallback {
        void onResult(boolean result);
    }

    private void checkExistingOrder(String foodName, String restaurantName, String phoneNumbers, OrderCheckCallback callback) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("ItemCart");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                boolean result = false;
                for (DataSnapshot ds : snapshot.getChildren()) {
                    String fName = ds.child("foodName").getValue(String.class);
                    String rName = ds.child("restName").getValue(String.class);
                    String userPhone = ds.child("userPhone").getValue(String.class);
                    if (foodName.equals(fName) && restaurantName.equals(rName) && phoneNumbers.equals(userPhone)) {
                        result = true;
                        break;
                    }
                }
                callback.onResult(result);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    @Override
    public int getItemCount() {
        return foods.size();
    }

    public class RestFoodItemsViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView description;
        private TextView price;
        private ImageView image;
        private Button addToCartBtn;

        public RestFoodItemsViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.food_name);
            description=itemView.findViewById(R.id.food_description);
            price=itemView.findViewById(R.id.food_price);
            image=itemView.findViewById(R.id.food_image);
            addToCartBtn=itemView.findViewById(R.id.add_to_cart_btn);
        }
    }
}