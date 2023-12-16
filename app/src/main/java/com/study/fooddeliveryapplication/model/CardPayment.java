package com.study.fooddeliveryapplication.model;

import java.util.HashMap;
import java.util.Map;

public class CardPayment {
    private String cardName;
    private  String cardIcon;
    private String expireDate;
    private String cvc;
    private String cardNumber;
    private String cardHolderName;
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("cardName", cardName);
        result.put("cardHolderName", cardHolderName);
        result.put("expireDate", expireDate);
        result.put("cvc", cvc);
        result.put("cardNumber", cardNumber);
        result.put("cardIcon", cardIcon);

        return result;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardIcon() {
        return cardIcon;
    }

    public void setCardIcon(String cardIcon) {
        this.cardIcon = cardIcon;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public String getCvc() {
        return cvc;
    }

    public void setCvc(String cvc) {
        this.cvc = cvc;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
}
