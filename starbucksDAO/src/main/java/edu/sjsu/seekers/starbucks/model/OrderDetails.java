package edu.sjsu.seekers.starbucks.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
public class OrderDetails {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="Order_Line_Key")
    private Integer orderLineKey;

    @ManyToOne
    @JoinColumn(name = "Order_Key", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Orders orderKey;

    @ManyToOne
    @JoinColumn(name = "Product_Key", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Products productKey;

    @Column(name="Order_Quantity")
    private Integer orderQuantity;

    @Column(name="Net_Price")
    private Double netPrice;

    public Integer getOrderLineKey() {
        return orderLineKey;
    }

    public void setOrderLineKey(Integer orderLineKey) {
        this.orderLineKey = orderLineKey;
    }

    public Orders getOrderKey() {
        return orderKey;
    }

    public void setOrderKey(Orders orderKey) {
        this.orderKey = orderKey;
    }

    public Products getProductKey() {
        return productKey;
    }

    public void setProductKey(Products productKey) {
        this.productKey = productKey;
    }

    public Integer getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(Integer orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public Double getNetPrice() {
        return netPrice;
    }

    public void setNetPrice(Double netPrice) {
        this.netPrice = netPrice;
    }

    @Override
    public String toString() {
        return "OrderDetails{" +
                "orderLineKey=" + orderLineKey +
                ", orderKey=" + orderKey +
                ", productKey=" + productKey +
                ", orderQuantity=" + orderQuantity +
                ", netPrice=" + netPrice +
                '}';
    }
}
