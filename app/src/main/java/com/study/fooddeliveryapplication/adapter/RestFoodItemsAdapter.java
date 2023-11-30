package com.study.fooddeliveryapplication.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import com.study.fooddeliveryapplication.ui.PaymentActivity;
import com.study.fooddeliveryapplication.ui.RestaurantDetails;

import java.util.List;

public class RestFoodItemsAdapter extends RecyclerView.Adapter<RestFoodItemsAdapter.RestFoodItemsViewHolder> {

    private List<Food> foods;

    public RestFoodItemsAdapter(List<Food> foods) {
        this.foods = foods;
    }
    private DatabaseReference databaseReference;
    private boolean result=false;

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
                Context context = view.getContext();
                if (context instanceof Activity) {
                    ((Activity)context).startActivity(intent);
                }
            }
        });
        holder.addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Food food=new Food(name,description,price,image,restaurantName);
                if(checkExistingOrder(name,restaurantName)){
                    Intent intent=new Intent(holder.name.getContext(), AddCartActivity.class);
                    holder.name.getContext().startActivity(intent);
                }else{
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

    public boolean checkExistingOrder(String foodName, String restaurantName){
        databaseReference=FirebaseDatabase.getInstance().getReference().child("ItemCart");
        databaseReference.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds:snapshot.getChildren()){
                    String fName=ds.child("foodName").getValue(String.class);
                    String rName=ds.child("restName").getValue(String.class);
                    if(foodName==fName && rName==restaurantName){
                        result=true;
                        break;
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return result;
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