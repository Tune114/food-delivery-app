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


        //spinner
        String[] options = {"Option 1", "Option 2", "Option 3"};
        CustomSpinnerAdapter adapter = new CustomSpinnerAdapter(this, android.R.layout.simple_spinner_item, options);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner = findViewById(R.id.sp_place_deliver);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedOption = (String) parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), "You Picked: " + selectedOption, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Xử lý khi không có tùy chọn nào được chọn
            }
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