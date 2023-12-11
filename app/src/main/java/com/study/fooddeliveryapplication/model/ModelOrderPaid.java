package com.study.fooddeliveryapplication.model;

import java.util.List;

public class ModelOrderPaid {
    private List<ModelOrder> modelOrder;


    public List<ModelOrder> getModelOrder() {
        return modelOrder;
    }

    public void setModelOrder(List<ModelOrder> modelOrder) {
        this.modelOrder = modelOrder;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    private String userName;
    private String cardNumber;
    private String address;


}