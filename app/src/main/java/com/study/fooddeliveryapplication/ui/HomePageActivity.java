package com.study.fooddeliveryapplication.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.study.fooddeliveryapplication.R;
import com.study.fooddeliveryapplication.adapter.RestauItemAdapter;
import com.study.fooddeliveryapplication.adapter.CustomSpinnerAdapter;
import com.study.fooddeliveryapplication.adapter.itemAdapter;
import com.study.fooddeliveryapplication.model.Item;
import com.study.fooddeliveryapplication.model.RestauItem;

import java.util.ArrayList;
import java.util.List;

public class HomePageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

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

        //Custom Items
        RecyclerView recyclerView1 = findViewById(R.id.recyclerView1);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView1.setLayoutManager(layoutManager1);
        List<Item> itemList1 = new ArrayList<>();
        itemList1.add(new Item(R.drawable.ic_search_foreground, "Text 1"));
        itemList1.add(new Item(R.drawable.pho, "Text 2"));
        itemList1.add(new Item(R.drawable.banhmi, "Text 3"));
        itemList1.add(new Item(R.drawable.buncha, "Text 4"));
        itemList1.add(new Item(R.drawable.ic_search_foreground, "Text 5"));
        itemAdapter adapter1 = new itemAdapter(itemList1);
        recyclerView1.setAdapter(adapter1);

        //Custom Restau Items
        RecyclerView recyclerView2 = findViewById(R.id.recyclerView2);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView2.setLayoutManager(layoutManager2);
        List<RestauItem> itemList2 = new ArrayList<>();
        itemList2.add(new RestauItem(R.drawable.pho, "Text 1a", "Text 1b"));
        itemList2.add(new RestauItem(R.drawable.banhmi, "Text 2a", "Text 2b"));
        itemList2.add(new RestauItem(R.drawable.buncha, "Text 3a", "Text 3b"));
        RestauItemAdapter adapter2 = new RestauItemAdapter(itemList2);
        recyclerView2.setAdapter(adapter2);

    }
}