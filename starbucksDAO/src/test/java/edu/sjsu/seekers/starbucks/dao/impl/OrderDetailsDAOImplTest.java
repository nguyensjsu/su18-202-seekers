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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("unit-test")
public class OrderDetailsDAOImplTest {


    @Autowired
    OrderDAO orderDAO;

    @Autowired
    OrderDetailsDAO orderDetailsDAO;

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
    ProductRepository productRepository;

    Orders order,order1;
    User user;
    Address address;
    Stores stores;
    OrderDetails orderDetails;
    Size sizeSmall,sizeMedium,sizeLarge;
    PaymentCardDetails paymentCardDetails;
    Products products;

    @Before
    public void setup()
    {
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

    private void createTestProduct()
    {
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

    @Test
    public void listOrderDetails() throws SQLException {
        System.out.println("************running listOrderDetails test************");
        int user_idx = userDAO.getUserKey("testuser1");

        Orders ordGet = orderDAO.findOrdersByUserKey(user_idx).get(0);
        List<OrderDetails> orderDetailsGetList = orderDetailsDAO.getAllOrderDetailsByOrderId(ordGet.getOrderKey());
        assertNotNull(orderDetailsGetList);
        assertEquals(1,orderDetailsGetList.size());
        assertEquals(java.util.Optional.of(2.55).get(),orderDetailsGetList.get(0).getNetPrice());
        assertEquals(java.util.Optional.of(1).get(),orderDetailsGetList.get(0).getOrderQuantity());

    }

    @Test
    public void deleteOrderDetails() throws SQLException {
        System.out.println("************running deleteOrderDetails test************");
        Exception ex = null;
        try {
            orderDetailsDAO.delete(orderDetails);
        }catch(Exception e)
        {
            System.out.println("************inside deleteOrderDetails catch************ " + e.getMessage());
            ex = e;
        }
        assertNull(ex);
    }

    @Test
    public void modifyOrderDetails() {
        System.out.println("************running modifyOrderDetails test************");
        Exception ex = null;
        try {
            Orders ordGet = orderDAO.findOrdersByUserKey(1).get(0);
            if(orderDetailsDAO.getAllOrderDetailsByOrderId(ordGet.getOrderKey()).size() == 0)
                createTestOrderDetails();
            List<OrderDetails> orderDetailsGetList = orderDetailsDAO.getAllOrderDetailsByOrderId(ordGet.getOrderKey());
            OrderDetails orderModify = orderDetailsGetList.get(0);
            orderModify.setToppings("test toppings");
            orderDetailsDAO.save(orderModify);
        }catch(Exception e)
        {
            System.out.println("************inside modifyOrderDetails catch************");
            ex = e;
        }
        assertNull(ex);
    }

}