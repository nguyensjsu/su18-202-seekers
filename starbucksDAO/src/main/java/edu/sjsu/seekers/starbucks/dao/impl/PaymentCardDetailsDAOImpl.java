package edu.sjsu.seekers.starbucks.dao.impl;

import edu.sjsu.seekers.starbucks.dao.PaymentCardDetailsDAO;
import edu.sjsu.seekers.starbucks.dao.repository.PaymentCardDetailsRepository;
import edu.sjsu.seekers.starbucks.model.PaymentCardDetails;
import edu.sjsu.seekers.starbucks.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Optional;

@Configuration
public class PaymentCardDetailsDAOImpl implements PaymentCardDetailsDAO {

    @Autowired
    PaymentCardDetailsRepository paymentCardDetailsRepository;


    @Override
    public Optional<PaymentCardDetails> get(Integer id) {
        Optional<PaymentCardDetails> paymentCardDetails;
        paymentCardDetails = paymentCardDetailsRepository.findById(id);
        return paymentCardDetails;
    }

    @Override
    public Optional<PaymentCardDetails> getByCardNumber(String CardNumber) {
        Optional<PaymentCardDetails> paymentCardDetails;
        paymentCardDetails=paymentCardDetailsRepository.findByCardNumber(CardNumber);
        return paymentCardDetails;
    }

    @Override
    public void save(PaymentCardDetails cardDetails) {

        paymentCardDetailsRepository.save(cardDetails);

    }

    @Override
    public List<PaymentCardDetails> findPaymentCardDetailsByUserKey(Integer userKey) {
        return paymentCardDetailsRepository.findByUserKey(userKey);
    }

    @Override
    public void delete(PaymentCardDetails cardDetails) {
        paymentCardDetailsRepository.delete(cardDetails);
    }


}