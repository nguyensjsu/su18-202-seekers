package edu.sjsu.seekers.PaymentCardDetailsAPI.service;

import edu.sjsu.seekers.PaymentCardDetailsAPI.Request.CardDetailsRequest;
import edu.sjsu.seekers.PaymentCardDetailsAPI.Response.CardDetailsResponse;
import edu.sjsu.seekers.PaymentCardDetailsAPI.Response.GenericResponse;
import edu.sjsu.seekers.starbucks.model.PaymentCardDetails;
import edu.sjsu.seekers.starbucks.model.User;

import java.util.Optional;

public interface CardDetailServiceAPI {

    CardDetailsResponse getCardDetails(Integer userId);
}
