package edu.sjsu.seekers.PaymentCardDetailsAPI.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.sjsu.seekers.PaymentCardDetailsAPI.Request.CardDetailsRequest;
import edu.sjsu.seekers.PaymentCardDetailsAPI.Response.CardDetailsResponse;
import edu.sjsu.seekers.PaymentCardDetailsAPI.Response.GenericResponse;
import edu.sjsu.seekers.PaymentCardDetailsAPI.Response.SaveCardsResponse;
import edu.sjsu.seekers.PaymentCardDetailsAPI.service.CardDetailServiceAPI;
import edu.sjsu.seekers.starbucks.dao.AddressDAO;
import edu.sjsu.seekers.starbucks.dao.PaymentCardDetailsDAO;
import edu.sjsu.seekers.starbucks.dao.UserDAO;
import edu.sjsu.seekers.starbucks.model.Address;
import edu.sjsu.seekers.starbucks.model.PaymentCardDetails;
import edu.sjsu.seekers.starbucks.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CardDetailsServiceAPIImpl implements CardDetailServiceAPI {

    @Autowired
    PaymentCardDetailsDAO paymentCardDetailsDAO;

    @Autowired
    UserDAO userdao;

    @Autowired
    AddressDAO addressDAO;

    private static ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
    }


    @Override
    public CardDetailsResponse getCardDetails(Integer userId) {
        CardDetailsResponse cardDetailsResponse = new CardDetailsResponse();
        List<CardDetailsResponse> cardDetails = new ArrayList<CardDetailsResponse>();
        System.out.println("getCardDetails");
        if (userId != null) {
            Optional<User> user = userdao.get(userId);
            List<PaymentCardDetails> cardDetail = paymentCardDetailsDAO.findPaymentCardDetailsByUserKey(user.get().getUserKey());
            System.out.println(cardDetail.toString());
            cardDetailsResponse.setCardDetails(cardDetail);
        }
        return cardDetailsResponse;

    }

    /*********
     * saving card deatails for specific user
     * @param request
     * @return
     */

    @Override
    public SaveCardsResponse saveCardDetails(CardDetailsRequest request) {
        SaveCardsResponse saveCardsResponse = new SaveCardsResponse();
        PaymentCardDetails cardDetails = new PaymentCardDetails();
        Address newAddress=new Address();
       Optional<User> user=userdao.findUserByUsername(request.getUserName());
        if (request.getUserName() != null) {
            newAddress.setAddressLine1(request.getAddressLine1());
            newAddress.setAddressLine2(request.getAddressLine2());
            newAddress.setCity(request.getCity());
            newAddress.setState(request.getState());
            newAddress.setCountry(request.getCountry());
            newAddress.setZipCode(request.getZipCode());
            addressDAO.save(newAddress);
            Integer lastInsertedAddressKey = addressDAO.getLastInsertedRow();
            cardDetails.setAddressKey(lastInsertedAddressKey);
            cardDetails.setUserKey(user.get().getUserKey());
            cardDetails.setCardNumber(request.getCardNumber());
            cardDetails.setExpirationMonth(request.getExpirationMonth());
            cardDetails.setExpirationYear(request.getExpirationYear());
            cardDetails.setCcvCode(request.getCcvCode());
            paymentCardDetailsDAO.save(cardDetails);
            saveCardsResponse.setMessage("Card Details Inserted Successfully");
        } else {
            saveCardsResponse.setMessage("Card Details were not added");

        }
        saveCardsResponse.setStatusCode(HttpStatus.OK.toString());
        return saveCardsResponse;

    }
}
