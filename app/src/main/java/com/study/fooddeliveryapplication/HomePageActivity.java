package com.study.fooddeliveryapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.widget.TextView;

public class HomePageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Custom hello text
        TextView tvhello = findViewById(R.id.tv_hello_text);
        String fullText = "Hey Quang, Good Morning!";
        SpannableString spannableString = new SpannableString(fullText);
        int startIndex = fullText.indexOf(",");
        int endIndex = fullText.length();
        spannableString.setSpan(new StyleSpan(Typeface.BOLD), startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvhello.setText(spannableString);

    }
}