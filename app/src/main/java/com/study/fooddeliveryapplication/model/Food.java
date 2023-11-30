package com.study.fooddeliveryapplication.model;

import java.util.HashMap;
import java.util.Map;

public class Food {
    private String name;
    private String description;
    private String price;
    private String image;
    private String restaurantName;

    public Food() {
    }

    public Food(String name, String description, String price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }


    public Food(String name, String description, String price, String image, String restaurantName) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.image = image;
        this.restaurantName=restaurantName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("foodName", this.name);
        result.put("foodImg", this.image);
        result.put("price", this.price);
        result.put("quantity", "1");
        result.put("restName", restaurantName);

        return result;
    }
}
