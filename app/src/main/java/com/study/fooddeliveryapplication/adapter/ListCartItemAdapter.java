package com.study.fooddeliveryapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.study.fooddeliveryapplication.R;
import com.study.fooddeliveryapplication.model.CardForPayment;

import java.util.List;

public class ListCartItemAdapter extends RecyclerView.Adapter<ListCartItemAdapter.PaymentViewHolder>{

    private List<String> list;
    public ListCartItemAdapter(List<String> listCard){
        this.list = listCard;
    }
    @NonNull
    @Override
    public PaymentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart , parent , false);
        return new ListCartItemAdapter.PaymentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentViewHolder holder, int position) {
        holder.mIconview.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class PaymentViewHolder  extends RecyclerView.ViewHolder {
        private TextView mIconview;

        public PaymentViewHolder(@NonNull View itemView) {
            super(itemView);
            mIconview = itemView.findViewById(R.id.textPizzaName);


        }
    }
}
