package com.study.fooddeliveryapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.study.fooddeliveryapplication.R;
import com.study.fooddeliveryapplication.model.Student;

import java.util.ArrayList;

public class CustomListAdapter extends ArrayAdapter<Student> {
    private Context mContext;
    private int mResource;

    public CustomListAdapter(Context context, int resource, ArrayList<Student> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int imageResource = getItem(position).getImageResource();
        String fullName = getItem(position).getFullName();
        String studentID = getItem(position).getStudentID();

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        ImageView imageView = convertView.findViewById(R.id.imageView);
        TextView tvFullName = convertView.findViewById(R.id.fullName);
        TextView tvStudentID = convertView.findViewById(R.id.studentID);

        imageView.setImageResource(imageResource);
        tvFullName.setText(fullName);
        tvStudentID.setText(studentID);

        return convertView;
    }
}
