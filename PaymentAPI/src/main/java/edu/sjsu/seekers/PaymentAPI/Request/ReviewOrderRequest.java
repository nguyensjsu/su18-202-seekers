package edu.sjsu.seekers.PaymentAPI.Request;

import java.io.Serializable;

public class ReviewOrderRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userName;

    private String paymentType;

    private String cardID;

    private String cvv;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getCardID() {
        return cardID;
    }

    public void setCardID(String cardID) {
        this.cardID = cardID;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    @Override
    public String toString() {
        return "ReviewOrderRequest{" +
                "userName='" + userName + '\'' +
                ", paymentType='" + paymentType + '\'' +
                ", cardID=" + cardID +
                ", cvv=" + cvv +
                '}';
    }
}
