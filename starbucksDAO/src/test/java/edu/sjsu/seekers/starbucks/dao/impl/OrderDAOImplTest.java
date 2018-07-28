package edu.sjsu.seekers.starbucks.dao.impl;

import edu.sjsu.seekers.starbucks.dao.*;
import edu.sjsu.seekers.starbucks.dao.repository.AddressRepository;
import edu.sjsu.seekers.starbucks.dao.repository.SizeRepository;
import edu.sjsu.seekers.starbucks.dao.repository.StoresRepository;
import edu.sjsu.seekers.starbucks.model.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("unit-test")
public class OrderDAOImplTest {

    @Autowired
    OrderDAO orderDAO;


    @Autowired
    StoresDAO storesDAO;

    @Autowired
    UserDAO userDAO;

    @Autowired
    AddressDAO addressDAO;

    @Autowired
    SizeRepository sizeRepository;

    @Autowired
    StoresRepository storesRepository;

    @Autowired
    PaymentCardDetailsDAO paymentCardDetailsDAO;

    @Autowired
    AddressRepository addressRepository;

    Orders order,order1;
    User user;
    Address address;
    Stores stores;
    Size sizeSmall,sizeMedium,sizeLarge;
    PaymentCardDetails paymentCardDetails;


    @After
    public void endTest()
    {
        System.out.println("************running setup for OrderDAOImplTest************");

        if(order != null)
            orderDAO.delete(order);
        if(order1 != null)
            orderDAO.delete(order1);

        System.out.println ( "******----->>>>>>  " + stores);
        if(stores != null)
            storesRepository.delete(stores);
        if(paymentCardDetails != null)
            paymentCardDetailsDAO.delete(paymentCardDetails);
        if(user != null)
            userDAO.delete(user);
        if(address != null)
            addressRepository.delete(address);


    }

    @Before
    public void setup() {
        System.out.println("************running endTest************");
        createTestSizes();
        createTestAddress();
        createTestUser();
        createTestPaymentCard();
        createTestStore();
        createTestOrder();
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

    private void createTestSizes() {
        sizeSmall = new Size();
        sizeSmall.setSizeName("Small");
        sizeSmall = sizeRepository.save(sizeSmall);

        sizeMedium = new Size();
        sizeMedium.setSizeName("Medium");
        sizeMedium = sizeRepository.save(sizeMedium);

        sizeLarge = new Size();
        sizeLarge.setSizeName("Large");
        sizeLarge = sizeRepository.save(sizeLarge);
    }

    private void createTestStore() {
        stores = new Stores();
        stores.setAddressKey(address);
        stores.setStoreName("test store");
        stores.setStoreOwner("test owner");
        stores = storesRepository.save(stores);
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
        user.setVerificationCode("");
        userDAO.save(user);
    }

    private void createTestOrder() {
        order = new Orders();
        order.setUserKey(user);
        order.setStoreKey(stores);
        order.setOrderAmount(12.12);
        order.setOrderDate(new Date());
        order.setOrderStatus("InProgress");
        orderDAO.save(order);

        order1 = new Orders();
        order1.setUserKey(user);
        order1.setStoreKey(stores);
        order1.setOrderAmount(12.12);
        order1.setOrderDate(new Date());
        order1.setOrderStatus("InProgress");
        orderDAO.save(order1);
    }


    @Test
    public void getOrder() throws SQLException {
       System.out.println("************running getOrder test************");
       Orders ordGet = orderDAO.findOrdersByUserKey(1).get(0);
       assertNotNull(ordGet);
       assertEquals(java.util.Optional.of(12.12).get(),ordGet.getOrderAmount());
       assertNotNull(ordGet.getUserKey());
       assertNotNull(ordGet.getStoreKey());
    }

    @Test
    public void updateOrder() throws SQLException {
        System.out.println("************running updateOrder test************");
        Exception ex = null;
        try {
            Orders ordUpdate = orderDAO.findOrdersByUserKey(1).get(0);
            ordUpdate.setRewardsEarned(1.1);
            orderDAO.save(ordUpdate);
        }catch(Exception e)
        {
            System.out.println("************inside updateOrder catch************ " + e.getMessage());
            ex = e;
        }
        assertNull(ex);
    }






    @Test
    public void findOrdersByUserKey() {
        System.out.println("************running findOrdersByUserKey test************");
        Exception ex = null;
        try {
            orderDAO.findOrdersByUserKey(1);
        }catch(Exception e)
        {
            System.out.println("************inside findOrdersByUserKey catch************");
            ex = e;
        }
        assertNull(ex);
    }
}