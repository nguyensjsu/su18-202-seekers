package edu.sjsu.seekers.starbucks.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="Order_Key")
    private Integer orderKey;

    @ManyToOne
    @JoinColumn(name = "Card_Key", nullable = true)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private PaymentCardDetails cardKey;

    @ManyToOne
    @JoinColumn(name = "User_Key", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private User userKey;

    @ManyToOne
    @JoinColumn(name = "Store_Key", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Stores storeKey;

    @Column(name="Order_Status")
    private String orderStatus;

    @Column(name="Order_Amount")
    private Double orderAmount;

    @Column(name="Order_Date")
    private Date orderDate;

    @Column(name="Rewards_Earned")
    private Double rewardsEarned;

    public Double getRewardsEarned() {
        return rewardsEarned;
    }

    public void setRewardsEarned(Double rewardsEarned) {
        this.rewardsEarned = rewardsEarned;
    }

    public Integer getOrderKey() {
        return orderKey;
    }

    public void setOrderKey(Integer orderKey) {
        this.orderKey = orderKey;
    }

    public PaymentCardDetails getCardKey() {
        return cardKey;
    }

    public void setCardKey(PaymentCardDetails cardKey) {
        this.cardKey = cardKey;
    }

    public User getUserKey() {
        return userKey;
    }

    public void setUserKey(User userKey) {
        this.userKey = userKey;
    }

    public Stores getStoreKey() {
        return storeKey;
    }

    public void setStoreKey(Stores storeKey) {
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
                ", cardKey=" + cardKey +
                ", userKey=" + userKey +
                ", storeKey=" + storeKey +
                ", orderStatus='" + orderStatus + '\'' +
                ", orderAmount=" + orderAmount +
                ", orderDate=" + orderDate +
                ", rewardsEarned=" + rewardsEarned +
                '}';
    }
}


