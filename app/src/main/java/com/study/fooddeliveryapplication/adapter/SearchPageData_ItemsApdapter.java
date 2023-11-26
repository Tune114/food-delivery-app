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

import java.util.ArrayList;

public class SearchPageData_ItemsApdapter extends FirebaseRecyclerAdapter<SearchPage_PopularFoodItem,SearchPageData_ItemsApdapter.myViewHolder> {

    public SearchPageData_ItemsApdapter(@NonNull FirebaseRecyclerOptions<SearchPage_PopularFoodItem> options) {
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
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String PopularFoodName = SearchPage_PopularFoodItem.getPopularFoodName();
                String PopularFoodResName = SearchPage_PopularFoodItem.getPopularFoodResName();
                String PopularFoodImage = SearchPage_PopularFoodItem.getPopularFoodImage();

                Log.d("SearchPageData_PopularFoodItem",PopularFoodName + " "+ PopularFoodResName + " "+ PopularFoodImage);

                Intent intent = new Intent(view.getContext(), FoodDetailsActivity.class);

                intent.putExtra("PopularFoodName", PopularFoodName);
                intent.putExtra("PopularFoodResName", PopularFoodResName);
                intent.putExtra("PopularFoodImage", PopularFoodImage);

                view.getContext().startActivity(intent);
            }
        });
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_page_data,parent,false);
        return new SearchPageData_ItemsApdapter.myViewHolder(view);
    }

    static class myViewHolder extends RecyclerView.ViewHolder{
        TextView PopularFoodName,PopularFoodResName;
        ImageView PopularFoodImage;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            PopularFoodName = (TextView)itemView.findViewById(R.id.tv_SPD_Name1);
            PopularFoodResName = (TextView) itemView.findViewById(R.id.tv_SPD_Name2);
            PopularFoodImage =  itemView.findViewById(R.id.iv_SPD_Image);
        }
    }
    public void searchDataList(ArrayList<SearchPage_PopularFoodItem> searchList){
        
    }
}
