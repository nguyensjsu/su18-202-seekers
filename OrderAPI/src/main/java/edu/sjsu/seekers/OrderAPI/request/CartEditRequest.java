package edu.sjsu.seekers.OrderAPI.request;

import java.io.Serializable;
import java.util.Map;

public class CartEditRequest implements Serializable {

    private static final long serialVersionUID = 1L;


    Map<Integer,CartProductEditRequest> productDetails;
    String userName;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Map<Integer, CartProductEditRequest> getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(Map<Integer, CartProductEditRequest> productDetails) {
        this.productDetails = productDetails;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    @Override
    public String toString() {
        return "CartRequest{" +
                "productName=" + productDetails +
                ", userName=" + userName +
                '}';
    }
}
