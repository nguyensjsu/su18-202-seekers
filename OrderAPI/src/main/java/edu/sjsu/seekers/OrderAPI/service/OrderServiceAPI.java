package edu.sjsu.seekers.OrderAPI.service;

import edu.sjsu.seekers.OrderAPI.request.CartProductRequest;
import edu.sjsu.seekers.OrderAPI.response.GenericResponse;
import edu.sjsu.seekers.OrderAPI.response.ProductResponse;
import edu.sjsu.seekers.OrderAPI.response.ProductsResponse;
import edu.sjsu.seekers.OrderAPI.response.StoresResponse;
import edu.sjsu.seekers.starbucks.model.Orders;
import edu.sjsu.seekers.starbucks.model.Products;
import edu.sjsu.seekers.starbucks.model.Stores;
import edu.sjsu.seekers.starbucks.model.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface OrderServiceAPI {

    public Optional<Orders> getInprogressOrder(int userKey);
    public Orders createInprogressOrder(Orders orders);
    public List<Stores> getAllStores();
    public List<Products> getAllProducts();
    public ProductResponse getSpecificProduct(String name);
    public User getUser(int userId);
    public Orders saveOrder(Orders orders);
    public ProductsResponse getAllProductsResponse();
    public List<Products> getAllActiveProducts();
    public StoresResponse getAllStoresResponse();
    public Optional<User> findUserByUsername(String userName);
    public GenericResponse addToCart(Map<String,CartProductRequest> productNames, User userKey);
}
