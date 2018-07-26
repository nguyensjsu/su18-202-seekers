package edu.sjsu.seekers.starbucks.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Optional;


@Entity
public class PaymentCardDetails {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="Card_Key")
    private Integer cardKey;

    @ManyToOne
    @JoinColumn(name = "Address_Key", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Address addressKey;

    @ManyToOne
    @JoinColumn(name = "User_Key", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private User userKey;

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

    @Column(name="Is_Active_Card")
    private String isActiverCard;

    public Integer getCardKey() {
        return cardKey;
    }

    public void setCardKey(Integer cardKey) {
        this.cardKey = cardKey;
    }

    public Address getAddressKey() {
        return addressKey;
    }

    public void setAddressKey(Address addressKey) {
        this.addressKey = addressKey;
    }

    public User getUserKey() {
        return userKey;
    }

    public void setUserKey(User userKey) {
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

    public String getIsActiverCard() {
        return isActiverCard;
    }

    public void setIsActiverCard(String isActiverCard) {
        this.isActiverCard = isActiverCard;
    }

    @Override
    public String toString() {
        return "PaymentCardDetails{" +
                "cardKey=" + cardKey +
                ", addressKey=" + addressKey +
                ", userKey=" + userKey +
                ", isDefaultpaymentcardKey='" + isDefaultpaymentcardKey + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", expirationMonth='" + expirationMonth + '\'' +
                ", expirationYear='" + expirationYear + '\'' +
                ", ccvCode='" + ccvCode + '\'' +
                ", isActiveCard='" + isActiverCard + '\'' +
                '}';
    }
}