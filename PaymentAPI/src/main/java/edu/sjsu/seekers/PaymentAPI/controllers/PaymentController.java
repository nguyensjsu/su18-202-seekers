package edu.sjsu.seekers.PaymentAPI.controllers;

import edu.sjsu.seekers.PaymentAPI.Response.PaymentOptionsResponse;
import edu.sjsu.seekers.PaymentAPI.service.PaymentOptionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PaymentController {

    @Autowired
    PaymentOptionsService paymentOptionsService;

    //Payment Options API
    @RequestMapping(value = "/paymentOptions", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity<PaymentOptionsResponse> getPaymentOptions(@RequestParam(value = "userName")String userName)
    {
        ResponseEntity<PaymentOptionsResponse> responseEntity;
        PaymentOptionsResponse paymentOptionsResponse;

        paymentOptionsResponse = paymentOptionsService.getPaymentOptions(userName);
        paymentOptionsResponse.setStatusCode(HttpStatus.OK.toString());
        responseEntity = new ResponseEntity<>(paymentOptionsResponse,HttpStatus.OK);
        return responseEntity;
    }
}
