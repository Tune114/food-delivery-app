package com.study.fooddeliveryapplication.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.study.fooddeliveryapplication.R;
import com.study.fooddeliveryapplication.model.Food;
import com.study.fooddeliveryapplication.ui.FoodDetailsActivity;
import com.study.fooddeliveryapplication.ui.RestaurantDetails;

import java.util.List;

public class RestFoodItemsAdapter extends RecyclerView.Adapter<RestFoodItemsAdapter.RestFoodItemsViewHolder> {

    private List<Food> foods;

    public RestFoodItemsAdapter(List<Food> foods) {
        this.foods = foods;
    }

    @NonNull
    @Override
    public RestFoodItemsAdapter.RestFoodItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RestFoodItemsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.rest_food_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RestFoodItemsAdapter.RestFoodItemsViewHolder holder, int position) {
        holder.name.setText(foods.get(position).getName());
        holder.description.setText(foods.get(position).getDescription());
        holder.price.setText(foods.get(position).getPrice());
        holder.image.setImageResource(foods.get(position).getImage());

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
