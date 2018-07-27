package edu.sjsu.seekers.starbucks.dao;

import edu.sjsu.seekers.starbucks.model.Orders;

import java.util.List;
import java.util.Optional;

public interface OrderDAO {
    public Optional<Orders> get(Integer id);
    public Orders save(Orders orders);
    public List<Orders> findOrdersByUserKey(Integer userKey);
    public Optional<Orders> findIncompleteOrdersByUserKey(Integer userKey);
    public void update(Orders orders);
    public void delete(Orders orders);
}
