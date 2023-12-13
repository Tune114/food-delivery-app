package com.study.fooddeliveryapplication.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModelOrder  {
    private List<ModelCart> listFood;
    private  String payable;
    private  String cardNumber;
    private String userPhone;
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public ModelOrder(){

    }

    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("listFood", listFood);
        result.put("payable", payable);
        result.put("cardNumber", cardNumber);
        result.put("userPhone", userPhone);
        result.put("address", address);

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