package com.study.fooddeliveryapplication.model;

public class CartItem {
    private String foodName;
    private String foodImg;
    private String restName;
    private String price;
    private String quantity;
    private String userPhone;

    public CartItem() {
        // Constructor mặc định (cần thiết cho Firebase)
    }

    public CartItem(String foodName, String foodImg, String restName, String price, String quantity, String userPhone) {
        this.foodName = foodName;
        this.foodImg = foodImg;
        this.restName = restName;
        this.price = price;
        this.quantity = quantity;
        this.userPhone = userPhone;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodImg() {
        return foodImg;
    }

    public void setFoodImg(String foodImg) {
        this.foodImg = foodImg;
    }

    public String getRestName() {
        return restName;
    }

    public void setRestName(String restName) {
        this.restName = restName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }
}