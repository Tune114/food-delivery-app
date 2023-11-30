package com.study.fooddeliveryapplication.model;

public class RestauItem {
    private String RestImage,RestName,RestDescrip;

    public RestauItem(){}

    public RestauItem(String restImage, String restName, String restDescrip) {
        RestImage = restImage;
        RestName = restName;
        RestDescrip = restDescrip;
    }

    public String getRestImage() {
        return RestImage;
    }

    public void setRestImage(String restImage) {
        RestImage = restImage;
    }

    public String getRestName() {
        return RestName;
    }

    public void setRestName(String restName) {
        RestName = restName;
    }

    public String getRestDescrip() {
        return RestDescrip;
    }

    public void setRestDescrip(String restDescrip) {
        RestDescrip = restDescrip;
    }
}

