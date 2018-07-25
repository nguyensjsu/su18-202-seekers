package edu.sjsu.seekers.PaymentAPI.service.impl;

import edu.sjsu.seekers.PaymentAPI.Request.ReviewOrderRequest;
import edu.sjsu.seekers.PaymentAPI.Response.ConfirmOrderResponse;
import edu.sjsu.seekers.PaymentAPI.Response.PaymentOptionsResponse;
import edu.sjsu.seekers.PaymentAPI.Response.ReviewOrderDetailsResponse;
import edu.sjsu.seekers.PaymentAPI.service.PaymentService;
import edu.sjsu.seekers.starbucks.dao.*;
import edu.sjsu.seekers.starbucks.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.*;
import edu.sjsu.seekers.PaymentAPI.Response.GenericResponse;
import edu.sjsu.seekers.starbucks.model.OrderDetails;
import edu.sjsu.seekers.starbucks.model.Orders;
import edu.sjsu.seekers.starbucks.model.PaymentCardDetails;
import edu.sjsu.seekers.starbucks.model.User;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    PaymentCardDetailsDAO paymentCardDetailsDAO;
    @Autowired
    UserDAO userDAO;
    @Autowired
    OrderDAO orderDAO;
    @Autowired
    PaymentDetailsDAO paymentDetailsDAO;
    @Autowired
    OrderDetailsDAO orderDetailsDAO;

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
    public ResponseEntity<GenericResponse> clearCart(String userName) {
        ResponseEntity<GenericResponse> responseEntity;
        GenericResponse genericResponse= new GenericResponse();

        Optional<User> user = userDAO.findUserByUsername(userName);
        if(user.get()!=null) {
            Optional<Orders> orders = orderDAO.findIncompleteOrdersByUserKey(user.get().getUserKey());
            if (orders.isPresent()) {
                orderDetailsDAO.deleteOrderDetailsforOrder(orders.get().getOrderKey());
                orderDAO.delete(orders.get());
                genericResponse.setResponseMessage("Cart Cleared!");
                genericResponse.setStatusCode(HttpStatus.OK.toString());
                responseEntity = new  ResponseEntity<>(genericResponse,HttpStatus.OK);

                return responseEntity;
            } else {
                genericResponse.setResponseMessage("Cart could not be cleared as there are no products in the cart!");
                genericResponse.setStatusCode(HttpStatus.EXPECTATION_FAILED.toString());
                responseEntity = new ResponseEntity<>(genericResponse,HttpStatus.EXPECTATION_FAILED);

                return responseEntity;
            }
        }
        else {
            genericResponse.setResponseMessage("Cart could not be cleared as the user was not authenticated!");
            genericResponse.setStatusCode(HttpStatus.EXPECTATION_FAILED.toString());
            responseEntity = new ResponseEntity<>(genericResponse,HttpStatus.EXPECTATION_FAILED);
            return responseEntity;
        }
    }


    @Override
    public ResponseEntity<ReviewOrderDetailsResponse> reviewOrder(ReviewOrderRequest reviewOrderRequest) {
        ResponseEntity<ReviewOrderDetailsResponse> responseEntity;
        ReviewOrderDetailsResponse response= new ReviewOrderDetailsResponse();

        Optional<User> user = userDAO.findUserByUsername(reviewOrderRequest.getUserName());
        if(user.get()!=null) {
            Optional<Orders> orders = orderDAO.findIncompleteOrdersByUserKey(user.get().getUserKey());
            if(orders.isPresent())
            {
                if(reviewOrderRequest.getPaymentType().equals("Card")) {
                    Optional<PaymentCardDetails> cardDetails = paymentCardDetailsDAO.get(Integer.parseInt(reviewOrderRequest.getCardID()));
                    //Assuming card are added already. Handle if there is no card usecase.
                    if(cardDetails.get().getCcvCode().equals(reviewOrderRequest.getCvv())) {
                        orders.get().setCardKey(cardDetails.get());
                        orders.get().setOrderDate(new Date(System.currentTimeMillis()));
                        orderDAO.save(orders.get());
                        List<OrderDetails> orderDetails = orderDetailsDAO.findAllOrderDetailsforOrderKey(orders.get().getOrderKey());

                        response.setOrderDetails(orderDetails);
                        response.setPaymentType(reviewOrderRequest.getPaymentType());
                        response.setResponseMessage("Please review your order and confirm checkout!");
                        response.setStatusCode(HttpStatus.OK.toString());
                        responseEntity = new ResponseEntity<>(response,HttpStatus.OK);

                        return responseEntity;
                    }
                    else {
                        response.setResponseMessage("Card CVV did not match!");
                        response.setStatusCode(HttpStatus.EXPECTATION_FAILED.toString());
                        responseEntity = new ResponseEntity<>(response,HttpStatus.EXPECTATION_FAILED);

                        return responseEntity;
                    }
                }
                else{
                    Double rwardBalence = user.get().getRewardPoints();
                    if(rwardBalence>orders.get().getOrderAmount()){
                        orders.get().setOrderDate(new Date(System.currentTimeMillis()));
                        orderDAO.save(orders.get());
                        List<OrderDetails> orderDetails = orderDetailsDAO.findAllOrderDetailsforOrderKey(orders.get().getOrderKey());
                        response.setOrderDetails(orderDetails);
                        response.setPaymentType(reviewOrderRequest.getPaymentType());
                        response.setResponseMessage("Please review your order and confirm checkout!");
                        response.setStatusCode(HttpStatus.OK.toString());
                        responseEntity = new ResponseEntity<>(response,HttpStatus.OK);

                        return responseEntity;

                    }
                    else {
                        response.setResponseMessage("Not Enough reward points to make a purchase!");
                        response.setStatusCode(HttpStatus.EXPECTATION_FAILED.toString());
                        responseEntity = new ResponseEntity<>(response,HttpStatus.EXPECTATION_FAILED);

                        return responseEntity;
                    }
                }
            }
            else {
                response.setResponseMessage("No Orders in th cart to clear!!!");
                response.setStatusCode(HttpStatus.EXPECTATION_FAILED.toString());
                responseEntity = new ResponseEntity<>(response,HttpStatus.EXPECTATION_FAILED);
                return responseEntity;
            }
        }
        else {
            response.setResponseMessage("Unable to find a user with this userName!");
            response.setStatusCode(HttpStatus.EXPECTATION_FAILED.toString());
            responseEntity = new ResponseEntity<>(response,HttpStatus.EXPECTATION_FAILED);

            return responseEntity;
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
            orders = orderDAO.findIncompleteOrdersByUserKey(user.get().getUserKey());
            if(orders.get().getCardKey()==null)
            {
              if(user.get().getRewardPoints() >= orders.get().getOrderAmount()) {

                  //Check Rewards
                  rewardPoints = user.get().getRewardPoints() - orders.get().getOrderAmount();
                  userSave = user.get();
                  userSave.setRewardPoints(rewardPoints);
                  userDAO.save(userSave);

                  //Change Order status to Completed
                  orderSave = orders.get();
                  orderSave.setOrderStatus("Completed");
                  orderDAO.save(orderSave);
                  completedFlag = true;
              }
              else{
                  //Insufficient Rewards
                  confirmOrderResponse.setResponseMessage("Insufficient Reward points to make this transaction");
              }
            }
            else {
                //Add reward points to User table Rewards
                double addRewards = orders.get().getRewardsEarned();
                userSave = user.get();
                double currentRewards = user.get().getRewardPoints();
                userSave.setRewardPoints(currentRewards+addRewards);
                userDAO.save(userSave);

                //Change Order status to Completed
                orderSave = orders.get();
                orderSave.setOrderStatus("Completed");
                orderDAO.save(orderSave);
                completedFlag = true;
            }

            if(completedFlag)
            {
                //If order completed, insert data to PAYMENT_DETAILS table
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