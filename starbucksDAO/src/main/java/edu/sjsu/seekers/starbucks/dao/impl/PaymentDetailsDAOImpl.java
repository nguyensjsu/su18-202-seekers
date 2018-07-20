package edu.sjsu.seekers.starbucks.dao.impl;

import edu.sjsu.seekers.starbucks.dao.PaymentDetailsDAO;
import edu.sjsu.seekers.starbucks.dao.repository.PaymentDetailsRepository;
import edu.sjsu.seekers.starbucks.model.payment_details;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import java.util.Optional;

@Configuration
public class PaymentDetailsDAOImpl implements PaymentDetailsDAO {
    @Autowired
    PaymentDetailsRepository paymentDetailsRepository;

    @Override
    public Optional<payment_details> get(Integer id) {
        Optional<payment_details> paymentDetails;
        paymentDetails =paymentDetailsRepository.findById(1);
        return paymentDetails;
    }
}

