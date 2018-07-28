package edu.sjsu.seekers.OrderAPI.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import edu.sjsu.seekers.OrderAPI.request.CartProductEditRequest;
import edu.sjsu.seekers.OrderAPI.request.CartProductRequest;
import edu.sjsu.seekers.OrderAPI.response.*;
import edu.sjsu.seekers.OrderAPI.service.OrderServiceAPI;
import edu.sjsu.seekers.starbucks.dao.*;
import edu.sjsu.seekers.starbucks.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

import static edu.sjsu.seekers.OrderAPI.utils.Constants.TOPPINGS_SIZE_NAME;

@Service
public class OrderServiceAPIImpl implements OrderServiceAPI {

    @Autowired
    OrderDAO orderDAO;

    @Autowired
    OrderDetailsDAO orderDetailsDAO;

    @Autowired
    UserDAO userDAO;

    @Autowired
    StoresDAO storesDAO;

    @Autowired
    ProductDAO productDAO;

    @Autowired
    ProductCatalogDAO productCatalogDAO;

    @Autowired
    SizeDAO sizeDAO;


    @Override
    public Optional<Orders> getInprogressOrder(int userKey) {
        return orderDAO.findIncompleteOrdersByUserKey(userKey);
    }

    @Override
    public Orders createInprogressOrder(Orders orders) {

        return orderDAO.save(orders);
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
            Products productOptional = getProductByProductName(name);
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
        return orderDAO.save(orders);
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

    public GenericResponse addToCart(Map<String,CartProductRequest> productDetails, User user)
    {

        GenericResponse genericResponse = new GenericResponse();
        boolean invalidTransaction = false;
        Orders currentOrder = null;
        Optional<Orders> orders = getInprogressOrder(user.getUserKey());

        if (orders.isPresent()) {
            System.out.println("user: " + user.getUserName() + " has 1 active carts");
            currentOrder = orders.get();
        } else {
            System.out.println("user: " + user.getUserName() + " has no active carts");
            //new cart
            Orders newOrder = new Orders();
            newOrder.setUserKey(user);
            newOrder.setOrderDate(new Date());
            newOrder.setOrderStatus("InProgress");
            newOrder.setOrderAmount(0.00);
            newOrder.setRewardsEarned(0.00);
            currentOrder = saveOrder(newOrder);
        }
            List<OrderDetails> orderDetailsList = new ArrayList<>();
            OrderDetails tempOrderDetails = new OrderDetails();
            Double productAmount = 0.0;
            Double orderAmount = 0.0;
            Double productRewards = 0.0;
            Double orderRewards = 0.0;
            int sizeId = 0;
            int quantity = 0;
            int productId = 0;
            String productName = "";
            Products tempProducts;
            Size tempSize;
            CartProductRequest tempCartProductRequest;
            ProductCatalog tempProductCatalog;
            String[] top;
            for(Map.Entry<String,CartProductRequest> mp : productDetails.entrySet())
            {
                productAmount = 0.0;
                productRewards = 0.0;
                productName = mp.getKey();
                tempCartProductRequest = mp.getValue();
                tempProducts = getProductByProductName(productName);
                if(tempProducts == null)
                {
                    genericResponse.setMessage("Invalid product: " + productName);
                    invalidTransaction = true;
                    break;
                }
                tempSize = getSizeByName(tempCartProductRequest);
                if(tempSize == null)
                {
                    genericResponse.setMessage("Invalid size: " + productName + " for product: " + productName);
                    invalidTransaction = true;
                    break;
                }
                sizeId = tempSize.getSizeKey();
                productId = tempProducts.getProductKey();
                tempProductCatalog = getProductCatalogByIdAndSize(sizeId, productId);
                if(tempProductCatalog == null)
                {
                    genericResponse.setMessage("Invalid size: " + productName + " and product: " + productName + " combination.");
                    invalidTransaction = true;
                    break;
                }
                try {
                    top = tempCartProductRequest.getToppings().split(",");
                }
                catch (Exception ex)
                {
                    genericResponse.setMessage("Formatting issue with toppings list");
                    invalidTransaction = true;
                    break;
                }
                Products tempToppingProduct;
                Double toppingsAmount = 0.0;
                Double toppingsRewards = 0.0;
                ProductCatalog temptToppingProductCatalog;
                for(String s: top)
                {
                    if(!s.equals("")) {
                        tempToppingProduct = getProductByProductName(s);
                        if (tempToppingProduct == null) {
                            genericResponse.setMessage("Invalid topping: " + s + " added on product: " + productName);
                            invalidTransaction = true;
                            break;
                        }
                        temptToppingProductCatalog = getProductCatalogByIdAndSize(sizeDAO.getSizeByName(TOPPINGS_SIZE_NAME).getSizeKey(), tempToppingProduct.getProductKey());
                        toppingsAmount += temptToppingProductCatalog.getPrice();
                        toppingsRewards += temptToppingProductCatalog.getRewards();
                    }
                }
                if(invalidTransaction)
                    break;

                productAmount = (tempProductCatalog.getPrice() * tempCartProductRequest.getQuantity()) + toppingsAmount;
                productRewards = (tempProductCatalog.getRewards() * tempCartProductRequest.getQuantity()) + toppingsRewards;
                tempOrderDetails = new OrderDetails();
                tempOrderDetails.setOrderKey(currentOrder);
                tempOrderDetails.setProductKey(tempProducts);
                tempOrderDetails.setOrderQuantity(tempCartProductRequest.getQuantity());
                tempOrderDetails.setNetPrice(productAmount);
                tempOrderDetails.setToppings(tempCartProductRequest.getToppings());
                tempOrderDetails.setSizeKey(tempSize);
                orderDetailsList.add(tempOrderDetails);
                orderAmount += productAmount;
                orderRewards += productRewards;
            }
            if(invalidTransaction) {
                deleteOrder(currentOrder);
                genericResponse.setStatusCode(HttpStatus.EXPECTATION_FAILED.toString());
            }
            else {
                for (OrderDetails orderDetails : orderDetailsList) {
                    try {
                        saveOrderDetails(orderDetails);
                    } catch (Exception ex) {
                        genericResponse.setMessage("Sorry! issue in saving order details for product");
                        genericResponse.setStatusCode(HttpStatus.EXPECTATION_FAILED.toString());
                        invalidTransaction = true;
                        break;
                    }
                }
                if(invalidTransaction)
                {
                    for (OrderDetails orderDetails : orderDetailsList) {
                        deleteOrderDetails(orderDetails);
                    }
                    deleteOrder(currentOrder);
                }
                else {
                    currentOrder.setOrderAmount(currentOrder.getOrderAmount() + orderAmount);
                    currentOrder.setRewardsEarned(currentOrder.getRewardsEarned() + orderRewards);
                    saveOrder(currentOrder);
                    genericResponse.setMessage("Products added to cart");
                    genericResponse.setStatusCode(HttpStatus.OK.toString());
                }
            }

        return genericResponse;
    }

    @Override
    public GenericResponse editCart(Map<Integer, CartProductEditRequest> productNames, User user) {
        GenericResponse genericResponse = new GenericResponse();
        boolean invalidTransaction = false;
        Orders currentOrder = null;
        Optional<Orders> orders = getInprogressOrder(user.getUserKey());

        if (orders.isPresent()) {
            System.out.println("user: " + user.getUserName() + " has 1 active carts");
            currentOrder = orders.get();
            int orderDetailsId = 0;
            OrderDetails tempOrderDetails = null;
            CartProductEditRequest tempCartProducEditRequest;
            ProductCatalog tempProductCatalog;
            for(Map.Entry<Integer,CartProductEditRequest> mp : productNames.entrySet()) {
                tempOrderDetails = null;
                orderDetailsId = mp.getKey();
                tempCartProducEditRequest = mp.getValue();
                List<OrderDetails> orderDetailsList = orderDetailsDAO.getAllOrderDetailsByOrderId(currentOrder.getOrderKey());
                for(OrderDetails od : orderDetailsList)
                {
                    if(od.getOrderLineKey() == orderDetailsId)
                    {
                        tempOrderDetails = od;
                        break;
                    }
                }
                if(tempOrderDetails == null)
                {
                    genericResponse.setMessage("Invalid item id provided");
                    genericResponse.setStatusCode(HttpStatus.EXPECTATION_FAILED.toString());
                    invalidTransaction = true;
                    break;
                }
                tempProductCatalog = getProductCatalogByIdAndSize(tempOrderDetails.getSizeKey().getSizeKey(), tempOrderDetails.getProductKey().getProductKey());
                Double perItemPrice = tempProductCatalog.getPrice();
                if(tempCartProducEditRequest.getQuantity() == 0)
                {
                    currentOrder.setRewardsEarned(currentOrder.getRewardsEarned() - (tempProductCatalog.getRewards() * tempOrderDetails.getOrderQuantity()));
                    currentOrder.setOrderAmount(currentOrder.getOrderAmount() - tempOrderDetails.getNetPrice());
                    deleteOrderDetails(tempOrderDetails);
                    currentOrder = saveOrder(currentOrder);
                    break;
                }
                if(tempCartProducEditRequest.getQuantity() < 0)
                {
                    genericResponse.setMessage("Invalid item quantity provided for item: " + orderDetailsId);
                    genericResponse.setStatusCode(HttpStatus.EXPECTATION_FAILED.toString());
                    invalidTransaction = true;
                    break;
                }
                if(tempOrderDetails.getOrderQuantity() == tempCartProducEditRequest.getQuantity())
                {
                    genericResponse.setMessage("Same quantity provided for item: " + orderDetailsId);
                    genericResponse.setStatusCode(HttpStatus.EXPECTATION_FAILED.toString());
                    invalidTransaction = true;
                    break;
                }
                Double finalAmount;
                Double oldAmount = tempOrderDetails.getNetPrice();
                if(tempOrderDetails.getOrderQuantity() > tempCartProducEditRequest.getQuantity())
                {
                    int diffQuantity = tempOrderDetails.getOrderQuantity() - tempCartProducEditRequest.getQuantity();
                    tempOrderDetails.setOrderQuantity(tempCartProducEditRequest.getQuantity());
                    finalAmount = tempOrderDetails.getNetPrice() - (perItemPrice * diffQuantity);
                    tempOrderDetails.setNetPrice(finalAmount);
                    currentOrder.setRewardsEarned(currentOrder.getRewardsEarned() - (tempProductCatalog.getRewards() * diffQuantity));
                }
                else
                {
                    int diffQuantity = tempCartProducEditRequest.getQuantity() - tempOrderDetails.getOrderQuantity();
                    tempOrderDetails.setOrderQuantity(tempCartProducEditRequest.getQuantity());
                    finalAmount = tempOrderDetails.getNetPrice() + (perItemPrice * diffQuantity);
                    tempOrderDetails.setNetPrice(finalAmount);
                    currentOrder.setRewardsEarned(currentOrder.getRewardsEarned() + (tempProductCatalog.getRewards() * diffQuantity));
                }
                tempOrderDetails.setOrderQuantity(tempCartProducEditRequest.getQuantity());
                finalAmount = finalAmount - oldAmount;
                currentOrder.setOrderAmount(currentOrder.getOrderAmount() + finalAmount);
                saveOrderDetails(tempOrderDetails);
                currentOrder = saveOrder(currentOrder);
            }
            if(!invalidTransaction) {
                genericResponse.setMessage("Cart updated successfully for user: " + user.getUserName());
                genericResponse.setStatusCode(HttpStatus.OK.toString());
            }
        }
        else
        {
            genericResponse.setMessage("No active carts for user: " + user.getUserName());
            genericResponse.setStatusCode(HttpStatus.EXPECTATION_FAILED.toString());
        }
        return genericResponse;
    }

    @Override
    public GenericResponse addStoreToCart(String storeName, User user) {
        GenericResponse genericResponse = new GenericResponse();
        Optional<Stores> store = getStoreByName(storeName);
        if(store.isPresent()) {
            Orders currentOrder = null;
            Optional<Orders> orders = getInprogressOrder(user.getUserKey());
            if (orders.isPresent()) {
                System.out.println("user: " + user.getUserName() + " has 1 active carts");
                currentOrder = orders.get();
                currentOrder.setStoreKey(store.get());
                saveOrder(currentOrder);
                genericResponse.setMessage("Store added to cart successfully!");
                genericResponse.setStatusCode(HttpStatus.OK.toString());
            } else {
                genericResponse.setMessage("No active cart for user:" + user.getUserName());
                genericResponse.setStatusCode(HttpStatus.EXPECTATION_FAILED.toString());
            }
        }
        else
        {
            genericResponse.setMessage("Invalid store name provided");
            genericResponse.setStatusCode(HttpStatus.EXPECTATION_FAILED.toString());
        }
        return genericResponse;
    }


    @Override
    public ReviewCartResponse reviewCartResponse(User user) {
        ReviewCartResponse reviewCartResponse = new ReviewCartResponse();
        Orders currentOrder = null;
        Optional<Orders> orders = getInprogressOrder(user.getUserKey());
        if (orders.isPresent()) {
            System.out.println("user: " + user.getUserName() + " has 1 active carts");
            currentOrder = orders.get();
            List<OrderDetails> orderDetailsList = orderDetailsDAO.getAllOrderDetailsByOrderId(currentOrder.getOrderKey());
            reviewCartResponse.setCartList(orderDetailsList);
            reviewCartResponse.setFinalMessage();
            reviewCartResponse.setStatusCode(HttpStatus.OK.toString());
        } else {
            System.out.println("user: " + user.getUserName() + " has no active carts");
            reviewCartResponse.setMessage("No items in cart");
            reviewCartResponse.setStatusCode(HttpStatus.EXPECTATION_FAILED.toString());
        }
        return reviewCartResponse;
    }

    private Optional<Stores> getStoreByName(String storeName) {
        return storesDAO.getStoreByName(storeName);
    }

    private ProductCatalog getProductCatalogByIdAndSize(int sizeId, int productId) {
        return productCatalogDAO.getProductCatalogByIdAndSize(productId,sizeId);
    }

    private Size getSizeByName(CartProductRequest tempCartProductRequest) {
        return sizeDAO.getSizeByName(tempCartProductRequest.getSize());
    }

    private Products getProductByProductName(String s) {
        return productDAO.getProductByProductName(s);
    }

    private void deleteOrderDetails(OrderDetails orderDetails) {
        orderDetailsDAO.delete(orderDetails);
    }

    private void deleteOrder(Orders currentOrder) {
        orderDAO.delete(currentOrder);
    }

    private void saveOrderDetails(OrderDetails orderDetails) {
        orderDetailsDAO.save(orderDetails);
    }
}


