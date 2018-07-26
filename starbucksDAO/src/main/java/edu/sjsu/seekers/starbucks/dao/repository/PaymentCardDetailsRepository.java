package edu.sjsu.seekers.starbucks.dao.repository;

import edu.sjsu.seekers.starbucks.model.Orders;
import edu.sjsu.seekers.starbucks.model.PaymentCardDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentCardDetailsRepository extends JpaRepository<PaymentCardDetails, Integer>  {


    @Query("SELECT o FROM PaymentCardDetails o WHERE o.userKey.userKey = :userKey")
    List<PaymentCardDetails> findByUserKey(@Param("userKey") Integer userKey);


    @Query("SELECT o FROM PaymentCardDetails o WHERE o.cardNumber= :cardNumber ")
    Optional<PaymentCardDetails> findByCardNumber(@Param("cardNumber") String cardNumber);






}