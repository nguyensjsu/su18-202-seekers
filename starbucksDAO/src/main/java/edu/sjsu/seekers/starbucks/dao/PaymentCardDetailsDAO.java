package edu.sjsu.seekers.starbucks.dao;

import edu.sjsu.seekers.starbucks.model.Orders;
import edu.sjsu.seekers.starbucks.model.PaymentCardDetails;
import edu.sjsu.seekers.starbucks.model.User;

import java.util.List;
import java.util.Optional;

public interface PaymentCardDetailsDAO {

    public Optional<PaymentCardDetails> get(Integer id);
    public Optional<PaymentCardDetails>getByCardNumber(String CardNumber);
    public void save(PaymentCardDetails cardDetails);
    public List<PaymentCardDetails> findPaymentCardDetailsByUserKey(Integer userKey);
     public void delete(PaymentCardDetails cardDetails);

}
