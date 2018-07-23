package edu.sjsu.seekers.PaymentAPI.Response;

import java.util.List;

public class PaymentOptionsResponse extends GenericResponse {
    private List<String> paymentCards;
    private double rewardPoints;

    public PaymentOptionsResponse(String responseMessage, String statusCode, List<String> paymentCards, double rewardPoints) {
        super(responseMessage, statusCode);
        this.paymentCards = paymentCards;
        this.rewardPoints = rewardPoints;
    }

    public PaymentOptionsResponse(){

    }

    public List<String> getPaymentCards() {
        return paymentCards;
    }

    public void setPaymentCards(List<String> paymentCards) {
        this.paymentCards = paymentCards;
    }

    public double getRewardPoints() {
        return rewardPoints;
    }

    public void setRewardPoints(double rewardPoints) {
        this.rewardPoints = rewardPoints;
    }

    @Override
    public String toString() {
        return "PaymentResponse{" +
                "paymentCards=" + paymentCards +
                ", rewardPoints=" + rewardPoints +
                '}';
    }

}
