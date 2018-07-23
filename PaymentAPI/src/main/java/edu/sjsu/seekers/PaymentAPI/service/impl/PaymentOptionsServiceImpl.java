package edu.sjsu.seekers.PaymentAPI.service.impl;

import edu.sjsu.seekers.PaymentAPI.Response.PaymentOptionsResponse;
import edu.sjsu.seekers.PaymentAPI.service.PaymentOptionsService;
import edu.sjsu.seekers.starbucks.dao.PaymentCardDetailsDAO;
import edu.sjsu.seekers.starbucks.dao.UserDAO;
import edu.sjsu.seekers.starbucks.model.PaymentCardDetails;
import edu.sjsu.seekers.starbucks.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class PaymentOptionsServiceImpl implements PaymentOptionsService {
    @Autowired
    PaymentCardDetailsDAO paymentCardDetailsDAO;
    @Autowired
    UserDAO userDAO;

    @Override
    public PaymentOptionsResponse getPaymentOptions(String userName) {
        PaymentOptionsResponse paymentOptionsResponse = new PaymentOptionsResponse();
        Map<Integer,String> cardList = new HashMap<>();
        String cardNumber;

        Optional<User> user = userDAO.findUserByUsername(userName);
        List<PaymentCardDetails> paymentCards = paymentCardDetailsDAO.findPaymentCardDetailsByUserKey(user.get().getUserKey());
        if(paymentCards != null) {
            for (PaymentCardDetails entry : paymentCards) {
                cardNumber = entry.getCardNumber();
                cardNumber = "************" + cardNumber.substring(cardNumber.length() - 4);
                cardList.put(entry.getCardKey(), cardNumber);
            }
            paymentOptionsResponse.setPaymentCards(cardList);
            paymentOptionsResponse.setRewardPoints(user.get().getRewardPoints());
            paymentOptionsResponse.setResponseMessage("Success!");
            return paymentOptionsResponse;
        }
        return null;
    }
}