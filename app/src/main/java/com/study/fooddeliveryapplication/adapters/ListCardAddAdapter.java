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

public class ListCardAddAdapter extends RecyclerView.Adapter<ListCardAddAdapter.PaymentViewHolder>{
    private List<CardForPayment> listCard;
    public ListCardAddAdapter(List<CardForPayment> listCard){
        this.listCard = listCard;
    }

    @NonNull
    @Override
    public PaymentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_add , parent , false);
        return new ListCardAddAdapter.PaymentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentViewHolder holder, int position) {
        holder.mCardNumber.setText(listCard.get(position).getCardNumber());
        holder.mIconview.setImageResource(listCard.get(position).getIcon());
        holder.mCardName.setText(listCard.get(position).getCardName());
    }

    @Override
    public int getItemCount() {
        return listCard.size();
    }


    public class PaymentViewHolder  extends RecyclerView.ViewHolder {
        private ImageView mIconview;
        private TextView mCardName;
        private TextView mCardNumber;

        public PaymentViewHolder(@NonNull View itemView) {
            super(itemView);
            mIconview = itemView.findViewById(R.id.iconCard);
            mCardName = itemView.findViewById(R.id.iconCardName);
            mCardNumber = itemView.findViewById(R.id.cardNumber);

        }
    }
}
