package edu.sjsu.seekers.OrderAPI.service;

import edu.sjsu.seekers.OrderAPI.response.ProductResponse;
import edu.sjsu.seekers.starbucks.model.Orders;
import edu.sjsu.seekers.starbucks.model.Products;
import edu.sjsu.seekers.starbucks.model.Stores;
import edu.sjsu.seekers.starbucks.model.User;

import java.util.List;

public interface OrderServiceAPI {

    public List<Orders> getInprogressOrder(int userKey);
    public Orders createInprogressOrder(Orders orders);
    public List<Stores> getAllStores();
    public List<Products> getAllProducts();
    public ProductResponse getSpecificProduct(Integer id);
    public User getUser(int userId);
    public Orders saveOrder(Orders orders);
}
