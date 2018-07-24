package edu.sjsu.seekers.OrderAPI.service.impl;

import edu.sjsu.seekers.OrderAPI.response.GenericResponse;
import edu.sjsu.seekers.OrderAPI.response.ProductResponse;
import edu.sjsu.seekers.OrderAPI.response.ProductsResponse;
import edu.sjsu.seekers.OrderAPI.response.StoresResponse;
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
    public List<Products> getAllActiveProducts() {
        return productDAO.getAllActiveProducts();
    }

    @Override
    public StoresResponse getAllStoresResponse() {
        StoresResponse storeResponse = new StoresResponse();
        try {
            List<Stores> storeList = getAllStores();
            if (storeList.size() > 0) {
                storeResponse.setStoresList(storeList);
                storeResponse.setStatusCode(HttpStatus.OK.toString());
                storeResponse.setFinalMessage();
            } else {
                storeResponse.setMessage("No Stores found");
                storeResponse.setStatusCode(HttpStatus.OK.toString());
            }

        } catch (Exception ex) {
            storeResponse.setMessage("Sorry, this request Cannot be completed at this time. Apologies for the inconvenience");
            storeResponse.setStatusCode(HttpStatus.EXPECTATION_FAILED.toString());
        }
        return storeResponse;
    }

    @Override
    public Optional<User> findUserByUsername(String userName) {
        return userDAO.findUserByUsername(userName);
    }

    @Override
    public ProductResponse getSpecificProduct(String name) {

        ProductResponse productResponse = new ProductResponse();
        try {
            Products productOptional = productDAO.getProductByProductName(name);
            if (productOptional != null) {
                List<ProductCatalog> productCatalogs = productCatalogDAO.getAllForProductByProductID(productOptional.getProductKey());
                productResponse.setProduct(productOptional);
                productResponse.setProductCatalog(productCatalogs);
                productResponse.setStatusCode(HttpStatus.OK.toString());
                productResponse.setFinalMessage();
            } else {
                productResponse.setMessage("Requested product not found");
                productResponse.setStatusCode(HttpStatus.OK.toString());
            }

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

    @Override
    public ProductsResponse getAllProductsResponse() {
        ProductsResponse productsResponse = new ProductsResponse();
        try {
            List<Products> prdList = getAllActiveProducts();
            if (prdList.size() > 0) {
                productsResponse.setProduct(prdList);
                productsResponse.setStatusCode(HttpStatus.OK.toString());
                productsResponse.setFinalMessage();
            } else
                productsResponse.setStatusCode(HttpStatus.OK.toString());

        } catch (Exception ex) {
            productsResponse.setStatusCode(HttpStatus.EXPECTATION_FAILED.toString());
        }
        return productsResponse;
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


