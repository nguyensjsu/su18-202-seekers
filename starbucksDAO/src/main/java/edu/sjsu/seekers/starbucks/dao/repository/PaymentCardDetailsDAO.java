package edu.sjsu.seekers.starbucks.dao.repository;

import edu.sjsu.seekers.starbucks.model.Orders;
import edu.sjsu.seekers.starbucks.model.PaymentCardDetails;

import java.util.List;
import java.util.Optional;

public interface PaymentCardDetailsDAO {
    public Optional<PaymentCardDetails> get(Integer id);
    public void save(PaymentCardDetails cardDetails);
    public List<PaymentCardDetails> findPaymentCardDetailsByUserKey(Integer userKey);


}
