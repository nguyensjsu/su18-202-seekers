package edu.sjsu.seekers.PaymentCardDetailsAPI.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.sjsu.seekers.PaymentCardDetailsAPI.Response.CardDetailsResponse;
import edu.sjsu.seekers.PaymentCardDetailsAPI.Response.GenericResponse;
import edu.sjsu.seekers.PaymentCardDetailsAPI.service.CardDetailServiceAPI;
import edu.sjsu.seekers.starbucks.dao.AddressDAO;
import edu.sjsu.seekers.starbucks.dao.PaymentCardDetailsDAO;
import edu.sjsu.seekers.starbucks.dao.UserDAO;
import edu.sjsu.seekers.starbucks.model.PaymentCardDetails;
import edu.sjsu.seekers.starbucks.model.User;
import org.springframework.beans.factory.annotation.Autowired;
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
        CardDetailsResponse cardDetailsResponse=new CardDetailsResponse();
        List<CardDetailsResponse> cardDetails=new ArrayList<CardDetailsResponse>();
        System.out.println("getCardDetails");
        if (userId!=null)
        {
            Optional<User> user=userdao.get(userId);
            List<PaymentCardDetails> cardDetail = paymentCardDetailsDAO.findPaymentCardDetailsByUserKey(user.get().getUserKey());
            System.out.println(cardDetail.toString());
            cardDetailsResponse.setCardDetails(cardDetail);
        }
        return cardDetailsResponse;

    }
}
