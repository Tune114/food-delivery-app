package com.study.fooddeliveryapplication.model;

public class CartItem {
    private String foodName;
    private String foodPrice;
    private String foodQuantity;
    private String fImage;
    private String nameofRes;
    private String pNumber;

    public CartItem() {
        // Constructor mặc định (cần thiết cho Firebase)
    }

    public CartItem(String foodName, String foodPrice, String foodQuantity, String fImage, String nameofRes, String pNumber) {
        this.foodName = foodName;
        this.foodPrice = foodPrice;
        this.foodQuantity = foodQuantity;
        this.fImage = fImage;
        this.nameofRes = nameofRes;
        this.pNumber = pNumber;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(String foodPrice) {
        this.foodPrice = foodPrice;
    }

    public String getFoodQuantity() {
        return foodQuantity;
    }

    public void setFoodQuantity(String foodQuantity) {
        this.foodQuantity = foodQuantity;
    }

    public String getFImage() {
        return fImage;
    }

    public void setFImage(String fImage) {
        this.fImage = fImage;
    }

    public String getNameofRes() {
        return nameofRes;
    }

    public void setNameofRes(String nameofRes) {
        this.nameofRes = nameofRes;
    }

    public String getPNumber() {
        return pNumber;
    }

    public void setPNumber(String pNumber) {
        this.pNumber = pNumber;
    }
}
