package com.study.fooddeliveryapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.study.fooddeliveryapplication.R;
import com.study.fooddeliveryapplication.model.RestauItem;

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

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int adapterPosition = holder.getAdapterPosition();
                RestauItem selectedItem = itemList.get(adapterPosition);
                String info = "You Picked: " + selectedItem.getText1();
                Toast.makeText(view.getContext(), info, Toast.LENGTH_SHORT).show();
            }
        });

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Xử lý sự kiện khi ImageView được click
                int adapterPosition = holder.getAdapterPosition();
                RestauItem selectedItem = itemList.get(adapterPosition);
                String info = "Bạn đã chọn: " + selectedItem.getText1();
                Toast.makeText(view.getContext(), info, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}


