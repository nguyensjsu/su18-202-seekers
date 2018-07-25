package edu.sjsu.seekers.starbucks.dao;

import edu.sjsu.seekers.starbucks.model.OrderDetails;
import edu.sjsu.seekers.starbucks.model.Orders;

import java.util.List;
import java.util.Optional;

public interface OrderDetailsDAO {
    Optional<OrderDetails> get(Integer id);
    void save(OrderDetails orderLineItems);
    void delete(OrderDetails orderDetails);
    List<OrderDetails> getAllOrderDetailsByOrderId(Integer orderId);
}
