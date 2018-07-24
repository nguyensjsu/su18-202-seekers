package edu.sjsu.seekers.starbucks.model;

import javax.persistence.*;
import java.util.Date;



@Entity
public class PaymentCardDetails {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="Card_Key")
    private Integer cardKey;

    @Column(name="Address_Key")
    private Integer addressKey;

    @Column(name="User_Key")
    private Integer userKey;

    @Column(name="Is_Default_payment_Card")
    private String isDefaultpaymentcardKey;

    @Column(name="Card_Number")
    private String cardNumber;

    @Column(name="Expiration_Month")
    private String expirationMonth;

    @Column(name="Expiration_Year")
    private String expirationYear;

    @Column(name="CCV_Code")
    private String ccvCode;

    public Integer getCardKey() {
        return cardKey;
    }

    public void setCardKey(Integer cardKey) {
        this.cardKey = cardKey;
    }

    public Integer getAddressKey() {
        return addressKey;
    }

    public void setAddressKey(Integer addressKey) {
        this.addressKey = addressKey;
    }

    public Integer getUserKey() {
        return userKey;
    }

    public void setUserKey(Integer userKey) {
        this.userKey = userKey;
    }

    public String getIsDefaultpaymentcardKey() {
        return isDefaultpaymentcardKey;
    }

    public void setIsDefaultpaymentcardKey(String isDefaultpaymentcardKey) {
        this.isDefaultpaymentcardKey = isDefaultpaymentcardKey;
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
        return "PaymentCardDetails{" +
                "cardKey=" + cardKey +
                ", addressKey='" + addressKey + '\'' +
                ", userKey='" + userKey + '\'' +
                ", Is_Default_payment_Card='" + isDefaultpaymentcardKey + '\'' +
                ", Card_Number='" + cardNumber + '\'' +
                ", expirationMonth=" + expirationMonth +
                ", expirationYear=" + expirationYear +
                ", ccvcode=" + ccvCode +
                '}';
    }

}
