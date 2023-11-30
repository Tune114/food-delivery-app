package com.study.fooddeliveryapplication;

public class Item {
    private int imageResource;
    private String text;
    private boolean isSelected;

    public Item(int imageResource, String text) {
        this.imageResource = imageResource;
        this.text = text;
        this.isSelected = false;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String getText() {
        return text;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
