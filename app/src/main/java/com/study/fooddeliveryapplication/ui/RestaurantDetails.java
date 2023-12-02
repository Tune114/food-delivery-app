package com.study.fooddeliveryapplication.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.study.fooddeliveryapplication.R;
import com.study.fooddeliveryapplication.adapter.CommentAdapter;
import com.study.fooddeliveryapplication.adapter.FoodListAdapter;
import com.study.fooddeliveryapplication.adapter.IngredientListAdapter;
import com.study.fooddeliveryapplication.adapter.RestCateItemsAdapter;
import com.study.fooddeliveryapplication.model.Category;
import com.study.fooddeliveryapplication.model.Food;
import com.study.fooddeliveryapplication.model.FoodItem;
import com.study.fooddeliveryapplication.model.IngredientItem;

import java.util.ArrayList;
import java.util.List;

public class FoodDetailsActivity extends AppCompatActivity {

    private ImageButton btnLove;
    private TextView textLoveCount;
    private int loveCount = 0;
    private Button btnSizeNho;
    private Button btnSizeVua;
    private Button btnSizeLon;
    private TextView textPrice;
    private TextView txtQuantity;
    private TextView txtSumPrice;
    private Button btnPlus;
    private Button btnMinus;
    private int quantity = 0;
    private int price = 0;

    private ImageButton btnBack;
    private Button btnAddToCart;
    private ArrayList<FoodItem> foodList;
    private RecyclerView mRecyclerView;
    private FoodListAdapter mFoodListAdapter;
    private List<FoodItem> mFoodList;
    private RecyclerView xrecyclerView;
    private IngredientListAdapter xadapter;
    private List<IngredientItem> xingredientList;
    private DatabaseReference databaseReference;

    private List<Category> categories;
    private RecyclerView categoriesRecyclerView;
    private RecyclerView foodRecyclerView;
    private RestCateItemsAdapter categoryAdapter;

    private List<Food> foods;
    private ImageView fimage;
    private TextView fname;
    private TextView fdescrip;

    private TextView fprice;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dish_details);
        List<Pair<String, String>> data = new ArrayList<>();
        data.add(new Pair<>("Đinh Công Minh\nMón này khá ngon", "anya"));
        data.add(new Pair<>("Đỗ Viết Thắng\nĐặt phần lớn thì khá nhiều", "gabimaru"));
        data.add(new Pair<>("Phạm Tấn Tú\nTạm ổn", "icon_ava"));
        data.add(new Pair<>("Trần Đình Thọ\nSẽ mua lại lần sau", "logo_valorant"));


        ListView listView = findViewById(R.id.list_comment);
        CommentAdapter adapter = new CommentAdapter(this, data);
        listView.setAdapter(adapter);

        mFoodList = new ArrayList<>();

        // Khởi tạo RecyclerView và FoodListAdapter
        mRecyclerView = findViewById(R.id.food_item);
        mFoodListAdapter = new FoodListAdapter(this, mFoodList);

        // Thiết lập LayoutManager và Adapter cho RecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mFoodListAdapter);

        // Thiết lập Adapter cho ListView

        // Thêm dữ liệu vào danh sách món ăn
        addFoodItem("Bún bò", getResources().getDrawable(R.drawable.bunbo));
        addFoodItem("Bún chả", getResources().getDrawable(R.drawable.buncha));
        addFoodItem("Bánh cuốn", getResources().getDrawable(R.drawable.banhcuon));
        addFoodItem("Bánh mì", getResources().getDrawable(R.drawable.banhmi));
        addFoodItem("Burger", getResources().getDrawable(R.drawable.burgers));
        addFoodItem("Bún đậu", getResources().getDrawable(R.drawable.bundau));

        // Khởi tạo danh sách nguyên liệu



        ImageView imageFood = findViewById(R.id.image_food);
        TextView dishName = findViewById(R.id.tv_dish_name);
        TextView dishInfo = findViewById(R.id.tv_dish_info);
        btnLove = findViewById(R.id.btn_love);
        textLoveCount = findViewById(R.id.text_love_count);
        btnSizeNho = findViewById(R.id.btn_size_nho);
        btnSizeVua = findViewById(R.id.btn_size_vua);
        btnSizeLon = findViewById(R.id.btn_size_lon);
        textPrice = findViewById(R.id.text_price);
        txtQuantity = findViewById(R.id.txt_quantity);
        txtSumPrice = findViewById(R.id.text_sumprice);
        btnPlus = findViewById(R.id.btn_plus);
        btnMinus = findViewById(R.id.btn_minus);
        btnBack = findViewById(R.id.button_back);
        btnAddToCart=findViewById(R.id.add_to_cart);

        btnSizeNho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textPrice.setText("50000");
                calculateSumPrice();
            }
        });
        xrecyclerView = findViewById(R.id.ingredient_item);
        LinearLayoutManager olayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        xrecyclerView.setLayoutManager(olayoutManager);
        Intent intent=getIntent();
        String NameofFood=intent.getStringExtra("foodName");
        String NameofCate=intent.getStringExtra("categoryName");
        fname=findViewById(R.id.tv_dish_name);
        fdescrip=findViewById(R.id.tv_dish_info);
        fimage=findViewById(R.id.image_food);
        fprice=findViewById(R.id.text_price);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference restaurantsRef = database.getReference("restaurants");

        restaurantsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot restaurantSnapshot : dataSnapshot.getChildren()) {
                    if (restaurantSnapshot.hasChild("categories") && restaurantSnapshot.hasChild("foods")) {
                        DataSnapshot categoriesSnapshot = restaurantSnapshot.child("categories");
                        DataSnapshot foodsSnapshot = restaurantSnapshot.child("foods");

                        for (DataSnapshot categorySnapshot : categoriesSnapshot.getChildren()) {
                            String categoryName = categorySnapshot.child("category_Name").getValue(String.class);

                            for (DataSnapshot foodSnapshot : foodsSnapshot.getChildren()) {
                                String foodName = foodSnapshot.child("name").getValue(String.class);

                                // So sánh cả "foodName" và "categoryName"
                                if (foodName != null && foodName.equals(NameofFood)
                                        && categoryName != null && categoryName.equals(NameofCate)) {
                                    String foodNameResult = foodSnapshot.child("name").getValue(String.class);
                                    String foodPriceResult = foodSnapshot.child("price").getValue(String.class);
                                    String foodImageResult = foodSnapshot.child("image").getValue(String.class);
                                    String foodDescriptionResult = foodSnapshot.child("description").getValue(String.class);

                                    // In ra thông tin
                                    Log.d("FirebaseSearch", "Tìm thấy kết quả: " +
                                            "Food Name: " + foodNameResult +
                                            ", Food Price: " + foodPriceResult +
                                            ", Food Image: " + foodImageResult +
                                            ", Food Description: " + foodDescriptionResult);

                                    break;
                                }
                            }
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Xử lý lỗi nếu có
                Log.e("FirebaseSearch", "Lỗi: " + databaseError.getMessage());
            }
        });



        btnSizeLon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textPrice.setText("60000");
                calculateSumPrice();
            }
        });

        btnLove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLoveButtonClick(v);
                calculateSumPrice();
            }
        });


        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                increaseQuantity();
            }
        });

        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decreaseQuantity();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(FoodDetailsActivity.this, SearchPageActivity.class);
                startActivity(intent);
            }
        });
        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(FoodDetailsActivity.this, AddCartActivity.class);
                startActivity(intent);
            }
        });

    }


    public void onLoveButtonClick(View view) {
        loveCount++;
        textLoveCount.setText(String.valueOf(loveCount));
    }

    private void increaseQuantity() {
        quantity++;
        txtQuantity.setText(String.valueOf(quantity));
        calculateSumPrice();
    }

    private void decreaseQuantity() {
        if (quantity > 0) {
            quantity--;
            txtQuantity.setText(String.valueOf(quantity));
            calculateSumPrice();
        }
    }
    private void addFoodItem(String name, Drawable imageDrawable) {
        FoodItem newItem = new FoodItem();
        newItem.setName(name);
        newItem.setImageDrawable(imageDrawable);
        mFoodList.add(newItem);
        mFoodListAdapter.notifyDataSetChanged();
    }
    private void calculateSumPrice() {
        price = Integer.parseInt(textPrice.getText().toString());
        int sumPrice = price * quantity;
        txtSumPrice.setText(String.valueOf(sumPrice));
    }
}