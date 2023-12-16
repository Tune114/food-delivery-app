package com.study.fooddeliveryapplication.model;

public class DataItem {
    private String imageResource;
    private String name;
    private String comment;

    public DataItem(String imageResource, String name, String comment) {
        this.imageResource = imageResource;
        this.name = name;
        this.comment = comment;
    }

    public String getImageResource() {
        return imageResource;
    }
    public String getName() {
        return name;
    }
    public String getComment() {
        return comment;
    }

}