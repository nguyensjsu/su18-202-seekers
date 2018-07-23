package edu.sjsu.seekers.PaymentAPI.service;

import edu.sjsu.seekers.PaymentAPI.Request.PaymentRequest;
import edu.sjsu.seekers.PaymentAPI.Response.PaymentResponse;

public interface PaymentServiceAPI {
    PaymentResponse confirmOrder(PaymentRequest response);
}
