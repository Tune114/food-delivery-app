package com.study.fooddeliveryapplication.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.study.fooddeliveryapplication.R;
import com.study.fooddeliveryapplication.model.RestauItem;
import com.study.fooddeliveryapplication.ui.RestaurantDetails;
import java.util.List;

public class RestaurantListAdapter extends RecyclerView.Adapter<RestaurantListAdapter.MyViewHolder>{


    private List<RestauItem> restauItems;

    public RestaurantListAdapter(List<RestauItem> restauItems) {
        this.restauItems = restauItems;
    }

    @NonNull
    @Override
    public RestaurantListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.restau_item, parent, false);
        return new RestaurantListAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantListAdapter.MyViewHolder holder, int position)
    {
        holder.restName.setText(restauItems.get(position).getRestName());
        holder.restDescription.setText(restauItems.get(position).getRestDescrip());
        Glide.with(holder.restImage.getContext())
                .load(restauItems.get(position).getRestImage())
                .placeholder(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                .error(com.google.firebase.database.R.drawable.common_google_signin_btn_icon_dark)
                .into(holder.restImage);

        holder.restImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, RestaurantDetails.class);
                int clickedPosition = holder.getAdapterPosition();

                if (clickedPosition != RecyclerView.NO_POSITION) {
                    intent.putExtra("RestName", restauItems.get(clickedPosition).getRestName());
                    if (context instanceof Activity) {
                        ((Activity) context).startActivity(intent);
                    }
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return restauItems.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView restName, restDescription;
        private ImageView restImage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            restName=itemView.findViewById(R.id.textView1Res);
            restDescription=itemView.findViewById(R.id.textView2Res);
            restImage=itemView.findViewById(R.id.imageViewRes);
        }
    }
}
