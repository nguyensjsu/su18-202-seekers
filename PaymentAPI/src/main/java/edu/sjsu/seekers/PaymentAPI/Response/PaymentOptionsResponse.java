package edu.sjsu.seekers.PaymentAPI.Response;

import java.util.Map;

public class PaymentOptionsResponse extends GenericResponse {
    private Map<Integer,String> paymentCards;
    private Double rewardPoints;

    public PaymentOptionsResponse(String responseMessage, String statusCode, Map<Integer,String> paymentCards, Double rewardPoints) {
        super(responseMessage, statusCode);
        this.paymentCards = paymentCards;
        this.rewardPoints = rewardPoints;
    }

    public PaymentOptionsResponse(){

    }

    public Map<Integer,String> getPaymentCards() {
        return paymentCards;
    }

    public void setPaymentCards(Map<Integer,String> paymentCards) {
        this.paymentCards = paymentCards;
    }

    public Double getRewardPoints() {
        return rewardPoints;
    }

    public void setRewardPoints(Double rewardPoints) {
        this.rewardPoints = rewardPoints;
    }

    @Override
    public String toString() {
        return "PaymentOptionsResponse{" +
                "paymentCards=" + paymentCards +
                ", rewardPoints=" + rewardPoints +
                '}';
    }
}

