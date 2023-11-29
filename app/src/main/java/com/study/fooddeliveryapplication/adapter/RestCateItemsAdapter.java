package com.study.fooddeliveryapplication.adapter;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.study.fooddeliveryapplication.R;
import com.study.fooddeliveryapplication.model.Category;
import com.study.fooddeliveryapplication.model.Food;
import com.study.fooddeliveryapplication.ui.FoodDetailsActivity;
import com.study.fooddeliveryapplication.ui.RestaurantDetails;

import java.util.ArrayList;
import java.util.List;

public class RestCateItemsAdapter extends RecyclerView.Adapter<RestCateItemsAdapter.RestCateItemsViewHolder> {

    private UpdateRestFoodItems updateRestFoodItems;
    private List<Category> categories;
    private List<Food> foods;
    private int categoryIndex = 0;
    public RestCateItemsAdapter(List<Category> categories) {
        this.categories = categories;
    }


    public RestCateItemsAdapter(UpdateRestFoodItems updateRestFoodItems, List<Category> categories) {
        this.updateRestFoodItems = updateRestFoodItems;
        this.categories = categories;
        this.foods = new ArrayList<>();
    }

    @NonNull
    @Override
    public RestCateItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rest_cate_items, parent, false);
        return new RestCateItemsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RestCateItemsViewHolder holder, int position) {
        holder.button.setText(categories.get(position).getName());

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                categoryIndex = holder.getAdapterPosition();
                List<Food> foods = new ArrayList<>();
                notifyDataSetChanged();
                if (categoryIndex == 0) {
                    foods.add(new Food("Burger king", "Spicy", "40$", R.drawable.burgers));
                    foods.add(new Food("Burger king", "Spicy", "40$", R.drawable.burgers));
                    foods.add(new Food("Burger king", "Spicy", "40$", R.drawable.burgers));
                    foods.add(new Food("Burger king", "Spicy", "40$", R.drawable.burgers));
                    updateRestFoodItems.callBack(categoryIndex, foods);
                } else if (categoryIndex == 1) {
                    foods.add(new Food("Seafood Pizza", "Perfect", "99$", R.drawable.pizza));
                    foods.add(new Food("Seafood Pizza", "Perfect", "99$", R.drawable.pizza));
                    foods.add(new Food("Seafood Pizza", "Perfect", "99$", R.drawable.pizza));
                    foods.add(new Food("Seafood Pizza", "Perfect", "99$", R.drawable.pizza));
                    updateRestFoodItems.callBack(categoryIndex, foods);
                } else if (categoryIndex == 2) {
                    foods.add(new Food("Medium BBQ", "For family", "399$", R.drawable.bbq));
                    foods.add(new Food("Medium BBQ", "For family", "399$", R.drawable.bbq));
                    foods.add(new Food("Medium BBQ", "For family", "399$", R.drawable.bbq));
                    foods.add(new Food("Medium BBQ", "For family", "399$", R.drawable.bbq));
                    updateRestFoodItems.callBack(categoryIndex, foods);
                } else if (categoryIndex == 3) {
                    foods.add(new Food("Hot pot", "So hot!!", "199$", R.drawable.hotpot));
                    foods.add(new Food("Hot pot", "So hot!!", "199$", R.drawable.hotpot));
                    foods.add(new Food("Medium BBQ", "For family", "399$", R.drawable.bbq));
                    foods.add(new Food("Medium BBQ", "For family", "399$", R.drawable.bbq));
                    updateRestFoodItems.callBack(categoryIndex, foods);
                } else if (categoryIndex == 4) {
                    foods.add(new Food("Seafood Pizza", "Perfect", "99$", R.drawable.pizza));
                    foods.add(new Food("Seafood Pizza", "Perfect", "99$", R.drawable.pizza));
                    foods.add(new Food("Seafood Pizza", "Perfect", "99$", R.drawable.pizza));
                    foods.add(new Food("Seafood Pizza", "Perfect", "99$", R.drawable.pizza));
                    updateRestFoodItems.callBack(categoryIndex, foods);
                }
            }
        });


        if(categoryIndex == position){
            holder.button.setBackgroundResource(R.drawable.custom_btn_selected);
            holder.button.setTextColor(Color.parseColor("#FF000000"));
        }else{
            holder.button.setBackgroundResource(R.drawable.custom_btn_unselected);
            holder.button.setTextColor(Color.parseColor("#FFFFFFFF"));
        }

    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    class RestCateItemsViewHolder extends RecyclerView.ViewHolder {

        private Button button;
        private CardView cardView;

        public RestCateItemsViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.food_card_view);
            button = itemView.findViewById(R.id.category_items_btn);
        }
    }
}
