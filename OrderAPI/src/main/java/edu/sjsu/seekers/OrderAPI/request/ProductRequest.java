package edu.sjsu.seekers.OrderAPI.request;

import java.io.Serializable;

public class ProductRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    String name;
    String userName;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "ProductRequest{" +
                "name=" + name +
                ", userName=" + userName +
                '}';
    }
}
