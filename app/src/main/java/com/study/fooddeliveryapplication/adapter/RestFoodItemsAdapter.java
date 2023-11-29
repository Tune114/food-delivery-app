package com.study.fooddeliveryapplication.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.study.fooddeliveryapplication.R;
import com.study.fooddeliveryapplication.model.Food;
import com.study.fooddeliveryapplication.ui.FoodDetailsActivity;

import java.util.List;

public class RestFoodItemsAdapter extends RecyclerView.Adapter<RestFoodItemsAdapter.RestFoodItemsViewHolder> {

    private List<Food> foods;

    public RestFoodItemsAdapter(List<Food> foods) {
        this.foods = foods;
    }

    @NonNull
    @Override
    public RestFoodItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RestFoodItemsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.rest_food_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RestFoodItemsViewHolder holder, int position) {
        holder.name.setText(foods.get(position).getName());
        holder.description.setText(foods.get(position).getDescription());
        holder.price.setText(foods.get(position).getPrice());
        Glide.with(holder.image.getContext())
                .load(foods.get(position).getImage())
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

        public RestFoodItemsViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.food_name);
            description=itemView.findViewById(R.id.food_description);
            price=itemView.findViewById(R.id.food_price);
            image=itemView.findViewById(R.id.food_image);
        }
    }
}