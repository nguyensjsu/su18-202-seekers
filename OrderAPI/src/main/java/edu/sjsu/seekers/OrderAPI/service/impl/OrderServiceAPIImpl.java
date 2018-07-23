package edu.sjsu.seekers.OrderAPI.service.impl;

import edu.sjsu.seekers.OrderAPI.response.ProductResponse;
import edu.sjsu.seekers.OrderAPI.service.OrderServiceAPI;
import edu.sjsu.seekers.starbucks.dao.*;
import edu.sjsu.seekers.starbucks.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceAPIImpl implements OrderServiceAPI {

    @Autowired
    OrderDAO orderDAOImpl;

    @Autowired
    OrderDetailsDAO orderDetailsDAOImpl;

    @Autowired
    UserDAO userDAO;

    @Autowired
    StoresDAO storesDAO;

    @Autowired
    ProductDAO productDAO;

    @Autowired
    ProductCatalogDAO productCatalogDAO;

    @Override
    public List<Orders> getInprogressOrder(int userKey) {
        return orderDAOImpl.findIncompleteOrdersByUserKey(userKey);
    }

    @Override
    public Orders createInprogressOrder(Orders orders) {

        return orderDAOImpl.save(orders);
    }

    @Override
    public List<Stores> getAllStores() {
        return storesDAO.getAll();
    }

    @Override
    public List<Products> getAllProducts() {
        return productDAO.getAll();
    }

    @Override
    public ProductResponse getSpecificProduct(Integer id) {

        ProductResponse productResponse = new ProductResponse();
        try {
            Optional<Products> productOptional = productDAO.get(id);
            if (productOptional.isPresent()) {
                Products prd = productOptional.get();
                List<ProductCatalog> productCatalogs = productCatalogDAO.getAllForProductByProductID(id);
                productResponse.setProduct(prd);
                productResponse.setProductCatalog(productCatalogs);
                productResponse.setStatusCode(HttpStatus.OK.toString());
                productResponse.setFinalMessage();
            } else
                productResponse.setStatusCode(HttpStatus.OK.toString());

        } catch (Exception ex) {
            productResponse.setStatusCode(HttpStatus.EXPECTATION_FAILED.toString());
        }
        return productResponse;
    }


    @Override
    public User getUser(int userId) {
        Optional<User> userOptional = userDAO.get(userId);
        if (userOptional.isPresent())
            return userOptional.get();
        else
            return null;
    }

    @Override
    public Orders saveOrder(Orders orders) {
        return orderDAOImpl.save(orders);
    }

    public String addProductToCart(Orders ord, OrderDetails orderDetails, int userKey) {
        try {
            List<Orders> orders = getInprogressOrder(userKey);
            Orders currentOrder = null;
            if (orders.size() > 0) {
                currentOrder = orders.get(orders.size() - 1);
            } else {
                currentOrder = saveOrder(ord);
            }

            orderDetails.setOrderKey(currentOrder);
            orderDetailsDAOImpl.save(orderDetails);
        } catch (Exception ex) {
            return ex.getMessage();
        }
        return "success";
    }
}


