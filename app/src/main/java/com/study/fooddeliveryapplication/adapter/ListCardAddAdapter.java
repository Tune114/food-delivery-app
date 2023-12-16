package com.study.fooddeliveryapplication.adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.study.fooddeliveryapplication.R;
import com.study.fooddeliveryapplication.model.CardPayment;
import com.study.fooddeliveryapplication.ui.AddCartActivity;

public class ListCardAddAdapter extends FirebaseRecyclerAdapter<CardPayment,ListCardAddAdapter.PaymentViewHolder> {


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public ListCardAddAdapter(@NonNull FirebaseRecyclerOptions<CardPayment> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull PaymentViewHolder holder, int position, @NonNull CardPayment model) {
        holder.mCardNumber.setText(model.getCardNumber());

        Glide.with(holder.mIconview.getContext())
                .load(model.getCardIcon())
                .placeholder(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                .error(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                .into(holder.mIconview);
        holder.constraintLayout.setOnClickListener(view -> {
            String cardNumber = model.getCardNumber();
            Intent intent = new Intent(view.getContext(), AddCartActivity.class);
            intent.putExtra("cardNumber", cardNumber);
            view.getContext().startActivity(intent);
        });
        holder.mtxtDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.mCardNumber.getContext());
                builder.setTitle("Are you sure ?");
                builder.setMessage("Deleted data can't be Undo");
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseDatabase.getInstance().getReference().child("CardForPayment")
                                .child(getRef(holder.getAdapterPosition()).getKey()).removeValue();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(holder.mCardNumber.getContext(), "Canceled",Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });

    }

    @NonNull
    @Override
    public PaymentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_add, parent, false);
        return  new PaymentViewHolder(view);
    }


    public class PaymentViewHolder  extends RecyclerView.ViewHolder {
        private ImageView mIconview;
        private TextView mCardNumber, mtxtDelete;
        ConstraintLayout constraintLayout;


        public PaymentViewHolder(@NonNull View itemView) {
            super(itemView);
            mIconview = itemView.findViewById(R.id.iconCard);
            mCardNumber = itemView.findViewById(R.id.cardNumber);
            mtxtDelete = itemView.findViewById(R.id.txtDeleteCard);
            constraintLayout = itemView.findViewById(R.id.addCardToUse);
        }
    }
}