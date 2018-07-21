package edu.sjsu.seekers.starbucks.dao.impl;

import edu.sjsu.seekers.starbucks.dao.OrderDAO;
import edu.sjsu.seekers.starbucks.dao.repository.OrderRepository;
import edu.sjsu.seekers.starbucks.model.Orders;
import edu.sjsu.seekers.starbucks.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Optional;

@Configuration
public class OrderDAOImpl implements OrderDAO {

    @Autowired
    OrderRepository orderRepository;

    @Override
    public Optional<Orders> get(Integer id) {

        Optional<Orders> order;
        order = orderRepository.findById(1);
        return order;
    }

    @Override
    public Orders save(Orders orders) {
        return orderRepository.save(orders);
    }

    @Override
    public List<Orders> findOrdersByUserKey(Integer userKey) {
        return orderRepository.findByUserKey(userKey);
    }

    @Override
    public void update(Orders orders) {
        orderRepository.save(orders);
    }


}
