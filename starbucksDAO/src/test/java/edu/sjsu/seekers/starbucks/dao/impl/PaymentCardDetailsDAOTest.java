package edu.sjsu.seekers.starbucks.dao.impl;

import edu.sjsu.seekers.starbucks.dao.AddressDAO;
import edu.sjsu.seekers.starbucks.dao.PaymentCardDetailsDAO;
import edu.sjsu.seekers.starbucks.dao.UserDAO;
import edu.sjsu.seekers.starbucks.model.Address;
import edu.sjsu.seekers.starbucks.model.PaymentCardDetails;
import edu.sjsu.seekers.starbucks.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("unit-test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class PaymentCardDetailsDAOTest {

    @Autowired
    PaymentCardDetailsDAO paymentCardDetailsDAO;

    @Autowired
    UserDAO userDAO;

    @Autowired
    AddressDAO addressDAO;

    PaymentCardDetails paymentCardDetails;
    User user;
    Address address;

    @Before
    public void setup()
    {
        System.out.println("************running setup************");
        createTestAddress();
        createTestUser();

//        createTestPaymentCard();
    }

    private void createTestPaymentCard() {
        paymentCardDetails = new PaymentCardDetails();
        paymentCardDetails.setAddressKey(address);
        paymentCardDetails.setCardNumber("123456789098765");
        paymentCardDetails.setCcvCode("123");
        paymentCardDetails.setExpirationMonth("12");
        paymentCardDetails.setExpirationYear("2019");
        paymentCardDetails.setIsActiverCard("Y");
        paymentCardDetails.setIsDefaultpaymentcardKey("Y");
        paymentCardDetails.setUserKey(user);
        paymentCardDetailsDAO.save(paymentCardDetails);
    }
    private void createTestAddress() {
        address = new Address();
        address.setAddressLine1("address line 1");
        address.setAddressLine2("address line 2");
        address.setCity("testCity");
        address.setCountry("testCountry");
        address.setState("testState");
        address.setZipCode(12345);
        addressDAO.save(address);
    }

    private void createTestUser() {
        user= new User();
        user.setUserName("testuser1");
        user.setDateOfBirth("1999-07-09");
        user.setEmailId("testuser1@testuser1.com");
        user.setFullName("test user");
        user.setDefaultStoreKey(1);
        user.setAddressKey(address);
        user.setIdActiveCustomer("1");
        user.setIsLoggedIn("1");
        user.setPassword("testPassword");
        user.setPhoneNumber("1234567890");
        user.setRewardPoints(0.0);
        user.setVerificationCode("1234");
        userDAO.save(user);
    }

    @Test
    public void paymentCardDetailscreate(){
        System.out.println("************running paymentCardDetailscreate test************");
        Exception ex = null;
        try {
            paymentCardDetails = new PaymentCardDetails();
            paymentCardDetails.setAddressKey(address);
            paymentCardDetails.setCardNumber("123456789098765");
            paymentCardDetails.setCcvCode("123");
            paymentCardDetails.setExpirationMonth("12");
            paymentCardDetails.setExpirationYear("2019");
            paymentCardDetails.setIsActiverCard("Y");
            paymentCardDetails.setIsDefaultpaymentcardKey("Y");
            paymentCardDetails.setUserKey(user);
            paymentCardDetailsDAO.save(paymentCardDetails);
        } catch (Exception e) {
            System.out.println("************inside paymentCardDetailscreate catch************ " + e.getMessage());
            ex = e;
        }
        assertNull(ex);

    }

    @Test
    public void paymentCardDetailsget(){
        System.out.println("************running paymentCardDetailsget test************");
        Exception ex = null;
        try {
            createTestPaymentCard();
            Optional<PaymentCardDetails> paycard = paymentCardDetailsDAO.get(paymentCardDetails.getCardKey());
            assert paycard.isPresent();
            assert paycard.get().getCardNumber().equals("123456789098765");
            assert paycard.get().getCcvCode().equals("123");
            assert paycard.get().getExpirationMonth().equals("12");
            assert paycard.get().getExpirationYear().equals("2019");
            assert paycard.get().getIsActiverCard().equals("Y");
            assert paycard.get().getIsDefaultpaymentcardKey().equals("Y");
            assert paycard.get().getUserKey().getUserKey().equals(user.getUserKey());


        } catch (Exception e) {
            System.out.println("************inside paymentCardDetailsget catch************ " + e.getMessage());
            ex = e;
        }
        assertNull(ex);

    }

    @Test
    public void paymentCardDetailsupdate(){
        System.out.println("************running paymentCardDetailsupdate test************");
        Exception ex = null;
        try {
            createTestPaymentCard();
            paymentCardDetails.setCcvCode("000");

            paymentCardDetailsDAO.save(paymentCardDetails);


        } catch (Exception e) {
            System.out.println("************inside paymentCardDetailsupdate catch************ " + e.getMessage());
            ex = e;
        }
        assertNull(ex);

    }

    @Test
    public void paymentCardDetailsdelete(){
        System.out.println("************running paymentCardDetailsdelete test************");
        Exception ex = null;
        try {
            createTestPaymentCard();

            paymentCardDetailsDAO.delete(paymentCardDetails);


        } catch (Exception e) {
            System.out.println("************inside paymentCardDetailsdelete catch************ " + e.getMessage());
            ex = e;
        }
        assertNull(ex);

    }


}
