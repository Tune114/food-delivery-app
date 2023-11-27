package com.study.fooddeliveryapplication.adapter;

import com.study.fooddeliveryapplication.model.Food;

import java.util.List;

public interface UpdateRestInfor {
    void callBack(List<Food> foods);
    void updateCategoryLabel(int size,String categoryName);
}
