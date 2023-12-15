package com.study.fooddeliveryapplication.ui;


import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.FirebaseDatabase;
import com.study.fooddeliveryapplication.R;
import com.study.fooddeliveryapplication.adapter.RestauItemAdapter;
import com.study.fooddeliveryapplication.adapter.CustomSpinnerAdapter;
import com.study.fooddeliveryapplication.adapter.itemAdapter;
import com.study.fooddeliveryapplication.model.RestauItem;

public class HomePageActivity extends AppCompatActivity {
    TextView et_search;
    ImageView cart, iv_menu;
    itemAdapter adapter1;
    DrawerLayout drawerLayout;
    LinearLayout lnHome, lnCart, lnRestaurant, lnProfile;
    ConstraintLayout constraintLayout;
    TextView tv_more_res;
    RestauItemAdapter adapter2;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        et_search = (TextView) findViewById(R.id.et_hp_seachBar);
        et_search.setOnClickListener(view -> {
            Intent intent = new Intent(HomePageActivity.this, SearchPageActivity.class);
            startActivity(intent);
        });

        tv_more_res = (TextView) findViewById(R.id.tv_see_all_res);
        tv_more_res.setOnClickListener(view -> {
            Intent intent = new Intent(HomePageActivity.this, RestaurantList.class);
            startActivity(intent);
        });

        cart = (ImageView)findViewById(R.id.iv_bag);
        cart.setOnClickListener(view -> {
            Intent intent = new Intent(HomePageActivity.this, AddCartActivity.class);
            startActivity(intent);
        });

        //Custom hello text
        TextView tvhello = findViewById(R.id.tv_hello_text);
        String fullText = "Hey Quang, Good Morning!";
        SpannableString spannableString = new SpannableString(fullText);
        int startIndex = fullText.indexOf(",");
        int endIndex = fullText.length();
        spannableString.setSpan(new StyleSpan(Typeface.BOLD), startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvhello.setText(spannableString);


        //Custom Restau Items
        RecyclerView recyclerView2 = findViewById(R.id.recyclerView2);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView2.setLayoutManager(layoutManager2);
        FirebaseRecyclerOptions<RestauItem> options2 =
                new FirebaseRecyclerOptions.Builder<RestauItem>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("/restaurants"),RestauItem.class)
                        .build();
        adapter2 = new RestauItemAdapter(options2);
        recyclerView2.setAdapter(adapter2);
        // bottomNavigationView
        drawerLayout = findViewById(R.id.drawLayout);
        iv_menu = findViewById(R.id.iv_menu);
        lnHome = findViewById(R.id.home);
        lnCart = findViewById(R.id.cart);
        lnProfile = findViewById(R.id.profile);
        lnRestaurant = findViewById(R.id.restaurant);
        constraintLayout = findViewById(R.id.dontknow);
        constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("MyApp", "is oke");
            }
        });
        iv_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDrawer(drawerLayout);
            }
        });
        lnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recreate();
            }
        });
        lnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActitvity(HomePageActivity.this, AddCartActivity.class);
            }
        });
        lnRestaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActitvity(HomePageActivity.this, RestaurantList.class);
            }
        });
        lnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActitvity(HomePageActivity.this, UserProflie.class);
            }
        });
    }
    public static void openDrawer(DrawerLayout drawerLayout){
        drawerLayout.openDrawer(GravityCompat.START);
    }
    public static void closeDrawer(DrawerLayout drawerLayout){
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }
    public static void redirectActitvity(Activity activity, Class secondActivity){
        Intent intent = new Intent(activity, secondActivity);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
        activity.finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        closeDrawer(drawerLayout);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter2.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter2.stopListening();
    }
}