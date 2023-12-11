package com.study.fooddeliveryapplication.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.annotation.NonNull;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.util.Pair;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Callback;
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
    private int fvalue;
    private TextView textLoveCount;
    private int loveCount = 17;
    private Button btnSizeNho;
    private Button btnSizeVua;
    private Button btnSizeLon;
    private TextView txtQuantity;
    DrawerLayout drawerLayout;
    ConstraintLayout constraintLayout;
    LinearLayout lnHome, lnCart, lnRestaurant, lnProfile;
    private TextView txtSumPrice;
    private Button btnPlus;
    private Button btnMinus;
    private int quantity = 0;
    private int price = 0;

    private ImageButton btnBack;
    private Button btnAddToCart;
    private RecyclerView mRecyclerView;
    private FoodListAdapter mFoodListAdapter;
    private List<FoodItem> mFoodList;
    private RecyclerView xrecyclerView;
    private ImageView fimage, show_more_btn;
    private TextView fname;
    private TextView fdescrip;
    private TextView fprice;
    private TextView fres;
    private boolean isLoveClicked = false;

    private List<IngredientItem> xingredientList;
    private IngredientListAdapter xadapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dish_details);
        FirebaseApp.initializeApp(this);
        List<Pair<String, String>> data = new ArrayList<>();
        data.add(new Pair<>("Đinh Công Minh\nMón này khá ngon", "minh"));
        data.add(new Pair<>("Đỗ Viết Thắng\nKhá nhiều so với mức ăn của tôi", "thang"));
        data.add(new Pair<>("Phạm Tấn Tú\nTạm ổn", "tu"));
        data.add(new Pair<>("Trần Đình Thọ\nSẽ mua lại lần sau", "tho"));
        data.add(new Pair<>("Mai Hồng Quang\nRất là ngon", "quang"));


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
        addFoodItem("Nem cuốn", getResources().getDrawable(R.drawable.nemcuon));
        addFoodItem("Bánh cuốn", getResources().getDrawable(R.drawable.banhcuon));
        addFoodItem("Bánh mì", getResources().getDrawable(R.drawable.banhmi));
        addFoodItem("Burger", getResources().getDrawable(R.drawable.burgers));
        addFoodItem("Bún đậu", getResources().getDrawable(R.drawable.bundau));

        btnLove = findViewById(R.id.btn_love);
        textLoveCount = findViewById(R.id.text_love_count);
        btnSizeNho = findViewById(R.id.btn_size_nho);
        btnSizeVua = findViewById(R.id.btn_size_vua);
        btnSizeLon = findViewById(R.id.btn_size_lon);
        txtQuantity = findViewById(R.id.txt_quantity);
        txtSumPrice = findViewById(R.id.text_sumprice);
        btnPlus = findViewById(R.id.btn_plus);
        btnMinus = findViewById(R.id.btn_minus);
        btnBack = findViewById(R.id.button_back);
        btnAddToCart=findViewById(R.id.add_to_cart);

        Intent intent= getIntent();
        String NameofFood=intent.getStringExtra("foodName");
        String NameofCate=intent.getStringExtra("categoryName");
        String NameofRes=intent.getStringExtra("restaurantName");
        fname=findViewById(R.id.tv_dish_name);
        fdescrip=findViewById(R.id.tv_dish_info);
        fimage=findViewById(R.id.image_food);
        fprice=findViewById(R.id.text_price);
        fres=findViewById(R.id.btn_location);
        fres.setText(NameofRes);

        xingredientList = new ArrayList<>();
        xrecyclerView = findViewById(R.id.ingredient_item);
        LinearLayoutManager olayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        xrecyclerView.setLayoutManager(olayoutManager);

        xrecyclerView.setHasFixedSize(true);
        xadapter = new IngredientListAdapter(this, xingredientList);
        xrecyclerView.setAdapter(xadapter);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("restaurants");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot restaurantSnapshot : dataSnapshot.getChildren()) {
                    String restName = restaurantSnapshot.child("RestName").getValue(String.class);

                    // So sánh tên nhà hàng
                    if (restName.equals(NameofRes)) {
                        for (DataSnapshot categorySnapshot : restaurantSnapshot.child("categories").getChildren()) {
                            String categoryName = categorySnapshot.child("category_name").getValue(String.class);

                            // So sánh tên danh mục
                            if (categoryName.equals(NameofCate)) {
                                for (DataSnapshot foodSnapshot : categorySnapshot.child("foods").getChildren()) {
                                    String foodName = foodSnapshot.child("name").getValue(String.class);

                                    // So sánh tên món ăn
                                    if (foodName.equals(NameofFood)) {
                                        for (DataSnapshot ingredientSnapshot : foodSnapshot.child("ingredient").getChildren()) {
                                            String name = ingredientSnapshot.child("ingrename").getValue(String.class);
                                            String image = ingredientSnapshot.child("ingreimage").getValue(String.class);
                                            IngredientItem item = new IngredientItem(name, image);
                                            xingredientList.add(item);
                                        }
                                        xadapter.notifyDataSetChanged();
                                    }
                                }
                            }
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Xử lý lỗi khi không thể đọc dữ liệu từ Firebase
                Toast.makeText(FoodDetailsActivity.this, "Failed to read data", Toast.LENGTH_SHORT).show();
            }
        });






        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference("restaurants");
        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot restaurantSnapshot : dataSnapshot.getChildren()) {
                    String restName = restaurantSnapshot.child("RestName").getValue(String.class);

                    // So sánh tên nhà hàng
                    if (restName.equals(NameofRes)) {
                        for (DataSnapshot categorySnapshot : restaurantSnapshot.child("categories").getChildren()) {
                            String categoryName = categorySnapshot.child("category_name").getValue(String.class);

                            // So sánh tên danh mục
                            if (categoryName.equals(NameofCate)) {
                                for (DataSnapshot foodSnapshot : categorySnapshot.child("foods").getChildren()) {
                                    String foodName = foodSnapshot.child("name").getValue(String.class);

                                    // So sánh tên món ăn
                                    if (foodName.equals(NameofFood)) {
                                        String foodDescription = foodSnapshot.child("description").getValue(String.class);
                                        String foodImage = foodSnapshot.child("image").getValue(String.class);
                                        String foodPrice = foodSnapshot.child("price").getValue(String.class);

                                        // Gán dữ liệu vào các giá trị
                                        fname.setText(foodName);
                                        fdescrip.setText(foodDescription);
                                        Picasso.get()
                                                .load(foodImage)
                                                .into(fimage, new Callback() {
                                                    @Override
                                                    public void onSuccess() {
                                                        // Xử lý khi tải hình ảnh thành công (nếu cần)
                                                    }

                                                    @Override
                                                    public void onError(Exception e) {
                                                        // Xử lý khi có lỗi xảy ra trong quá trình tải hình ảnh (nếu cần)
                                                    }
                                                });
                                        fprice.setText(foodPrice);
                                        fvalue = Integer.parseInt(fprice.getText().toString());
                                        fres.setText(restName);
                                    }
                                }
                            }
                        }
                    }
                }
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Xử lý khi có lỗi xảy ra trong quá trình truy vấn dữ liệu
            }
        });
        btnSizeNho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newPrice = fvalue - 5; // Lấy giá trị của fvalue và trừ đi 5
                fprice.setText(String.valueOf(newPrice));
                calculateSumPrice();
            }
        });
        btnSizeVua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fprice.setText(String.valueOf(fvalue));
                calculateSumPrice();
            }
        });
        btnSizeLon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newPrice = fvalue + 5; // Lấy giá trị của fvalue và cộng thêm 5
                fprice.setText(String.valueOf(newPrice));
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
    //navbar
        show_more_btn = findViewById(R.id.show_more_btn);
        drawerLayout = findViewById(R.id.drawLayout);
        lnHome = findViewById(R.id.home);
        lnCart = findViewById(R.id.cart);
        lnProfile = findViewById(R.id.profile);
        lnRestaurant = findViewById(R.id.restaurant);
        show_more_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomePageActivity.openDrawer(drawerLayout);
            }
        });
        lnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomePageActivity.redirectActitvity(FoodDetailsActivity.this, HomePageActivity.class);
            }
        });
        lnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomePageActivity.redirectActitvity(FoodDetailsActivity.this, AddCartActivity.class);

            }
        });
        lnRestaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomePageActivity.redirectActitvity(FoodDetailsActivity.this, RestaurantList.class);
            }
        });
        lnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomePageActivity.redirectActitvity(FoodDetailsActivity.this, UserProflie.class);
            }
        });
        constraintLayout = findViewById(R.id.dontknow);
        constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("MyApp", "is oke");
            }
        });
    }
    @Override
    protected void onPause() {
        super.onPause();
        HomePageActivity.closeDrawer(drawerLayout);
    }
    public void onLoveButtonClick(View view) {
        if (!isLoveClicked) { // Kiểm tra trạng thái của nút Love
            loveCount++;
            textLoveCount.setText(String.valueOf(loveCount));

            isLoveClicked = true; // Đánh dấu nút Love đã được bấm
        }
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
        price = Integer.parseInt(fprice.getText().toString());
        int sumPrice = price * quantity;
        txtSumPrice.setText(String.valueOf(sumPrice));
    }
}