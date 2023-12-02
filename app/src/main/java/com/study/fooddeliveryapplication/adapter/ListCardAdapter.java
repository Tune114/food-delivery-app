package com.study.fooddeliveryapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.study.fooddeliveryapplication.R;
import com.study.fooddeliveryapplication.model.ModelCard;

import java.util.List;

public class ListCardAdapter extends FirebaseRecyclerAdapter<ModelCard, ListCardAdapter.PaymentViewHolder> {

    private List<ModelCard> listCard;


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public ListCardAdapter(@NonNull FirebaseRecyclerOptions<ModelCard> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull PaymentViewHolder holder, int position, @NonNull ModelCard model) {
        holder.mText.setText(model.getCardName());

        Glide.with(holder.mImageview.getContext())
                .load(model.getImg())
                .placeholder(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                .error(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                .into(holder.mImageview);
        holder.mImageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int color = ContextCompat.getColor(holder.itemView.getContext(), R.color.hint_text);
                holder.layout.setBackgroundColor(color);

            }
        });
    }

    @NonNull
    @Override
    public PaymentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
       return  new PaymentViewHolder(view);
    }


    public class PaymentViewHolder  extends RecyclerView.ViewHolder{
        private ImageView mImageview;
        private TextView mText;
        private ConstraintLayout layout;
        public PaymentViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageview = itemView.findViewById(R.id.imgCard);
            mText = itemView.findViewById(R.id.cardName);
            layout = itemView.findViewById(R.id.layoutCard);
        }
    }
}
