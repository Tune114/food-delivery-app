package com.study.fooddeliveryapplication.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.study.fooddeliveryapplication.R;
import com.study.fooddeliveryapplication.model.Category;

import java.util.List;

public class RestCateItemsAdapter extends RecyclerView.Adapter<RestCateItemsAdapter.RestCateItemsViewHolder>{

    private List<Category> categories;

    public RestCateItemsAdapter(List<Category> categories) {
        this.categories = categories;
    }

    @NonNull
    @Override
    public RestCateItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.rest_cate_items,parent,false);
        return new RestCateItemsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RestCateItemsViewHolder holder, int position) {
        holder.button.setText(categories.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    class RestCateItemsViewHolder extends RecyclerView.ViewHolder{

        private Button button;
        private CardView cardView;

        public RestCateItemsViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView=itemView.findViewById(R.id.cate_card_view);
            button = itemView.findViewById(R.id.category_items_btn);
        }
    }
}
