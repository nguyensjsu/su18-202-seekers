package edu.sjsu.seekers.PaymentAPI.service;

import edu.sjsu.seekers.PaymentAPI.Response.ConfirmOrderResponse;
import edu.sjsu.seekers.PaymentAPI.Response.PaymentOptionsResponse;

public interface PaymentService {
    PaymentOptionsResponse getPaymentOptions(String userName);
    ConfirmOrderResponse doConfirmOrder(String confirm,String userName);
}
