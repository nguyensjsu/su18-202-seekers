package edu.sjsu.seekers.PaymentAPI.service.impl;

import edu.sjsu.seekers.PaymentAPI.Request.PaymentRequest;
import edu.sjsu.seekers.PaymentAPI.Response.PaymentResponse;
import edu.sjsu.seekers.PaymentAPI.service.PaymentServiceAPI;

public class PaymentServiceAPIImpl implements PaymentServiceAPI {

    @Override
    public PaymentResponse confirmOrder(PaymentRequest request) {
        PaymentResponse response  = new PaymentResponse();

        response.setResponseMessage(request.getUserName().toString()+" "+request.getPassword().toString());
        return response;
    }
}
