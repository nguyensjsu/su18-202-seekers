package edu.sjsu.seekers.PaymentCardDetailsAPI.Request;

import java.io.Serializable;

public class CardDetailsRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private String addressLine1;
    private String addressLine2;
    private String state;
    private String city;
    private String country;
    private Integer zipCode;
    private String userName;
    private String cardNumber;
    private String expirationMonth;
    private String expirationYear;
    private String ccvCode;

    public CardDetailsRequest()
    {

    }

    public CardDetailsRequest(String userName,String cardNumber,String expirationMonth,String expirationYear,String ccvCode)
    {

        super();

        this.userName=userName;
        this.cardNumber=cardNumber;
        this.expirationMonth=expirationMonth;
        this.expirationYear=expirationYear;
        this.ccvCode=ccvCode;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        return "CardDetailsRequest{" +
                ", userName='" + userName + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", expirationMonth='" + expirationMonth + '\'' +
                ", expirationYear='" + expirationYear + '\'' +
                ", ccvCode='" + ccvCode + '\'' +
                '}';
    }




}

