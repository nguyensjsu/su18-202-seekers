package edu.sjsu.seekers.OrderAPI.request;

import java.io.Serializable;

public class StoreRequest implements Serializable {

    private static final long serialVersionUID = 1L;



    String storeName;
    String userName;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "StoreRequest{" +
                "storeName='" + storeName + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
