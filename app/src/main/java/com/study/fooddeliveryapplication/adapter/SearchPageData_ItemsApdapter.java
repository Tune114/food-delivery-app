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
import com.study.fooddeliveryapplication.model.RestauItem;
import com.study.fooddeliveryapplication.model.SearchPage_PopularFoodItem;
import com.study.fooddeliveryapplication.ui.RestaurantDetailsActivity;

import java.util.ArrayList;

public class SearchPageData_ItemsApdapter extends FirebaseRecyclerAdapter<RestauItem,SearchPageData_ItemsApdapter.myViewHolder> {

    public SearchPageData_ItemsApdapter(@NonNull FirebaseRecyclerOptions<RestauItem> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull RestauItem restauItem) {
        holder.RestName.setText(restauItem.getRestName());
        holder.RestDescrip.setText(restauItem.getRestDescrip());
        Glide.with(holder.RestImage.getContext())
                .load(restauItem.getRestImage())
                .placeholder(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                .error(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                .into(holder.RestImage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String RestName = restauItem.getRestName();
                String RestDescrip = restauItem.getRestDescrip();
                String RestImage = restauItem.getRestImage();

                Log.d("SearchPageData_PopularFoodItem",RestName + " "+ RestDescrip + " "+ RestImage);

                Intent intent = new Intent(view.getContext(), RestaurantDetailsActivity.class);

                intent.putExtra("RestName", RestName);
                intent.putExtra("RestDescrip", RestDescrip);
                intent.putExtra("RestImage", RestImage);

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
        TextView RestName,RestDescrip;
        ImageView RestImage;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            RestName = (TextView)itemView.findViewById(R.id.tv_SPD_Name1);
            RestDescrip = (TextView) itemView.findViewById(R.id.tv_SPD_Name2);
            RestImage =  itemView.findViewById(R.id.iv_SPD_Image);
        }
    }
    public void searchDataList(ArrayList<SearchPage_PopularFoodItem> searchList){

    }
}