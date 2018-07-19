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

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {StarbucksApplication.class})
@ActiveProfiles("unit-test")
public class OrderDAOImplTest {

    @Autowired
    OrderDAOImpl orderDAOImpl;

    Orders order;

    @Before
    public void setup()
    {
        order = new Orders();
        order.setCardKey(1);
        order.setUserKey(1);
        order.setStoreKey(1);
        order.setOrderAmount(12.12);
        order.setOrderDate(new Date());
        order.setOrderStatus("Approved");
        orderDAOImpl.save(order);

    }


    @Test
    public void save() throws SQLException {
        Exception ex = null;
        order = new Orders();
        order.setCardKey(2);
        order.setUserKey(1);
        order.setStoreKey(2);
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