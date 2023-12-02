package com.study.fooddeliveryapplication.model;

import java.util.List;

public class Category {
    private String name;
    private List<Food> foods;
    private boolean selected;

    public Category() {
    }

    public Category(String name, List<Food> foods) {
        this.name = name;
        this.foods = foods;
    }
    public Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Food> getFoods() {
        return foods;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
