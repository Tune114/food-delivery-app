package com.study.fooddeliveryapplication.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.Pair;

import com.study.fooddeliveryapplication.R;

import java.util.List;

public class CommentAdapter extends ArrayAdapter<Pair<String, String>> {
    private Context context;
    private List<Pair<String, String>> data;

    public CommentAdapter(Context context, List<Pair<String, String>> data) {
        super(context, 0, data);
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.item_list_comment, parent, false);
        }

        ImageView imageView = view.findViewById(R.id.imageView);
        TextView tvName = view.findViewById(R.id.tvName);
        TextView tvNumber = view.findViewById(R.id.tvcomment);
        LinearLayout layout = view.findViewById(R.id.layoutListItem);

        Pair<String, String> item = data.get(position);
        String name = item.first.split("\n")[0].trim();
        String number = item.first.split("\n")[1].trim();
        String imageName = "drawable/" + item.second.toLowerCase();

        int imageResource = context.getResources().getIdentifier(
                imageName, null, context.getPackageName()
        );
        imageView.setImageResource(imageResource);

        tvName.setText(name);
        tvNumber.setText(number);

        // Set background color
        layout.setBackgroundColor(Color.parseColor(getRowColor(position)));

        return view;
    }

    private String getRowColor(int position) {
        return (position % 2 == 0) ? "#F0F0F0" : "#FFFFFF";
    }
}