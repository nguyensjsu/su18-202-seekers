package edu.sjsu.seekers.PaymentCardDetailsAPI.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.sjsu.seekers.PaymentCardDetailsAPI.Request.CardDetailsRequest;
import edu.sjsu.seekers.PaymentCardDetailsAPI.Request.DeleteCardRequest;
import edu.sjsu.seekers.PaymentCardDetailsAPI.Request.UpdateExistingCardRequest;
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
        Optional<User> user = userdao.get(userId);
        if (userId != null && user.get().getIsLoggedIn().equals("Y")   ) {
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
    public GenericResponse saveCardDetails(CardDetailsRequest request) {
        GenericResponse saveCardsResponse = new GenericResponse();
        PaymentCardDetails cardDetails = new PaymentCardDetails();
        Address newAddress = new Address();
        Optional<User> user = userdao.findUserByUsername(request.getUserName());

        if (request.getUserName() != null && user.get().getIsLoggedIn().equals("Y")) {
            newAddress.setAddressLine1(request.getAddressLine1());
            newAddress.setAddressLine2(request.getAddressLine2());
            newAddress.setCity(request.getCity());
            newAddress.setState(request.getState());
            newAddress.setCountry(request.getCountry());
            newAddress.setZipCode(request.getZipCode());
            addressDAO.save(newAddress);
            Optional<Address> lastInsertedAddressKey = addressDAO.getLastInsertedRow();
            cardDetails.setAddressKey(lastInsertedAddressKey.get());
            cardDetails.setIsActiverCard("Y");
            cardDetails.setIsDefaultpaymentcardKey("Y");
            cardDetails.setUserKey(user.get());
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

    @Override
    public GenericResponse UpdateCard(UpdateExistingCardRequest request) {
        GenericResponse updateCard = new GenericResponse();
        Optional<PaymentCardDetails> cardDetails = paymentCardDetailsDAO.getByCardNumber(request.getCardNumber());
        Optional<User> user = userdao.findUserByUsername(request.getUserName());
        System.out.println(user.get().getUserKey());
        Optional<Address> existingUserAddress = addressDAO.get(user.get().getAddressKey().getAddressKey());
        System.out.println(existingUserAddress);
        System.out.println(existingUserAddress.get().getAddressKey());
        if (cardDetails != null && existingUserAddress != null &&  user.get().getIsLoggedIn().equals("Y")) {

            if (request.getAddressLine1() != null)
                existingUserAddress.get().setAddressLine1(request.getAddressLine1());
            if (request.getAddressLine2() != null)
                existingUserAddress.get().setAddressLine2(request.getAddressLine2());
            if (request.getCity() != null)
                existingUserAddress.get().setCity(request.getCity());
            if (request.getState() != null)
                existingUserAddress.get().setState(request.getState());
            if (request.getCountry() != null)
                existingUserAddress.get().setCountry(request.getCountry());
            if (request.getZipCode() != null)
                existingUserAddress.get().setZipCode(request.getZipCode());
            if (request.getCardNumber() != null)
                cardDetails.get().setCardNumber(request.getCardNumber());
            if (request.getExpirationMonth() != null)
                cardDetails.get().setExpirationMonth(request.getExpirationMonth());
            if (request.getExpirationYear() != null)
                cardDetails.get().setExpirationYear(request.getExpirationYear());
            if (request.getCcvCode() != null)
                cardDetails.get().setCcvCode(request.getCcvCode());

            addressDAO.save(existingUserAddress.get());
            paymentCardDetailsDAO.save(cardDetails.get());

            updateCard.setMessage("User Details updated successfully");
        } else {
            updateCard.setMessage("Card details could not update");
        }
        updateCard.setStatusCode(HttpStatus.OK.toString());
        return updateCard;
    }

    @Override
    public GenericResponse deleteCard(DeleteCardRequest request) {
        GenericResponse deleteCard = new GenericResponse();
        Optional<User> user=userdao.findUserByUsername(request.getUserName());
        Optional<PaymentCardDetails> cardDetails = paymentCardDetailsDAO.getByCardNumber(request.getCardNumber());

        if(request.getUserName()!=null && request.getCardNumber()!=null && user.get().getIsLoggedIn().equals("Y"))
        {
            cardDetails.get().setIsActiverCard("N");
            paymentCardDetailsDAO.save(cardDetails.get());
            deleteCard.setMessage("Card deleted");
        }
        else
        {
            deleteCard.setMessage("Card does not exist");
        }

        deleteCard.setStatusCode(HttpStatus.OK.toString());
        return  deleteCard;

    }
}
