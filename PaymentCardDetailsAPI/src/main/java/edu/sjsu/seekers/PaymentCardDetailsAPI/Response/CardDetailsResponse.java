package edu.sjsu.seekers.PaymentCardDetailsAPI.Response;

import edu.sjsu.seekers.starbucks.model.PaymentCardDetails;

import java.util.List;

public class CardDetailsResponse extends GenericResponse {


    public List<PaymentCardDetails> getCardDetails() {
        return cardDetails;
    }

    public void setCardDetails(List<PaymentCardDetails> cardDetails) {
        this.cardDetails = cardDetails;
    }

    private List<PaymentCardDetails> cardDetails;

    private static final long serialVersionUID = 1L;

    private Integer Address;
    private Integer UserId;
    private String cardNumber;
    private String expirationMonth;
    private String expirationYear;
    private String ccvCode;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getAddress() {
        return Address;
    }

    public void setAddress(Integer address) {
        Address = address;
    }

    public Integer getUserId() {
        return UserId;
    }

    public void setUserId(Integer userId) {
        UserId = userId;
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



    public CardDetailsResponse() {
        super();
    }

    public CardDetailsResponse(Integer address, Integer userId, String cardNumber, String expirationMonth, String expirationYear, String ccvCode) {

        super();
        this.Address = address;
        this.UserId = userId;
        this.cardNumber = cardNumber;
        this.expirationMonth = expirationMonth;
        this.expirationYear = expirationYear;
        this.ccvCode = ccvCode;
    }

    @Override
    public String toString() {
        String toReturn="";
        for(int i = 0; i < this.cardDetails.size(); i++)
        {
            toReturn=toReturn + cardDetails.get(i).toString();
        }
        return super.toString()+toReturn;

    }


}
