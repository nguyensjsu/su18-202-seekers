package edu.sjsu.seekers.PaymentAPI.controllers;

import edu.sjsu.seekers.PaymentAPI.Response.GenericResponse;
import edu.sjsu.seekers.PaymentAPI.Response.PaymentOptionsResponse;
import edu.sjsu.seekers.PaymentAPI.service.PaymentOptionsService;
import edu.sjsu.seekers.starbucks.dao.PaymentDetailsDAO;
import edu.sjsu.seekers.starbucks.model.OrderDetails;
import edu.sjsu.seekers.starbucks.model.PaymentDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import edu.sjsu.seekers.starbucks.dao.OrderDetailsDAO;

import java.util.Optional;

@RestController
public class PaymentController {
    @Autowired
    PaymentDetailsDAO paymentDetailsDAO;
    @Autowired
    OrderDetailsDAO orderDetailsDAO;

    @Autowired
    PaymentOptionsService paymentOptionsService;

    @RequestMapping(value = "/confirmOrder", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity<GenericResponse> confirmOrderController(){
        ResponseEntity<GenericResponse> responseEntity = null;
        Optional<PaymentDetails> response;
        Optional<OrderDetails> response1;
        try {
            response = paymentDetailsDAO.get(1);
            response1 = orderDetailsDAO.get(8);
            System.out.println("PAYMENT RESPONSE: "+response.toString());
            System.out.println("ORDER DETAILS RESPONSE: "+response1.toString());
        } catch (Exception e) {
        }
        return responseEntity;
    }

    //Payment Options API
    @RequestMapping(value = "/paymentOptions", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity<PaymentOptionsResponse> getPaymentOptions(@RequestParam(value = "userName")String userName)
    {
        ResponseEntity<PaymentOptionsResponse> responseEntity = null;
        PaymentOptionsResponse paymentOptionsResponse = new PaymentOptionsResponse();

//        paymentOptionsResponse.setPaymentCards();
        paymentOptionsService.getPaymentOptions(userName);

        return null;
    }
}
