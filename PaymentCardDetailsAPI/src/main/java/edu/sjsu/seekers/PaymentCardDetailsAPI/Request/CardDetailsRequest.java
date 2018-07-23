package edu.sjsu.seekers.PaymentCardDetailsAPI.Request;

import java.io.Serializable;

public class CardDetailsRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer addressLine1;
    private Integer addressLine2;
    private String cardNumber;
    private String expirationMonth;
    private String expirationYear;
    private String ccvCode;

    public CardDetailsRequest()
    {

    }

    public CardDetailsRequest(Integer addressLine1,Integer addressLine2,String cardNumber,String expirationMonth,String expirationYear,String ccvCode)
    {

        super();
        this.addressLine1=addressLine1;
        this.addressLine2=addressLine2;
        this.cardNumber=cardNumber;
        this.expirationMonth=expirationMonth;
        this.expirationYear=expirationYear;
        this.ccvCode=ccvCode;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(Integer addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public Integer getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(Integer addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpirationMonth() {
        return expirationMonth;
    }

    public void setExpirationMonth(String expirationMonth) {
        this.expirationMonth = expirationMonth;
    }

    public String getExpirationYear() {
        return expirationYear;
    }

    public void setExpirationYear(String expirationYear) {
        this.expirationYear = expirationYear;
    }

    public String getCcvCode() {
        return ccvCode;
    }

    public void setCcvCode(String ccvCode) {
        this.ccvCode = ccvCode;
    }

    @Override
    public String toString() {
        return "CardDetailsRequest{" +
                "addressLine1=" + addressLine1 +
                ", addressLine2=" + addressLine2 +
                ", cardNumber='" + cardNumber + '\'' +
                ", expirationMonth='" + expirationMonth + '\'' +
                ", expirationYear='" + expirationYear + '\'' +
                ", ccvCode='" + ccvCode + '\'' +
                '}';
    }
}

