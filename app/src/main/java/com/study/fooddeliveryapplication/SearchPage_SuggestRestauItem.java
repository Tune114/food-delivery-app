package com.study.fooddeliveryapplication;

public class SearchPage_SuggestRestauItem {
    private int imageResource;
    private String text;
    private String num;

    public SearchPage_SuggestRestauItem(int imageResource, String text, String num) {
        this.imageResource = imageResource;
        this.text = text;
        this.num = num;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String getText() {
        return text;
    }

    public String getNum() {
        return num;
    }


}
