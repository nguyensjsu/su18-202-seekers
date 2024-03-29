package edu.sjsu.seekers.PaymentCardDetailsAPI.controllers;


import edu.sjsu.seekers.PaymentCardDetailsAPI.Request.CardDetailsRequest;
import edu.sjsu.seekers.PaymentCardDetailsAPI.Request.DeleteCardRequest;
import edu.sjsu.seekers.PaymentCardDetailsAPI.Request.UpdateExistingCardRequest;
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

    /***
     * get all card details using userid
     * @param userId
     * @return
     */

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

    /****
     * save all card details and also verigy string length of card length and cvv
     * @param request
     * @return
     */

        @RequestMapping(value = "/saveCards" , method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ResponseEntity<GenericResponse> saveCardDetails(@RequestBody CardDetailsRequest request ) {
        ResponseEntity<GenericResponse> responseEntity = null;
        try {
            System.out.println("CardDetailsRequest: " + request);
            if(request.getCardNumber().length()==9 ) {
                if (request.getCcvCode().length() == 3) {
                    if(Integer.parseInt(request.getExpirationMonth())<=12 && Integer.parseInt(request.getExpirationMonth())>=1)
                    {
                        GenericResponse response = apiService.saveCardDetails(request);
                        responseEntity = new ResponseEntity<GenericResponse>(response, HttpStatus.OK);
                    }
                    else
                    {
                        GenericResponse response = new GenericResponse();
                        response.setMessage("Expiration month cannot exceed 12");
                        response.setStatusCode(HttpStatus.EXPECTATION_FAILED.toString());
                        responseEntity = new ResponseEntity<GenericResponse>(response, HttpStatus.OK);
                    }
                } else {
                    GenericResponse response = new GenericResponse();
                    response.setMessage("CVV Length should be three characters");
                    response.setStatusCode(HttpStatus.EXPECTATION_FAILED.toString());
                    responseEntity = new ResponseEntity<GenericResponse>(response, HttpStatus.OK);

                }

            }
            else {

                GenericResponse response = new GenericResponse();
                response.setMessage("Card Length should be 9 digits ");
                response.setStatusCode(HttpStatus.EXPECTATION_FAILED.toString());
                responseEntity = new ResponseEntity<GenericResponse>(response, HttpStatus.OK);
            }

        } catch (Exception e) {
            GenericResponse response = new GenericResponse();
            response.setMessage(e.getMessage());
            response.setStatusCode(HttpStatus.EXPECTATION_FAILED.toString());
            responseEntity = new ResponseEntity<GenericResponse>(response, HttpStatus.EXPECTATION_FAILED);

        }
        return responseEntity;
    }

    /***
     * update all card details
     * @param request
     * @return
     */

    @RequestMapping(value="/UpdateExistingCardDetails",  method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ResponseEntity<GenericResponse> UpdateCard(@RequestBody UpdateExistingCardRequest request)
    {
        ResponseEntity<GenericResponse> responseEntity = null;
        try {

            System.out.println("UpdateExistingCardRequest: " + request);

            GenericResponse response = apiService.UpdateCard(request);
            responseEntity = new ResponseEntity<GenericResponse>(response, HttpStatus.OK);

        }
        catch (Exception e)
        {
            GenericResponse response = new GenericResponse();
            response.setMessage(e.getMessage());
            response.setStatusCode(HttpStatus.EXPECTATION_FAILED.toString());
            responseEntity = new ResponseEntity<GenericResponse>(response, HttpStatus.EXPECTATION_FAILED);
        }
        return responseEntity;
    }

    /***
     * delete card if user wants to remove
     * @param request
     * @return
     */
    @RequestMapping(value="/DeleteCard",  method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ResponseEntity<GenericResponse> deleteCard(@RequestBody DeleteCardRequest request)
    {
        ResponseEntity<GenericResponse> responseEntity = null;
        try {

            System.out.println("DeletecardRequest: " + request);

            GenericResponse response = apiService.deleteCard(request);
            responseEntity = new ResponseEntity<GenericResponse>(response, HttpStatus.OK);

        }
        catch (Exception e)
        {
            GenericResponse response = new GenericResponse();
            response.setMessage(e.getMessage());
            response.setStatusCode(HttpStatus.EXPECTATION_FAILED.toString());
            responseEntity = new ResponseEntity<GenericResponse>(response, HttpStatus.EXPECTATION_FAILED);
        }
        return responseEntity;

    }


        @RequestMapping("/greetings")
    public String greeting(@RequestParam(value = "name", defaultValue = "World") String name) {

        return String.format("Hello %s", name);
    }
}