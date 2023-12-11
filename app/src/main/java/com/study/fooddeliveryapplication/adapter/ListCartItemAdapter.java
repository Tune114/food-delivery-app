package com.study.fooddeliveryapplication.adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.database.FirebaseDatabase;
import com.study.fooddeliveryapplication.R;
import com.study.fooddeliveryapplication.model.ModelCart;

import java.util.Map;

public class ListCartItemAdapter extends FirebaseRecyclerAdapter<ModelCart,ListCartItemAdapter.PaymentViewHolder> {


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */

    public ListCartItemAdapter(@NonNull FirebaseRecyclerOptions<ModelCart> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull PaymentViewHolder holder, int position, @NonNull ModelCart model) {
        holder.mFoodName.setText(model.getFoodName());
        holder.mRestName.setText(model.getRestName());
        holder.mQuantity.setText(model.getQuantity());
        holder.mPrice.setText(model.getPrice());
        int total =Integer.parseInt(model.getPrice())*Integer.parseInt(model.getQuantity());
        holder.mTotal.setText(String.valueOf(total));
        Glide.with(holder.mImgFood.getContext())
                .load(model.getFoodImg())
                .placeholder(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                .error(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                .into(holder.mImgFood);
        holder.mDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.mFoodName.getContext());
                builder.setTitle("Are you sure ?");
                builder.setMessage("Deleted data can't be Undo");
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseDatabase.getInstance().getReference().child("ItemCart")
                                .child(getRef(holder.getAdapterPosition()).getKey()).removeValue();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(holder.mFoodName.getContext(), "Canceled",Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });
        holder.mIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newQuantity =Integer.parseInt(model.getQuantity())+1;
                model.setQuantity(String.valueOf(newQuantity));
                Map<String, Object> updateQuantity = model.toMap();
                FirebaseDatabase.getInstance().getReference().child("ItemCart")
                        .child(getRef(holder.getAdapterPosition()).getKey()).updateChildren(updateQuantity)
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(holder.mFoodName.getContext(), "Error", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
        holder.mDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newQuantity =Integer.parseInt(model.getQuantity())-1;
                if(newQuantity<1){
                    Toast.makeText(holder.mFoodName.getContext(), "Quantity cannot be less than 1", Toast.LENGTH_SHORT).show();
                }
                else{
                    model.setQuantity(String.valueOf(newQuantity));
                    Map<String, Object> updateQuantity = model.toMap();
                    FirebaseDatabase.getInstance().getReference().child("ItemCart")
                            .child(getRef(holder.getAdapterPosition()).getKey()).updateChildren(updateQuantity)
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(holder.mFoodName.getContext(), "Error", Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            }
        });

    }

    @NonNull
    @Override
    public PaymentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        return  new PaymentViewHolder(view);
    }

    public class PaymentViewHolder  extends RecyclerView.ViewHolder {
        private TextView mFoodName, mTotal, mPrice, mQuantity, mRestName, mDelete, mIncrease, mDecrease;
        private ImageView mImgFood;

        public PaymentViewHolder(@NonNull View itemView) {
            super(itemView);
            mFoodName = itemView.findViewById(R.id.txtFoodName);
            mPrice = itemView.findViewById(R.id.txtPrice);
            mImgFood = itemView.findViewById(R.id.imgFood);
            mQuantity = itemView.findViewById(R.id.txtQuantity);
            mRestName = itemView.findViewById(R.id.txtRestName);
            mTotal = itemView.findViewById(R.id.txtTotal);
            mDelete = itemView.findViewById(R.id.txtDelete);
            mIncrease = itemView.findViewById(R.id.txtIncrease);
            mDecrease = itemView.findViewById(R.id.txtDecrease);
        }
    }
}