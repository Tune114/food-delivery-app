package com.study.fooddeliveryapplication.model;

import android.graphics.drawable.Drawable;

public class FoodItem {
    private Drawable imageDrawable;
    private String name;

    public Drawable getImageDrawable() {
        return imageDrawable;
    }

    public void setImageDrawable(Drawable imageDrawable) {
        this.imageDrawable = imageDrawable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}