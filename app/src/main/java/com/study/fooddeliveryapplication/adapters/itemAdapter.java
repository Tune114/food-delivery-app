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
import com.study.fooddeliveryapplication.model.Item;
import com.study.fooddeliveryapplication.ui.FoodDetailsActivity;


public class itemAdapter extends FirebaseRecyclerAdapter<Item,itemAdapter.myViewHolder> {
    public itemAdapter(@NonNull FirebaseRecyclerOptions<Item> options) {
        super(options);
    }
    @Override
    protected void onBindViewHolder(@NonNull itemAdapter.myViewHolder holder, int position, @NonNull Item item) {
        holder.FoodName.setText(item.getFoodName());
        Glide.with(holder.FoodImage.getContext())
                .load(item.getFoodImage())
                .placeholder(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                .circleCrop()
                .error(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                .into(holder.FoodImage);

        holder.itemView.setOnClickListener(view -> {
            String foodName = item.getFoodName();
            String imageUrl = item.getFoodImage();

            Log.d("Itemview", foodName + " " + imageUrl);

            Intent intent = new Intent(view.getContext(), FoodDetailsActivity.class);

            intent.putExtra("foodName", foodName);
            intent.putExtra("imageUrl", imageUrl);

            view.getContext().startActivity(intent);
        });
    }
    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,false);
        return new myViewHolder(view);
    }

    static class myViewHolder extends RecyclerView.ViewHolder{
        TextView FoodName;
        ImageView FoodImage;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            FoodName = itemView.findViewById(R.id.tvHPFoodItemsName);
            FoodImage = itemView.findViewById(R.id.ivHPFoodItemsImage);
        }
    }
}



