package com.study.fooddeliveryapplication.adapter;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.study.fooddeliveryapplication.R;
import com.study.fooddeliveryapplication.model.SearchPage_RecentItem;
import com.study.fooddeliveryapplication.ui.FoodDetailsActivity;

public class SearchPage_RecentitemAdapter extends FirebaseRecyclerAdapter<SearchPage_RecentItem,SearchPage_RecentitemAdapter.myViewHolder> {

    public SearchPage_RecentitemAdapter(@NonNull FirebaseRecyclerOptions<SearchPage_RecentItem> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull SearchPage_RecentItem SearchPage_RecentItem) {
        holder.RecentItemName.setText(SearchPage_RecentItem.getRecentItemName());

        holder.itemView.setOnClickListener(view -> {
            String RecentItemName = SearchPage_RecentItem.getRecentItemName();

            Log.d("SearchPage_RecentItem", RecentItemName);

            Intent intent = new Intent(view.getContext(), FoodDetailsActivity.class);

            intent.putExtra("RecentItemName", RecentItemName);

            view.getContext().startActivity(intent);
        });
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_page_recent_item,parent,false);
        return new myViewHolder(view);
    }

    static class myViewHolder extends RecyclerView.ViewHolder{
        TextView RecentItemName;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            RecentItemName = (TextView)itemView.findViewById(R.id.tvRecentItemName);
        }
    }
}
