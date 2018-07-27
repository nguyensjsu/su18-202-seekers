package edu.sjsu.seekers.OrderAPI.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import edu.sjsu.seekers.OrderAPI.request.CartProductEditRequest;
import edu.sjsu.seekers.OrderAPI.request.CartProductRequest;
import edu.sjsu.seekers.OrderAPI.response.*;
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
    public GenericResponse addToCart(Map<String,CartProductRequest> productNames, User user);
    public GenericResponse editCart(Map<Integer,CartProductEditRequest> productNames, User user);
    public GenericResponse addStoreToCart(String storeName, User user);
    public ReviewCartResponse reviewCartResponse(User user);

}
