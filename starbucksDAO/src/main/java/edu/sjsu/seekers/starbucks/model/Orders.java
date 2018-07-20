package edu.sjsu.seekers.starbucks.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="Order_Key")
    private Integer orderKey;

    @Column(name="Card_Key")
    private Integer cardKey;

    @Column(name="User_Key")
    private Integer userKey;

    @Column(name="Store_Key")
    private Integer storeKey;

    @Column(name="Order_Status")
    private String orderStatus;

    @Column(name="Order_Amount")
    private Double orderAmount;

    @Column(name="Order_Date")
    private Date orderDate;

    public Integer getOrderKey() {
        return orderKey;
    }

    public void setOrderKey(Integer orderKey) {
        this.orderKey = orderKey;
    }

    public Integer getCardKey() {
        return cardKey;
    }

    public void setCardKey(Integer cardKey) {
        this.cardKey = cardKey;
    }

    public Integer getUserKey() {
        return userKey;
    }

    public void setUserKey(Integer userKey) {
        this.userKey = userKey;
    }

    public Integer getStoreKey() {
        return storeKey;
    }

    public void setStoreKey(Integer storeKey) {
        this.storeKey = storeKey;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Double getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Double orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "orderKey=" + orderKey +
                ", cardKey='" + cardKey + '\'' +
                ", userKey='" + userKey + '\'' +
                ", storeKey='" + storeKey + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                ", orderAmount=" + orderAmount +
                ", orderDate=" + orderDate +
                '}';
    }
}


