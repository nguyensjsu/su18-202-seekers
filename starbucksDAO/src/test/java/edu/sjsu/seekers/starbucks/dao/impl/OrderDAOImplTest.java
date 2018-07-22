package edu.sjsu.seekers.starbucks.dao.impl;

import edu.sjsu.seekers.starbucks.StarbucksApplication;
import edu.sjsu.seekers.starbucks.model.Orders;
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

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("unit-test")
public class OrderDAOImplTest {

    @Autowired
    OrderDAOImpl orderDAOImpl;

    @Autowired
    PaymentCardDetailsDAOImpl paymentCardDetailsDAOImpl;

    @Autowired
    UserDAOImpl userDAOImpl;

    @Autowired
    StoresDAOImpl storDAOImpl;

    Orders order;

    @Before
    public void setup()
    {
        order = new Orders();
        order.setCardKey(paymentCardDetailsDAOImpl.get(1).get());
        order.setUserKey(userDAOImpl.get(1).get());
        order.setStoreKey(storDAOImpl.get(1).get());
        order.setOrderAmount(12.12);
        order.setOrderDate(new Date());
        order.setOrderStatus("Approved");
        orderDAOImpl.save(order);

    }


    @Test
    public void save() throws SQLException {
        Exception ex = null;
        order = new Orders();
        order.setCardKey(paymentCardDetailsDAOImpl.get(2).get());
        order.setUserKey(userDAOImpl.get(2).get());
        order.setStoreKey(storDAOImpl.get(1).get());
        order.setOrderAmount(22.44);
        order.setOrderDate(new Date());
        order.setOrderStatus("Approved");
        try {
            List<Orders> orders;
            orderDAOImpl.save(order);
        }
        catch (Exception e){
            ex = e;
        }
        assertEquals(null,ex);
    }

    @Test
    public void get() throws SQLException {
        Exception ex = null;
        try {
            List<Orders> orders;
            orders = orderDAOImpl.findOrdersByUserKey(1);

            for (Orders ord: orders) {
                System.out.println("test order: " + ord.toString());
            }
        }
        catch (Exception e){
            ex = e;
        }
        assertEquals(null,ex);
    }
}