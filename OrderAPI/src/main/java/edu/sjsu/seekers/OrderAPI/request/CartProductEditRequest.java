package edu.sjsu.seekers.OrderAPI.request;

import java.io.Serializable;

public class CartProductEditRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    int quantity = 0;



    public static long getSerialVersionUID() {
        return serialVersionUID;
    }



    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    @Override
    public String toString() {
        return "CartProductEditRequest{" +
                "quantity=" + quantity +
                '}';
    }
}
