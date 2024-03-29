package edu.sjsu.seekers.starbucks.dao.impl;

import edu.sjsu.seekers.starbucks.dao.*;
import edu.sjsu.seekers.starbucks.dao.repository.ProductRepository;
import edu.sjsu.seekers.starbucks.dao.repository.SizeRepository;
import edu.sjsu.seekers.starbucks.dao.repository.StoresRepository;
import edu.sjsu.seekers.starbucks.model.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.Optional;

import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("unit-test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class PaymentDetailsDAOTest {

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
    OrderDetailsDAO orderDetailsDAO;

    @Autowired
    PaymentDetailsDAO paymentDetailsDAO;

    @Autowired
    ProductRepository productRepository;

    Orders order, order1;
    User user;
    Address address;
    Stores stores;
    OrderDetails orderDetails;
    Size sizeSmall, sizeMedium, sizeLarge;
    PaymentCardDetails paymentCardDetails;
    PaymentDetails paymentDetailsResponse;
    Products products;
    @Before
    public void setup() {
        System.out.println("************running setup************");
        createTestSizes();
        createTestAddress();
        createTestUser();
        createTestPaymentCard();
        createTestStore();
        createTestOrder();
        createTestProduct();
        createTestOrderDetails();
    }

    private void createTestProduct() {
        products = new Products();
        products.setActive(true);
        products.setProductDescription("test description");
        products.setProductImageLink("test link");
        products.setProductName("test product");
        products.setProductStatus(true);
        productRepository.save(products);
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
        user = new User();
        user.setUserName("testuser1");
        user.setDateOfBirth("1999-07-09");
        user.setEmailId("testuser1@testuser1.com");
        user.setFullName("test user");
        user.setDefaultStoreKey(1);
        user.setAddressKey(address);
        user.setIdActiveCustomer("Y");
        user.setIsLoggedIn("Y");
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
        order.setOrderStatus("Complete");
        orderDAO.save(order);
    }

    private void createTestOrderDetails() {
        orderDetails = new OrderDetails();
        orderDetails.setSizeKey(sizeLarge);
        orderDetails.setOrderQuantity(1);
        orderDetails.setNetPrice(2.55);
        orderDetails.setToppings("");
        orderDetails.setOrderKey(order);
        orderDetails.setProductKey(products);
        orderDetailsDAO.save(orderDetails);
    }

    private void createPaymentDetails() {
        PaymentDetails paymentDetails = new PaymentDetails();
        paymentDetails.setOrderKey(order);
        paymentDetails.setPaymentStatus("SUCCESS");
        paymentDetailsResponse = paymentDetailsDAO.save(paymentDetails);
    }


    @Test
    public void paymentDetailsAddLine() {
        System.out.println("************running paymentDetailsAddLine test************");
        Exception ex = null;
        try {
            PaymentDetails paymentDetails = new PaymentDetails();
            paymentDetails.setOrderKey(order);
            paymentDetails.setPaymentStatus("SUCCESS");
            PaymentDetails paymentDetailsResponse = paymentDetailsDAO.save(paymentDetails);

            assert paymentDetailsResponse.getPaymentStatus().equals("SUCCESS");
        } catch (Exception e) {
            System.out.println("************inside paymentDetailsAddLine catch************ " + e.getMessage());
            ex = e;
        }
        assertNull(ex);
    }

    @Test
    public void paymentDetailsGetLine() {
        System.out.println("************running paymentDetailsGetLine test************");
        Exception ex = null;
        try {
            createPaymentDetails();
            Optional<PaymentDetails> response = paymentDetailsDAO.get(paymentDetailsResponse.getPaymentId());


            assert response.get().getPaymentStatus().equals("SUCCESS");
            assert response.get().getOrderKey().getOrderKey().equals(paymentDetailsResponse.getOrderKey().getOrderKey());
        } catch (Exception e) {
            System.out.println("************inside paymentDetailsGetLine catch************ " + e.getMessage());
            ex = e;
        }
        assertNull(ex);
    }


}
