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
import com.study.fooddeliveryapplication.ui.RestaurantDetails;

public class RestauItemAdapter extends FirebaseRecyclerAdapter<RestauItem,RestauItemAdapter.myViewHolder> {
    public RestauItemAdapter(@NonNull FirebaseRecyclerOptions<RestauItem> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull RestauItem RestauItem) {
        holder.RestName.setText(RestauItem.getRestName());
        holder.RestDescrip.setText(RestauItem.getRestDescrip());
        Glide.with(holder.RestImage.getContext())
                .load(RestauItem.getRestImage())
                .placeholder(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                .error(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                .into(holder.RestImage);

        holder.itemView.setOnClickListener(view -> {
            String RestName = RestauItem.getRestName();
            String RestDescrip = RestauItem.getRestDescrip();
            String RestImage = RestauItem.getRestImage();

            Log.d("RestauItem", RestName + " " + RestDescrip + " "+ RestImage);

            Intent intent = new Intent(view.getContext(), RestaurantDetails.class);

            intent.putExtra("RestName", RestName);
            intent.putExtra("RestDescrip", RestDescrip);
            intent.putExtra("RestImage", RestImage);

            view.getContext().startActivity(intent);
        });
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.restau_item,parent,false);
        return new myViewHolder(view);
    }

    static class myViewHolder extends RecyclerView.ViewHolder{
        TextView RestName,RestDescrip;
        ImageView RestImage;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            RestName = (TextView)itemView.findViewById(R.id.textView1Res);
            RestDescrip = (TextView) itemView.findViewById(R.id.textView2Res);
            RestImage =  itemView.findViewById(R.id.imageViewRes);
        }
    }
}


