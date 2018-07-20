package edu.sjsu.seekers.starbucks.model;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class PaymentDetails {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="Payment_Id")
    private Integer paymentId;

    @Column(name="Order_Key")
    private Integer orderKey;

    @Column(name="Payment_Status")
    private String paymentStatus;

    public Integer getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }

    public Integer getOrderKey() {
        return orderKey;
    }

    public void setOrderKey(Integer orderKey) {
        this.orderKey = orderKey;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    @Override
    public String toString() {
        return "PaymentDetails{" +
                "paymentId=" + paymentId +
                ", orderKey=" + orderKey +
                ", paymentStatus='" + paymentStatus + '\'' +
                '}';
    }
}
