package edu.sjsu.seekers.starbucks.dao.impl;

import edu.sjsu.seekers.starbucks.dao.OrderDetailsDAO;
import edu.sjsu.seekers.starbucks.dao.repository.OrderDetailsRepository;
import edu.sjsu.seekers.starbucks.model.OrderDetails;
import edu.sjsu.seekers.starbucks.model.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Optional;

@Configuration
public class OrderDetailsDAOImpl implements OrderDetailsDAO {
    @Autowired
    OrderDetailsRepository orderDetailsRepository;

    @Override
    public Optional<OrderDetails> get(Integer id) {
        Optional<OrderDetails> orderDetails;
        orderDetails =orderDetailsRepository.findById(id);
        return orderDetails;
    }

    @Override
    public void save(OrderDetails ordersLineItems)
    {
        orderDetailsRepository.save(ordersLineItems);
    }

    @Override
    public void deleteOrderDetailsforOrder(Integer orderKey) {
        orderDetailsRepository.deleteOrderDetailsforOrder(orderKey);
    }
  
    public void delete(OrderDetails orderDetails) {
        orderDetailsRepository.delete(orderDetails);
    }

    @Override
    public List<OrderDetails> getAllOrderDetailsByOrderId(Integer orderId) {
        return orderDetailsRepository.getAllOrderDetailsByOrderId(orderId);
    }
}
