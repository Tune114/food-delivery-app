package com.study.fooddeliveryapplication.model;

import java.util.List;

public class OrderPaid {
    private List<Order> order;


    public List<Order> getModelOrder() {
        return order;
    }

    public void setModelOrder(List<Order> order) {
        this.order = order;
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