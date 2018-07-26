package edu.sjsu.seekers.PaymentAPI.controllers;

import edu.sjsu.seekers.PaymentAPI.Request.ReviewOrderRequest;
import edu.sjsu.seekers.PaymentAPI.Response.ConfirmOrderResponse;
import edu.sjsu.seekers.PaymentAPI.Response.GenericResponse;
import edu.sjsu.seekers.PaymentAPI.Response.PaymentOptionsResponse;
import edu.sjsu.seekers.PaymentAPI.Response.ReviewOrderDetailsResponse;
import edu.sjsu.seekers.PaymentAPI.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PaymentController {
    @Autowired
    PaymentService paymentService;

    //Payment Options API
    @RequestMapping(value = "/paymentOptions", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity<PaymentOptionsResponse> getPaymentOptions(@RequestParam(value = "userName")String userName)
    {
//        ResponseEntity<PaymentOptionsResponse> responseEntity;
//        PaymentOptionsResponse paymentOptionsResponse;

        ResponseEntity<PaymentOptionsResponse> responseEntity = paymentService.getPaymentOptions(userName);
//        paymentOptionsResponse.setStatusCode(HttpStatus.OK.toString());
//        responseEntity = new ResponseEntity<>(paymentOptionsResponse,HttpStatus.OK);
        return responseEntity;
    }


    @RequestMapping(value = "/clearCart", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity<GenericResponse> clearCart(@RequestParam(value = "userName")String userName)
    {
        return paymentService.clearCart(userName);
    }


	@RequestMapping(value = "/reviewOrder", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ResponseEntity<ReviewOrderDetailsResponse> reviewOrder(@RequestBody ReviewOrderRequest reviewOrderRequest)
    {
        return paymentService.reviewOrder(reviewOrderRequest);
    }

    //Confirm Order API
    @RequestMapping(value = "/confirmOrder", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ResponseEntity<ConfirmOrderResponse> doConfirmOrder(@RequestParam(value ="confirm")String confirm,
                                                               @RequestParam(value ="userName")String userName)
    {
//        ResponseEntity<ConfirmOrderResponse> responseEntity;
//        ConfirmOrderResponse confirmOrderResponse;

        ResponseEntity<ConfirmOrderResponse> responseEntity = paymentService.doConfirmOrder(confirm,userName);
//        confirmOrderResponse.setStatusCode(HttpStatus.OK.toString());
//        responseEntity = new ResponseEntity<>(confirmOrderResponse,HttpStatus.OK);
        return responseEntity;
    }

}
