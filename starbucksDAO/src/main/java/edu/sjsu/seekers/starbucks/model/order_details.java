package edu.sjsu.seekers.starbucks.model;

import javax.persistence.*;

@Entity
public class order_details {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="Order_Line_Key")
    private Integer orderLineKey;

    @Column(name="Order_Key")
    private Integer orderKey;

    @Column(name="Product_Key")
    private Integer productKey;

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

    public Integer getOrderKey() {
        return orderKey;
    }

    public void setOrderKey(Integer orderKey) {
        this.orderKey = orderKey;
    }

    public Integer getProductKey() {
        return productKey;
    }

    public void setProductKey(Integer productKey) {
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
        return "order_details{" +
                "orderLineKey=" + orderLineKey +
                ", orderKey=" + orderKey +
                ", productKey=" + productKey +
                ", orderQuantity=" + orderQuantity +
                ", netPrice=" + netPrice +
                '}';
    }
}
