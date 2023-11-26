package com.study.fooddeliveryapplication.model;

public class SearchPage_PopularFoodItem {
    private String PopularFoodName,PopularFoodResName,PopularFoodImage;

    public SearchPage_PopularFoodItem(){}
    public SearchPage_PopularFoodItem(String popularFoodName, String popularFoodResName, String popularFoodImage) {
        PopularFoodName = popularFoodName;
        PopularFoodResName = popularFoodResName;
        PopularFoodImage = popularFoodImage;
    }

    public String getPopularFoodName() {
        return PopularFoodName;
    }

    public void setPopularFoodName(String popularFoodName) {
        PopularFoodName = popularFoodName;
    }

    public String getPopularFoodResName() {
        return PopularFoodResName;
    }

    public void setPopularFoodResName(String popularFoodResName) {
        PopularFoodResName = popularFoodResName;
    }

    public String getPopularFoodImage() {
        return PopularFoodImage;
    }

    public void setPopularFoodImage(String popularFoodImage) {
        PopularFoodImage = popularFoodImage;
    }
}
