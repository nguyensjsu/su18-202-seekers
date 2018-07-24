package edu.sjsu.seekers.PaymentAPI.service.impl;

import edu.sjsu.seekers.PaymentAPI.Response.ConfirmOrderResponse;
import edu.sjsu.seekers.PaymentAPI.Response.PaymentOptionsResponse;
import edu.sjsu.seekers.PaymentAPI.service.PaymentService;
import edu.sjsu.seekers.starbucks.dao.OrderDAO;
import edu.sjsu.seekers.starbucks.dao.PaymentCardDetailsDAO;
import edu.sjsu.seekers.starbucks.dao.PaymentDetailsDAO;
import edu.sjsu.seekers.starbucks.dao.UserDAO;
import edu.sjsu.seekers.starbucks.model.Orders;
import edu.sjsu.seekers.starbucks.model.PaymentCardDetails;
import edu.sjsu.seekers.starbucks.model.PaymentDetails;
import edu.sjsu.seekers.starbucks.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    PaymentCardDetailsDAO paymentCardDetailsDAO;
    @Autowired
    UserDAO userDAO;
    @Autowired
    OrderDAO orderDao;
    @Autowired
    PaymentDetailsDAO paymentDetailsDAO;

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
        else {
            paymentOptionsResponse.setResponseMessage("User has no Cards. Please add a card.");
            return paymentOptionsResponse;
        }
    }

    @Override
    public ConfirmOrderResponse doConfirmOrder(String confirm,String userName) {
        ConfirmOrderResponse confirmOrderResponse = new ConfirmOrderResponse();
        Double rewardPoints;
        Optional<User> user;
        User userSave;
        Optional<Orders> orders;
        Orders orderSave;
        Boolean completedFlag =false;

        if(confirm.equalsIgnoreCase("Yes"))
        {
            user = userDAO.findUserByUsername(userName);

            //Get Order that is InProgress and set status to Completed
            orders = orderDao.findIncompleteOrdersByUserKey(user.get().getUserKey());
            if(orders.get().getCardKey()==null)
            {
              if(user.get().getRewardPoints() >= orders.get().getOrderAmount()) {
                  rewardPoints = user.get().getRewardPoints() - orders.get().getOrderAmount();
                  userSave = user.get();
                  userSave.setRewardPoints(rewardPoints);
                  userDAO.save(userSave);

                  orderSave = orders.get();
                  orderSave.setOrderStatus("Completed");
                  orderDao.save(orderSave);
                  completedFlag = true;
              }
              else{
                  //Insufficient Rewards
                  confirmOrderResponse.setResponseMessage("Insufficient Reward points to make this transaction");
              }
            }
            else {
                orderSave = orders.get();
                orderSave.setOrderStatus("Completed");
                orderDao.save(orderSave);
                completedFlag = true;
            }

            if(completedFlag)
            {
                PaymentDetails paymentDetails = new PaymentDetails();
                PaymentDetails paymentDetailsResponse;
                paymentDetails.setOrderKey(orders.get());
                paymentDetails.setPaymentStatus("SUCCESS");
                paymentDetailsResponse = paymentDetailsDAO.save(paymentDetails);
                confirmOrderResponse.setResponseMessage("Congratulations! Order success with Payment ID: "+paymentDetailsResponse.getPaymentId());
                confirmOrderResponse.setPaymentId(paymentDetailsResponse.getPaymentId());
                return confirmOrderResponse;
            }
        }
        return confirmOrderResponse;
    }
}