package com.study.fooddeliveryapplication.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.study.fooddeliveryapplication.R;
import com.study.fooddeliveryapplication.adapter.CustomListAdapter;
import com.study.fooddeliveryapplication.model.Student;

import java.util.ArrayList;

public class AboutUsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        ArrayList<Student> studentList = new ArrayList<>();
        studentList.add(new Student(R.drawable.pic, "Đỗ Viết Thắng(Nhóm Trưởng)", "21115053120347"));
        studentList.add(new Student(R.drawable.pic, "Phạm Tấn Tú", "21115053120357"));
        studentList.add(new Student(R.drawable.pic, "Trần Đình Thọ", "21115053120349"));
        studentList.add(new Student(R.drawable.pic, "Mai Hồng Quang", "21115053120342"));
        studentList.add(new Student(R.drawable.pic, "Đinh Công Minh", "21115053120334"));

        CustomListAdapter adapter1 = new CustomListAdapter(this, R.layout.list_item, studentList);
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter1);
    }
}