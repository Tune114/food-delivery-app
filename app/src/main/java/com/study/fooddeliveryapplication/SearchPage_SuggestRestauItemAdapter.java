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

public class SearchPage_SuggestRestauItemAdapter extends RecyclerView.Adapter<SearchPage_SuggestRestauItemAdapter.ViewHolder> {
    private final List<SearchPage_SuggestRestauItem> itemList;

    // Constructor
    public SearchPage_SuggestRestauItemAdapter(List<SearchPage_SuggestRestauItem> itemList) {
        this.itemList = itemList;
    }

    private int selectedPosition = RecyclerView.NO_POSITION;

    // ViewHolder class
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textViewSuggestRes;
        public TextView textViewStar;

        public ViewHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.imageViewRes);
            textViewSuggestRes = view.findViewById(R.id.textViewSuggestRes);
            textViewStar = view.findViewById(R.id.textViewStar);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.search_page_restau_suggest_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SearchPage_SuggestRestauItem item = itemList.get(position);
        holder.imageView.setImageResource(item.getImageResource());
        holder.textViewSuggestRes.setText(item.getText());
        holder.textViewStar.setText(item.getNum());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int adapterPosition = holder.getAdapterPosition();
                SearchPage_SuggestRestauItem selectedItem = itemList.get(adapterPosition);
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
