package edu.sjsu.seekers.OrderAPI.request;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class CartRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    Map<String,CartProductRequest> productDetails;
    String userName;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }


    public Map<String,CartProductRequest> getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(Map<String,CartProductRequest> productDetails) {
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
