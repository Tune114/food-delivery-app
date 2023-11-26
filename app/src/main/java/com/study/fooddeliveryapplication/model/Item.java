package com.study.fooddeliveryapplication.model;

public class Item {
    private String FoodImage;
    private String FoodName;

    public Item(){}

    public Item(String foodImage, String foodName) {
        FoodImage = foodImage;
        FoodName = foodName;
    }

    public String getFoodImage() {
        return FoodImage;
    }

    public void setFoodImage(String foodImage) {
        FoodImage = foodImage;
    }

    public String getFoodName() {
        return FoodName;
    }

    public void setFoodName(String foodName) {
        FoodName = foodName;
    }
}
