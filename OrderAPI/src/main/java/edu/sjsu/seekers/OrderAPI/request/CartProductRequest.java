package edu.sjsu.seekers.OrderAPI.request;

import java.io.Serializable;
import java.util.Map;

public class CartProductRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    String size = "";
    int quantity = 0;
    String toppings = "";

    public String getToppings() {
        return toppings;
    }

    public void setToppings(String toppings) {
        this.toppings = toppings;
    }



    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    @Override
    public String toString() {
        return "CartProductRequest{" +
                "size='" + size + '\'' +
                ", quantity=" + quantity +
                ", toppings='" + toppings + '\'' +
                '}';
    }
}
