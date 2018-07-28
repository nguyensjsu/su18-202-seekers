package edu.sjsu.seekers.PaymentAPI.Response;

public class ConfirmOrderResponse extends GenericResponse {
    private Integer paymentId;

    public ConfirmOrderResponse(String responseMessage, String statusCode, Integer paymentId) {
        super(responseMessage, statusCode);
        this.paymentId = paymentId;
    }

    public ConfirmOrderResponse()
    {

    }

    public Integer getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }

    @Override
    public String toString() {
        return "ConfirmOrderResponse{" +
                "paymentId=" + paymentId +
                '}';
    }
}
