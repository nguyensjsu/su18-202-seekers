package edu.sjsu.seekers.starbucks.dao;

import edu.sjsu.seekers.starbucks.model.PaymentDetails;

import java.util.Optional;

public interface PaymentDetailsDAO {
    public Optional<PaymentDetails> get(Integer id);
}

