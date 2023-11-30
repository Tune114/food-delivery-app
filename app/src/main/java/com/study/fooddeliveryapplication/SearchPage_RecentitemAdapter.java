package com.study.fooddeliveryapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SearchPage_RecentitemAdapter extends RecyclerView.Adapter<SearchPage_RecentitemAdapter.ViewHolder> {
    private final List<SearchPage_RecentItem> itemList;

    // Constructor
    public SearchPage_RecentitemAdapter(List<SearchPage_RecentItem> itemList) {
        this.itemList = itemList;
    }

    private int selectedPosition = RecyclerView.NO_POSITION;

    // ViewHolder class
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public ViewHolder(View view) {
            super(view);
            textView = view.findViewById(R.id.textView);


        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.search_page_recent_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SearchPage_RecentItem item = itemList.get(position);
        holder.textView.setText(item.getText());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int adapterPosition = holder.getAdapterPosition();
                SearchPage_RecentItem selectedItem = itemList.get(adapterPosition);
                String info = "You Picked: " + selectedItem.getText();
                Toast.makeText(view.getContext(), info, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}
