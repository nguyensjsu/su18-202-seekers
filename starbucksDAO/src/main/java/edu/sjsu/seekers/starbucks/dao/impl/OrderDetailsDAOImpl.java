package edu.sjsu.seekers.starbucks.dao.impl;

import edu.sjsu.seekers.starbucks.dao.OrderDetailsDAO;
import edu.sjsu.seekers.starbucks.dao.repository.OrderDetailsRepository;
import edu.sjsu.seekers.starbucks.model.order_details;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;

@Configuration
public class OrderDetailsDAOImpl implements OrderDetailsDAO {
    @Autowired
    OrderDetailsRepository orderDetailsRepository;

    @Override
    public Optional<order_details> get(Integer id) {
        Optional<order_details> orderDetails;
        orderDetails =orderDetailsRepository.findById(1);
        return orderDetails;
    }

    @Override
    public void save(order_details ordersLineItems)
    {
        orderDetailsRepository.save(ordersLineItems);
    }
}
