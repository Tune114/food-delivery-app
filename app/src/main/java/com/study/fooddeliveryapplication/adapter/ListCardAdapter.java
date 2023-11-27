package com.study.fooddeliveryapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.study.fooddeliveryapplication.model.CardForPayment;
import com.study.fooddeliveryapplication.R;
import java.util.List;

public class ListCardAdapter extends RecyclerView.Adapter<ListCardAdapter.PaymentViewHolder>{

    private List<CardForPayment> listCard;
    public ListCardAdapter(List<CardForPayment> listCard){
        this.listCard = listCard;
    }
    @NonNull
    @Override
    public PaymentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card , parent , false);
        return new PaymentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentViewHolder holder, int position) {
        holder.mText.setText(listCard.get(position).getCardName());
        holder.mImageview.setImageResource(listCard.get(position).getImg());
    }

    @Override
    public int getItemCount() {
        return listCard.size();
    }

    public class PaymentViewHolder  extends RecyclerView.ViewHolder{
        private ImageView mImageview;
        private TextView mText;
        public PaymentViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageview = itemView.findViewById(R.id.imgCard);
            mText = itemView.findViewById(R.id.cardName);
        }
    }
}
