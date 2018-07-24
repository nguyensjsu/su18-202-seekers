package edu.sjsu.seekers.OrderAPI.request;

public class ProductsRequest {

    private static final long serialVersionUID = 1L;


    String userName;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "ProductRequest {userName='" + userName + '\'' + '}';
    }
}
