package edu.sjsu.seekers.OrderAPI.response;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import edu.sjsu.seekers.starbucks.model.OrderDetails;
import edu.sjsu.seekers.starbucks.model.Products;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReviewCartResponse extends GenericResponse {



    public ReviewCartResponse() {

    }


    private static final long serialVersionUID = 1L;

    @JsonIgnore
    public List<OrderDetails> getCartList() {
        return cartList;
    }

    public void setCartList(List<OrderDetails> cartList) {
        this.cartList = cartList;
    }

    @JsonIgnore
    private List<OrderDetails> cartList;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }



    public void setFinalMessage() {


        Map<Integer,Object> outputCartsMap = new HashMap<>();
        Map<String,Object> cartDescMap = new HashMap<>();


        for(OrderDetails orderDetails : cartList) {
            cartDescMap = new HashMap<>();
            cartDescMap.put("Product Name",orderDetails.getProductKey().getProductName());
            cartDescMap.put("Product Description",orderDetails.getProductKey().getProductDescription());
            cartDescMap.put("Product ImageLink",orderDetails.getProductKey().getProductImageLink());
            cartDescMap.put("Quantity",orderDetails.getOrderQuantity());
            cartDescMap.put("Price",orderDetails.getNetPrice());
            cartDescMap.put("Size",orderDetails.getSizeKey().getSizeName());
            cartDescMap.put("Item Id",orderDetails.getOrderLineKey());
            if(orderDetails.getToppings() != null && !orderDetails.getToppings().equals(""))
                cartDescMap.put("Toppings",orderDetails.getToppings());
            outputCartsMap.put(orderDetails.getOrderLineKey(),cartDescMap);
        }

        setMessage(outputCartsMap);
    }

    @Override
    public String toString() {
        return "ReviewCartResponse{" +
                "cartList=" + cartList +
                '}';
    }
}
