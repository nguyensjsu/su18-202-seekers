package edu.sjsu.seekers.PaymentCardDetailsAPI.Request;

import java.io.Serializable;

public class DeleteCardRequest implements Serializable {

    private String userName;
    private String cardNumber;

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


    @Override
    public String toString() {
        return "DeleteCardRequest{" +
                "userName='" + userName + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                '}';
    }
}
