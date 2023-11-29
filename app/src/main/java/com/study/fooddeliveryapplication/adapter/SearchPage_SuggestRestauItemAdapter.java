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
import com.study.fooddeliveryapplication.model.SearchPage_SuggestRestauItem;
import com.study.fooddeliveryapplication.ui.RestaurantDetails;

public class SearchPage_SuggestRestauItemAdapter extends FirebaseRecyclerAdapter<SearchPage_SuggestRestauItem,SearchPage_SuggestRestauItemAdapter.myViewHolder> {

    public SearchPage_SuggestRestauItemAdapter(@NonNull FirebaseRecyclerOptions<SearchPage_SuggestRestauItem> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull SearchPage_SuggestRestauItem SearchPage_SuggestRestauItem) {
        holder.SuggestRestItemName.setText(SearchPage_SuggestRestauItem.getSuggestRestItemName());
        holder.SuggestRestItemStar.setText(SearchPage_SuggestRestauItem.getSuggestRestItemStar());
        Glide.with(holder.SuggestRestItemImage.getContext())
                .load(SearchPage_SuggestRestauItem.getSuggestRestItemImage())
                .placeholder(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                .error(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                .into(holder.SuggestRestItemImage);

        holder.itemView.setOnClickListener(view -> {
            String SuggestRestItemName = SearchPage_SuggestRestauItem.getSuggestRestItemName();
            String SuggestRestItemStar = SearchPage_SuggestRestauItem.getSuggestRestItemStar();
            String SuggestRestItemImage = SearchPage_SuggestRestauItem.getSuggestRestItemImage();

            Log.d("SearchPage_SuggestRestauItem", SuggestRestItemName + " "+ SuggestRestItemStar+ " "+ SuggestRestItemImage);

            Intent intent = new Intent(view.getContext(), RestaurantDetails.class);

            intent.putExtra("SuggestRestItemName", SuggestRestItemName);
            intent.putExtra("SuggestRestItemStar", SuggestRestItemStar);
            intent.putExtra("SuggestRestItemImage", SuggestRestItemImage);

            view.getContext().startActivity(intent);
        });
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_page_restau_suggest_item,parent,false);
        return new myViewHolder(view);
    }

    static class myViewHolder extends RecyclerView.ViewHolder{
        TextView SuggestRestItemName,SuggestRestItemStar;
        ImageView SuggestRestItemImage;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            SuggestRestItemName = (TextView)itemView.findViewById(R.id.tvSuggestRestItemName);
            SuggestRestItemStar = (TextView) itemView.findViewById(R.id.tvSuggestRestItemStar);
            SuggestRestItemImage =  itemView.findViewById(R.id.ivSuggestRestItemImage);
        }
    }
}
