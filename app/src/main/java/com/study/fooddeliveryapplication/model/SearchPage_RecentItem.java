package com.study.fooddeliveryapplication.model;

public class SearchPage_RecentItem {
    private String RecentItemName;
    public SearchPage_RecentItem(){};
    public SearchPage_RecentItem(String recentItemName) {
        RecentItemName = recentItemName;
    }

    public String getRecentItemName() {
        return RecentItemName;
    }

    public void setRecentItemName(String recentItemName) {
        RecentItemName = recentItemName;
    }
}
