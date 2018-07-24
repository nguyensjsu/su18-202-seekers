package edu.sjsu.seekers.PaymentCardDetailsAPI.Response;

public class SaveCardsResponse extends GenericResponse {

    private String cardNumber;
    private String expirationMonth;
    private String expirationYear;
    private String ccvCode;

    public SaveCardsResponse()
    {


    }

    public SaveCardsResponse(String cardNumber,String expirationMonth,String expirationYear,String ccvCode)
    {
        super();
        this.cardNumber=cardNumber;
        this.expirationMonth=expirationMonth;
        this.expirationYear=expirationYear;
        this.ccvCode=ccvCode;
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
        return "SaveCardsResponse{" +
                "cardNumber='" + cardNumber + '\'' +
                ", expirationMonth='" + expirationMonth + '\'' +
                ", expirationYear='" + expirationYear + '\'' +
                ", ccvCode='" + ccvCode + '\'' +
                '}';
    }

}
