package edu.sjsu.seekers.PaymentAPI.service;

import edu.sjsu.seekers.PaymentAPI.Request.PaymentRequest;
import edu.sjsu.seekers.PaymentAPI.Response.PaymentOptionsResponse;
import edu.sjsu.seekers.PaymentAPI.Response.PaymentResponse;

public interface PaymentOptionsService {
    PaymentResponse confirmOrder(PaymentRequest response);
    PaymentOptionsResponse getPaymentOptions(String userName);
}
