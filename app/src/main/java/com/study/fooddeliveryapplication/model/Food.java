package com.study.fooddeliveryapplication.model;

public class Food {
    private String name;
    private String description;
    private String price;

    private int image;

    public Food(String name, String description, String price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Food(String name, String description, String price, int image) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.image=image;
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

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
