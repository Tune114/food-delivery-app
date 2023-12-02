package com.study.fooddeliveryapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.study.fooddeliveryapplication.R;
import com.study.fooddeliveryapplication.model.FoodItem;

import java.util.List;

public class FoodListAdapter extends RecyclerView.Adapter<FoodListAdapter.FoodViewHolder> {
    private Context mContext;
    private List<FoodItem> mFoodList;

    public FoodListAdapter(Context context, List<FoodItem> foodList) {
        mContext = context;
        mFoodList = foodList;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_food, parent, false);
        return new FoodViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        FoodItem item = mFoodList.get(position);
        holder.imageView.setImageDrawable(item.getImageDrawable());
        holder.nameTextView.setText(item.getName());
    }

    @Override
    public int getItemCount() {
        return mFoodList.size();
    }

    static class FoodViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView nameTextView;

        FoodViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.food_image_item);
            nameTextView = itemView.findViewById(R.id.food_text_item);
        }
    }
}