package edu.sjsu.seekers.starbucks.dao;

import edu.sjsu.seekers.starbucks.model.payment_details;

import java.util.Optional;

public interface PaymentDetailsDAO {
    public Optional<payment_details> get(Integer id);
}

