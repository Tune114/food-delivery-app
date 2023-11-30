package com.study.fooddeliveryapplication.model;

public class SearchPage_SuggestRestauItem {
    private String SuggestRestItemName,SuggestRestItemImage,SuggestRestItemStar;

    public SearchPage_SuggestRestauItem(){}

    public SearchPage_SuggestRestauItem(String suggestRestItemName, String suggestRestItemImage, String suggestRestItemStar) {
        SuggestRestItemName = suggestRestItemName;
        SuggestRestItemImage = suggestRestItemImage;
        SuggestRestItemStar = suggestRestItemStar;
    }

    public String getSuggestRestItemName() {
        return SuggestRestItemName;
    }

    public void setSuggestRestItemName(String suggestRestItemName) {
        SuggestRestItemName = suggestRestItemName;
    }

    public String getSuggestRestItemImage() {
        return SuggestRestItemImage;
    }

    public void setSuggestRestItemImage(String suggestRestItemImage) {
        SuggestRestItemImage = suggestRestItemImage;
    }

    public String getSuggestRestItemStar() {
        return SuggestRestItemStar;
    }

    public void setSuggestRestItemStar(String suggestRestItemStar) {
        SuggestRestItemStar = suggestRestItemStar;
    }
}
