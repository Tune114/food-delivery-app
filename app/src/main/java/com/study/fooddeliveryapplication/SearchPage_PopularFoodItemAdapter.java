package com.study.fooddeliveryapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SearchPage_PopularFoodItemAdapter extends RecyclerView.Adapter<SearchPage_PopularFoodItemAdapter.ViewHolder> {
    private final List<SearchPage_PopularFoodItem> itemList;

    // Constructor
    public SearchPage_PopularFoodItemAdapter(List<SearchPage_PopularFoodItem> itemList) {
        this.itemList = itemList;
    }

    private int selectedPosition = RecyclerView.NO_POSITION;

    // ViewHolder class
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView;

        public ViewHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.imageViewRes);
            textView = view.findViewById(R.id.textView1);
            textView = view.findViewById(R.id.textView2);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.search_page_popular_food_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SearchPage_PopularFoodItem item = itemList.get(position);
        holder.imageView.setImageResource(item.getImageResource());
        holder.textView.setText(item.getText1());
        holder.textView.setText(item.getText2());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}
