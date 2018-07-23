package edu.sjsu.seekers.PaymentAPI.service.impl;

import edu.sjsu.seekers.PaymentAPI.Request.PaymentRequest;
import edu.sjsu.seekers.PaymentAPI.Response.PaymentOptionsResponse;
import edu.sjsu.seekers.PaymentAPI.Response.PaymentResponse;
import edu.sjsu.seekers.PaymentAPI.service.PaymentOptionsService;
import edu.sjsu.seekers.starbucks.dao.PaymentCardDetailsDAO;
import edu.sjsu.seekers.starbucks.dao.PaymentDetailsDAO;
import edu.sjsu.seekers.starbucks.dao.UserDAO;
import edu.sjsu.seekers.starbucks.dao.impl.UserDAOImpl;
import edu.sjsu.seekers.starbucks.model.PaymentCardDetails;
import edu.sjsu.seekers.starbucks.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentOptionsServiceImpl implements PaymentOptionsService {
    @Autowired
    PaymentCardDetailsDAO paymentCardDetailsDAO;
    @Autowired
    UserDAO userDAO;

    @Override
    public PaymentResponse confirmOrder(PaymentRequest request) {
        PaymentResponse response  = new PaymentResponse();

        response.setResponseMessage(request.getUserName().toString()+" "+request.getPassword().toString());
        return response;
    }

    @Override
    public PaymentOptionsResponse getPaymentOptions(String userName) {
        Optional<User> user = userDAO.findUserByUsername(userName);
        System.out.println(user.get().getUserKey());
        List<PaymentCardDetails> paymentCards = paymentCardDetailsDAO.findPaymentCardDetailsByUserKey(user.get().getUserKey());
        System.out.println(paymentCards);
        return null;
    }
}
