package com.study.fooddeliveryapplication.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.study.fooddeliveryapplication.R;
import com.study.fooddeliveryapplication.adapter.CommentAdapter;
import com.study.fooddeliveryapplication.adapter.FoodListAdapter;
import com.study.fooddeliveryapplication.adapter.IngredientListAdapter;
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
    private ArrayList<FoodItem> foodList;
    private RecyclerView mRecyclerView;
    private FoodListAdapter mFoodListAdapter;
    private List<FoodItem> mFoodList;
    private RecyclerView xrecyclerView;
    private IngredientListAdapter xadapter;
    private List<IngredientItem> xingredientList;


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
        xingredientList = new ArrayList<>();

        // Khởi tạo RecyclerView
        xrecyclerView = findViewById(R.id.ingredient_item);
        LinearLayoutManager olayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        xrecyclerView.setLayoutManager(olayoutManager);


        xrecyclerView.setHasFixedSize(true);

        // Khởi tạo Adapter
        xadapter = new IngredientListAdapter(this, xingredientList);
        xrecyclerView.setAdapter(xadapter);

        // Lấy dữ liệu từ Firebase Realtime Database
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("ingredient");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String name = snapshot.child("name").getValue(String.class);
                    String image = snapshot.child("image").getValue(String.class);
                    IngredientItem item = new IngredientItem(name, image);
                    xingredientList.add(item);
                }
                xadapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Xử lý lỗi khi không thể đọc dữ liệu từ Firebase
                Toast.makeText(FoodDetailsActivity.this, "Failed to read data", Toast.LENGTH_SHORT).show();
            }
        });


        ImageView imageFood = findViewById(R.id.image_food);
        TextView dishName = findViewById(R.id.dish_name);
        TextView dishInfo = findViewById(R.id.dish_info);
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

        btnSizeNho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textPrice.setText("50000");
                calculateSumPrice();
            }
        });



        DatabaseReference dishesRef = FirebaseDatabase.getInstance().getReference().child("dish").child("dish1");
        dishesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Lấy dữ liệu từ DataSnapshot và gán cho các thành phần giao diện tương ứng
                for (DataSnapshot dishSnapshot : dataSnapshot.getChildren()) {
                    String imageURL = dataSnapshot.child("image").getValue(String.class);
                    String name = dataSnapshot.child("name").getValue(String.class);
                    String information = dataSnapshot.child("information").getValue(String.class);
                    int price = dataSnapshot.child("price").getValue(Integer.class);

                    // Gán dữ liệu cho các thành phần giao diện
                    Picasso.get().load(imageURL).into(imageFood);
                    dishName.setText(name);
                    dishInfo.setText(information);
                    textPrice.setText(String.valueOf(price));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Xử lý khi có lỗi xảy ra trong quá trình đọc dữ liệu từ Firebase Realtime Database
            }
        });
        btnSizeVua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textPrice.setText("55000");
                calculateSumPrice();
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