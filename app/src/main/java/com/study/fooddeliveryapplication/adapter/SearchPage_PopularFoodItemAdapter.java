package com.study.fooddeliveryapplication.adapter;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.study.fooddeliveryapplication.R;
import com.study.fooddeliveryapplication.model.SearchPage_PopularFoodItem;
import com.study.fooddeliveryapplication.ui.FoodDetailsActivity;

public class SearchPage_PopularFoodItemAdapter extends FirebaseRecyclerAdapter<SearchPage_PopularFoodItem,SearchPage_PopularFoodItemAdapter.myViewHolder> {

    public SearchPage_PopularFoodItemAdapter(@NonNull FirebaseRecyclerOptions<SearchPage_PopularFoodItem> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull SearchPage_PopularFoodItem SearchPage_PopularFoodItem) {
        holder.PopularFoodName.setText(SearchPage_PopularFoodItem.getPopularFoodName());
        holder.PopularFoodResName.setText(SearchPage_PopularFoodItem.getPopularFoodResName());
        Glide.with(holder.PopularFoodImage.getContext())
                .load(SearchPage_PopularFoodItem.getPopularFoodImage())
                .placeholder(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                .error(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                .into(holder.PopularFoodImage);

        holder.itemView.setOnClickListener(view -> {
            String PopularFoodName = SearchPage_PopularFoodItem.getPopularFoodName();
            String PopularFoodResName = SearchPage_PopularFoodItem.getPopularFoodResName();
            String PopularFoodImage = SearchPage_PopularFoodItem.getPopularFoodImage();

            Log.d("SearchPage_PopularFoodItem",PopularFoodName + " "+ PopularFoodResName + " "+ PopularFoodImage);

            Intent intent = new Intent(view.getContext(), FoodDetailsActivity.class);

            intent.putExtra("PopularFoodName", PopularFoodName);
            intent.putExtra("PopularFoodResName", PopularFoodResName);
            intent.putExtra("PopularFoodImage", PopularFoodImage);

            view.getContext().startActivity(intent);
        });
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_page_popular_food_item,parent,false);
        return new myViewHolder(view);
    }

    static class myViewHolder extends RecyclerView.ViewHolder{
        TextView PopularFoodName,PopularFoodResName;
        ImageView PopularFoodImage;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            PopularFoodName = (TextView)itemView.findViewById(R.id.tvPopularFoodName);
            PopularFoodResName = (TextView) itemView.findViewById(R.id.tvPopularFoodResName);
            PopularFoodImage =  itemView.findViewById(R.id.ivPopularFoodImage);
        }
    }
}
