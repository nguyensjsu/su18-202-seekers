package edu.sjsu.seekers.PaymentAPI.service;

import edu.sjsu.seekers.PaymentAPI.Response.PaymentOptionsResponse;

public interface PaymentOptionsService {
    PaymentOptionsResponse getPaymentOptions(String userName);
}
