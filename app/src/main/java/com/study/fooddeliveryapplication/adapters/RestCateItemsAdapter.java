package com.study.fooddeliveryapplication.adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.study.fooddeliveryapplication.R;
import com.study.fooddeliveryapplication.model.Category;
import com.study.fooddeliveryapplication.model.Food;

import java.util.ArrayList;
import java.util.List;

public class RestCateItemsAdapter extends RecyclerView.Adapter<RestCateItemsAdapter.RestCateItemsViewHolder> {

    private UpdateRestFoodItems updateRestFoodItems;
    private List<Category> categories;
    private boolean check = true;
    private boolean select = true;
    private int index = -1;

    public RestCateItemsAdapter(List<Category> categories) {
        this.categories = categories;
    }

    public RestCateItemsAdapter(UpdateRestFoodItems updateRestFoodItems, List<Category> categories) {
        this.updateRestFoodItems = updateRestFoodItems;
        this.categories = categories;
    }

    @NonNull
    @Override
    public RestCateItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rest_cate_items, parent, false);
        return new RestCateItemsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RestCateItemsViewHolder holder, int position) {
        holder.button.setText(categories.get(position).getName());

//        if(check){
//            List<Food> foods=new ArrayList<>();
//            foods.add(new Food("Burger king","Spicy","40$",R.drawable.burgers));
//            foods.add(new Food("Burger king","Spicy","40$",R.drawable.burgers));
//            foods.add(new Food("Burger king","Spicy","40$",R.drawable.burgers));
//            foods.add(new Food("Burger king","Spicy","40$",R.drawable.burgers));
//            updateRestFoodItems.callBack(position,foods);
//
//            check=false;
//            holder.cardView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    List<Food> foods=new ArrayList<>();
//                    index=holder.getAdapterPosition();
//                    notifyDataSetChanged();
//                    if (holder.getAdapterPosition()==0){
//                        foods.clear();
//                        foods.add(new Food("Burger king","Spicy","40$",R.drawable.burgers));
//                        foods.add(new Food("Burger king","Spicy","40$",R.drawable.burgers));
//                        foods.add(new Food("Burger king","Spicy","40$",R.drawable.burgers));
//                        foods.add(new Food("Burger king","Spicy","40$",R.drawable.burgers));
//                        updateRestFoodItems.callBack(holder.getAdapterPosition(),foods);
//                    }else if(holder.getAdapterPosition()==1){
//                        foods.clear();
//                        foods.add(new Food("Seafood Pizza","Perfect","99$",R.drawable.pizza));
//                        foods.add(new Food("Seafood Pizza","Perfect","99$",R.drawable.pizza));
//                        foods.add(new Food("Seafood Pizza","Perfect","99$",R.drawable.pizza));
//                        foods.add(new Food("Seafood Pizza","Perfect","99$",R.drawable.pizza));
//                        updateRestFoodItems.callBack(holder.getAdapterPosition(),foods);
//                    }else if(holder.getAdapterPosition()==2){
//                        foods.clear();
//                        foods.add(new Food("Medium BBQ","For family","399$",R.drawable.bbq));
//                        foods.add(new Food("Medium BBQ","For family","399$",R.drawable.bbq));
//                        foods.add(new Food("Medium BBQ","For family","399$",R.drawable.bbq));
//                        foods.add(new Food("Medium BBQ","For family","399$",R.drawable.bbq));
//                        updateRestFoodItems.callBack(holder.getAdapterPosition(),foods);
//                    }else if(holder.getAdapterPosition()==3){
//                        foods.clear();
//                        foods.add(new Food("Hot pot","So hot!!","199$",R.drawable.hotpot));
//                        foods.add(new Food("Hot pot","So hot!!","199$",R.drawable.hotpot));
//                        foods.add(new Food("Medium BBQ","For family","399$",R.drawable.bbq));
//                        foods.add(new Food("Medium BBQ","For family","399$",R.drawable.bbq));
//                        updateRestFoodItems.callBack(holder.getAdapterPosition(),foods);
//                    }else if(holder.getAdapterPosition()==4){
//                        foods.clear();
//                        foods.add(new Food("Hot pot","So hot!!","199$",R.drawable.hotpot));
//                        foods.add(new Food("Hot pot","So hot!!","199$",R.drawable.hotpot));
//                        foods.add(new Food("Medium BBQ","For family","399$",R.drawable.bbq));
//                        foods.add(new Food("Medium BBQ","For family","399$",R.drawable.bbq));
//                        updateRestFoodItems.callBack(holder.getAdapterPosition(),foods);
//                    }
//                }
//            });
//            if(select){
//                if(position==0){
//                    holder.cardView.setBackgroundResource(R.drawable.custom_btn_selected);
//                    holder.button.setTextColor(Color.parseColor("#FF000000"));
//                    select=false; // For first item "Burger"
//                }
//            }else{
//                if(index==position){
//                    holder.cardView.setBackgroundResource(R.drawable.custom_btn_selected);
//                    holder.button.setTextColor(Color.parseColor("#FF000000"));
//                }else{
//                    holder.cardView.setBackgroundResource(R.drawable.custom_btn);
//                    holder.button.setTextColor(Color.parseColor("#FFFFFFFF"));
//                }
//            }
//        }

    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    class RestCateItemsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Button button;
        private CardView cardView;

        public RestCateItemsViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cate_card_view);
            button = itemView.findViewById(R.id.category_items_btn);
            cardView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    List<Food> foods = new ArrayList<>();
                    index = getAdapterPosition();
                    notifyDataSetChanged();
                    if (getAdapterPosition() == 0) {
                        foods.clear();
                        foods.add(new Food("Burger king", "Spicy", "40$", R.drawable.burgers));
                        foods.add(new Food("Burger king", "Spicy", "40$", R.drawable.burgers));
                        foods.add(new Food("Burger king", "Spicy", "40$", R.drawable.burgers));
                        foods.add(new Food("Burger king", "Spicy", "40$", R.drawable.burgers));
                        updateRestFoodItems.callBack(getAdapterPosition(), foods);
                    } else if (getAdapterPosition() == 1) {
                        foods.clear();
                        foods.add(new Food("Seafood Pizza", "Perfect", "99$", R.drawable.pizza));
                        foods.add(new Food("Seafood Pizza", "Perfect", "99$", R.drawable.pizza));
                        foods.add(new Food("Seafood Pizza", "Perfect", "99$", R.drawable.pizza));
                        foods.add(new Food("Seafood Pizza", "Perfect", "99$", R.drawable.pizza));
                        updateRestFoodItems.callBack(getAdapterPosition(), foods);
                    } else if (getAdapterPosition() == 2) {
                        foods.clear();
                        foods.add(new Food("Medium BBQ", "For family", "399$", R.drawable.bbq));
                        foods.add(new Food("Medium BBQ", "For family", "399$", R.drawable.bbq));
                        foods.add(new Food("Medium BBQ", "For family", "399$", R.drawable.bbq));
                        foods.add(new Food("Medium BBQ", "For family", "399$", R.drawable.bbq));
                        updateRestFoodItems.callBack(getAdapterPosition(), foods);
                    } else if (getAdapterPosition() == 3) {
                        foods.clear();
                        foods.add(new Food("Hot pot", "So hot!!", "199$", R.drawable.hotpot));
                        foods.add(new Food("Hot pot", "So hot!!", "199$", R.drawable.hotpot));
                        foods.add(new Food("Medium BBQ", "For family", "399$", R.drawable.bbq));
                        foods.add(new Food("Medium BBQ", "For family", "399$", R.drawable.bbq));
                        updateRestFoodItems.callBack(getAdapterPosition(), foods);
                    } else if (getAdapterPosition() == 4) {
                        foods.clear();
                        foods.add(new Food("Hot pot", "So hot!!", "199$", R.drawable.hotpot));
                        foods.add(new Food("Hot pot", "So hot!!", "199$", R.drawable.hotpot));
                        foods.add(new Food("Medium BBQ", "For family", "399$", R.drawable.bbq));
                        foods.add(new Food("Medium BBQ", "For family", "399$", R.drawable.bbq));
                        updateRestFoodItems.callBack(getAdapterPosition(), foods);
                    }
                }
            });
        }
    }
}
