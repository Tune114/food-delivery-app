package com.study.fooddeliveryapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.study.fooddeliveryapplication.R;
import com.study.fooddeliveryapplication.model.IngredientItem;

import java.util.List;

// IngredientListAdapter.java
public class IngredientListAdapter extends RecyclerView.Adapter<IngredientListAdapter.IngredientViewHolder> {
    private List<IngredientItem> ingredientList;
    private Context context;

    public IngredientListAdapter(Context context, List<IngredientItem> ingredientList) {
        this.context = context;
        this.ingredientList = ingredientList;
    }

    @NonNull
    @Override
    public IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ingredient, parent, false);
        return new IngredientViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull IngredientViewHolder holder, int position) {
        IngredientItem item = ingredientList.get(position);
        holder.ingredientName.setText(item.getName());

        // Load image using Glide library
        Glide.with(context)
                .load(item.getImage())
                .into(holder.ingredientImage);
    }

    @Override
    public int getItemCount() {
        return ingredientList.size();
    }

    public static class IngredientViewHolder extends RecyclerView.ViewHolder {
        public ImageView ingredientImage;
        public TextView ingredientName;

        public IngredientViewHolder(@NonNull View itemView) {
            super(itemView);
            ingredientImage = itemView.findViewById(R.id.ingre_image);
            ingredientName = itemView.findViewById(R.id.ingre_text);
        }
    }
}