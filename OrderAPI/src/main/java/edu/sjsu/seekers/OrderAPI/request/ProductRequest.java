package edu.sjsu.seekers.OrderAPI.request;

import java.io.Serializable;

public class ProductRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    int id;
    String userName;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
                "id=" + id +
                ", userName='" + userName + '\'' +
                '}';
    }
}
