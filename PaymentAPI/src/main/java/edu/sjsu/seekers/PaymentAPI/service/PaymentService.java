package edu.sjsu.seekers.PaymentAPI.service;

import edu.sjsu.seekers.PaymentAPI.Request.ReviewOrderRequest;
import edu.sjsu.seekers.PaymentAPI.Response.ConfirmOrderResponse;
import edu.sjsu.seekers.PaymentAPI.Response.PaymentOptionsResponse;
import edu.sjsu.seekers.PaymentAPI.Response.ReviewOrderDetailsResponse;
import org.springframework.http.ResponseEntity;

public interface PaymentService {
    PaymentOptionsResponse getPaymentOptions(String userName);
    ConfirmOrderResponse doConfirmOrder(String confirm,String userName);
    ResponseEntity<ReviewOrderDetailsResponse> reviewOrder(ReviewOrderRequest reviewOrderRequest);
}
