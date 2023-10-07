package com.study.fooddeliveryapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RestauItemAdapter extends RecyclerView.Adapter<RestauItemAdapter.ViewHolder>  {
    private final List<RestauItem> itemList;

    public RestauItemAdapter(List<RestauItem> itemList) {
        this.itemList = itemList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView1;
        public TextView textView2;

        public ViewHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.imageViewRes);
            textView1 = view.findViewById(R.id.textView1Res);
            textView2 = view.findViewById(R.id.textView2Res);
        }
    }

    @NonNull
    @Override
    public RestauItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.restau_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RestauItem restauItem = itemList.get(position);
        holder.imageView.setImageResource(restauItem.getImageResource());
        holder.textView1.setText(restauItem.getText1());
        holder.textView2.setText(restauItem.getText2());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}


