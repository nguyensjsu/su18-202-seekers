package edu.sjsu.seekers.starbucks.dao;

import edu.sjsu.seekers.starbucks.model.OrderDetails;
import edu.sjsu.seekers.starbucks.model.PaymentDetails;

import java.util.Optional;

public interface PaymentDetailsDAO {
    Optional<PaymentDetails> get(Integer id);
    void save(PaymentDetails paymentDetails);
}

