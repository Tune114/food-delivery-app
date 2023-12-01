package com.study.fooddeliveryapplication.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.util.Pair;
import androidx.drawerlayout.widget.DrawerLayout;

import com.study.fooddeliveryapplication.R;
import com.study.fooddeliveryapplication.adapter.CommentAdapter;

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
    DrawerLayout drawerLayout;
    ConstraintLayout constraintLayout;
    LinearLayout lnHome, lnCart, lnRestaurant, lnProfile;
    private Button btnPlus;
    private Button btnMinus;
    private int quantity = 0;
    private int price = 0;
    private ImageButton btnBack,show_more_btn;
    private Button btnAddToCart;

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
                textPrice.setText("25000");
                calculateSumPrice();
            }
        });

        btnSizeVua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textPrice.setText("30000");
                calculateSumPrice();
            }
        });

        btnSizeLon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textPrice.setText("35000");
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
                HomePageActivity.redirectActitvity(FoodDetailsActivity.this, RestaurantList.class);
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

    private void calculateSumPrice() {
        price = Integer.parseInt(textPrice.getText().toString());
        int sumPrice = price * quantity;
        txtSumPrice.setText(String.valueOf(sumPrice));
    }
}