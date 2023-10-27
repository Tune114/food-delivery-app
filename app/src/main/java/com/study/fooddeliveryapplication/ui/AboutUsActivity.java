package com.study.fooddeliveryapplication.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.study.fooddeliveryapplication.R;
import com.study.fooddeliveryapplication.adapter.CustomListAdapter;
import com.study.fooddeliveryapplication.model.Student;

import java.util.ArrayList;

public class AboutUsActivity extends AppCompatActivity {

    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        ArrayList<Student> studentList = new ArrayList<>();
        studentList.add(new Student(R.drawable.thang, "Đỗ Viết Thắng(Nhóm Trưởng)", "21115053120347"));
        studentList.add(new Student(R.drawable.tu, "Phạm Tấn Tú", "21115053120357"));
        studentList.add(new Student(R.drawable.tho, "Trần Đình Thọ", "21115053120349"));
        studentList.add(new Student(R.drawable.quang, "Mai Hồng Quang", "21115053120342"));
        studentList.add(new Student(R.drawable.minh, "Đinh Công Minh", "21115053120334"));

        CustomListAdapter adapter1 = new CustomListAdapter(this, R.layout.list_item, studentList);
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter1);

        imageView = (ImageView) findViewById(R.id.left_btn);
        Intent intent = new Intent(this, LoginActivity.class);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(intent);
            }
        });
    }
}