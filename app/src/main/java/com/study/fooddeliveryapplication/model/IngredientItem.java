package com.study.fooddeliveryapplication.model;

// IngredientItem.java
public class IngredientItem {
    private String name;
    private String image;

    public IngredientItem() {
        // Empty constructor required for Firebase
    }

    public IngredientItem(String name, String image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }
}