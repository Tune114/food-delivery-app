package com.study.fooddeliveryapplication.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.study.fooddeliveryapplication.R;
import com.study.fooddeliveryapplication.model.Category;
import com.study.fooddeliveryapplication.model.Food;

import java.util.ArrayList;
import java.util.List;

public class RestCateItemsAdapter extends RecyclerView.Adapter<RestCateItemsAdapter.RestCateItemsViewHolder> {
    private UpdateRestInfor updateRestFoodItems;
    private List<Category> categories;
    private List<Food> foods;
    private int categoryIndex = 0;
    public RestCateItemsAdapter(List<Category> categories) {
        this.categories = categories;
    }


    public RestCateItemsAdapter(UpdateRestInfor updateRestFoodItems, List<Category> categories) {
        this.updateRestFoodItems = updateRestFoodItems;
        this.categories = categories;
        this.foods = new ArrayList<>();
    }

    @NonNull
    @Override
    public RestCateItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rest_cate_items, parent, false);
        return new RestCateItemsViewHolder(view);
    }

//    public List<Food> getFoodsByCategory(String category){
//        List<Food> foodsList=new ArrayList<>();
//        DatabaseReference databaseReference= databaseReference= FirebaseDatabase.getInstance().getReference().child("restaurants");
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//            main:   for(DataSnapshot ds:snapshot.getChildren()){
//                        DataSnapshot categoriesSnapshot = ds.child("categories");
//                        for(DataSnapshot cateDs:categoriesSnapshot.getChildren()){
//                            String categoryName=cateDs.child("category_name").getValue(String.class);
//                            if(categoryName.equals(category)){
//                                DataSnapshot foodsSnapshot = cateDs.child("foods");
//                                for(DataSnapshot foodDs:foodsSnapshot.getChildren()){
//                                    Food food=foodDs.getValue(Food.class);
//                                    foodsList.add(food);
//                                }
//                                break main;
//                            }
//                        }
//                    }
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//        return foodsList;
//    }

    public void unSelectAll(){
        for(int i=0;i<categories.size();i++){
            categories.get(i).setSelected(false);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RestCateItemsViewHolder holder, int position) {
        holder.button.setText(categories.get(position).getName());

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                categoryIndex = holder.getAdapterPosition();
                List<Food> foods=categories.get(categoryIndex).getFoods();
                updateRestFoodItems.callBack(foods);
                unSelectAll();
                categories.get(categoryIndex).setSelected(true);
                notifyDataSetChanged();
                updateRestFoodItems.updateCategoryLabel(categories.get(categoryIndex).getFoods().size(),
                        categories.get(categoryIndex).getName());
            }
        });

        if(categories.get(position).isSelected()){
            holder.button.setBackgroundResource(R.drawable.custom_btn_selected);
            holder.button.setTextColor(Color.parseColor("#FF000000"));
        }else{
            holder.button.setBackgroundResource(R.drawable.custom_btn_unselected);
            holder.button.setTextColor(Color.parseColor("#FFFFFFFF"));
        }
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    class RestCateItemsViewHolder extends RecyclerView.ViewHolder {

        private Button button;
        private CardView cardView;

        public RestCateItemsViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.food_card_view);
            button = itemView.findViewById(R.id.category_items_btn);
        }
    }
}