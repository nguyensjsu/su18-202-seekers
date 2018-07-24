package edu.sjsu.seekers.PaymentCardDetailsAPI.controllers;


import edu.sjsu.seekers.PaymentCardDetailsAPI.Request.CardDetailsRequest;
import edu.sjsu.seekers.PaymentCardDetailsAPI.Response.CardDetailsResponse;
import edu.sjsu.seekers.PaymentCardDetailsAPI.Response.GenericResponse;
import edu.sjsu.seekers.PaymentCardDetailsAPI.Response.SaveCardsResponse;
import edu.sjsu.seekers.PaymentCardDetailsAPI.service.CardDetailServiceAPI;
import edu.sjsu.seekers.PaymentCardDetailsAPI.service.TestClass;
import edu.sjsu.seekers.PaymentCardDetailsAPI.service.impl.CardDetailsServiceAPIImpl;
import edu.sjsu.seekers.starbucks.dao.PaymentCardDetailsDAO;
import edu.sjsu.seekers.starbucks.dao.impl.OrderDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.sjsu.seekers.starbucks.dao.impl.PaymentCardDetailsDAOImpl;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardDetailsController {

    @Autowired
    CardDetailServiceAPI apiService;


    ObjectMapper mapper = new ObjectMapper();

    @RequestMapping(value = "/cardDetails")

    public ResponseEntity<CardDetailsResponse> getCardDetails(@RequestParam(value="userId") Integer userId) {
        ResponseEntity<CardDetailsResponse> responseEntity = null;
        try {
            if (userId == null) {
                throw new Exception("User Not Found");
            }
            CardDetailsResponse response = apiService.getCardDetails(userId);
            responseEntity = new ResponseEntity<CardDetailsResponse> (response, HttpStatus.OK);

        } catch (Exception e) {
            CardDetailsResponse response = new CardDetailsResponse();
            response.setMessage(e.getMessage());
            response.setStatusCode(HttpStatus.EXPECTATION_FAILED.toString());
            responseEntity = new ResponseEntity<CardDetailsResponse>(response, HttpStatus.EXPECTATION_FAILED);

        }
        return responseEntity;
    }

        @RequestMapping(value = "/saveCards" , method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ResponseEntity<SaveCardsResponse> saveCardDetails(@RequestBody CardDetailsRequest request ) {
        ResponseEntity<SaveCardsResponse> responseEntity = null;
        try {
            System.out.println("CardDetailsRequest: " + request);
             SaveCardsResponse response=apiService.saveCardDetails(request);
            responseEntity = new ResponseEntity<SaveCardsResponse>(response, HttpStatus.OK);

        } catch (Exception e) {
            SaveCardsResponse response = new SaveCardsResponse();
            response.setMessage(e.getMessage());
            response.setStatusCode(HttpStatus.EXPECTATION_FAILED.toString());
            responseEntity = new ResponseEntity<SaveCardsResponse>(response, HttpStatus.EXPECTATION_FAILED);

        }
        return responseEntity;
    }



        @RequestMapping("/greetings")
    public String greeting(@RequestParam(value = "name", defaultValue = "World") String name) {

        return String.format("Hello %s", name);
    }
}