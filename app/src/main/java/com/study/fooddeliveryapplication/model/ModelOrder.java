package com.study.fooddeliveryapplication.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModelOrder {
    private List<ModelCart> listFood;
    private  String payable;
    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("listFood", listFood);
        result.put("payable", payable);
        return result;
    }
    public List<ModelCart> getListFood() {
        return listFood;
    }

    public void setListFood(List<ModelCart> listFood) {
        this.listFood = listFood;
    }

    public String getPayable() {
        return payable;
    }

    public void setPayable(String payable) {
        this.payable = payable;
    }
}
