package com.study.fooddeliveryapplication;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class itemAdapter extends RecyclerView.Adapter<itemAdapter.ViewHolder> {
    private final List<Item> itemList;

    // Constructor
    public itemAdapter(List<Item> itemList) {
        this.itemList = itemList;
    }

    private int selectedPosition = RecyclerView.NO_POSITION;

    // ViewHolder class
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView;

        public ViewHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.imageView);
            textView = view.findViewById(R.id.textView);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item item = itemList.get(position);
        holder.imageView.setImageResource(item.getImageResource());
        holder.textView.setText(item.getText());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item.setSelected(!item.isSelected());

                if (item.isSelected()) {
                    holder.itemView.setBackgroundResource(R.drawable.item_rounded_background); // Màu nền khi được chọn
                } else {
                    holder.itemView.setBackgroundResource(R.drawable.item_rounded_background_normal); // Màu nền mặc định
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}
