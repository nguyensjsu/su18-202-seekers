package edu.sjsu.seekers.starbucks.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class PaymentDetails {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="Payment_Id")
    private Integer paymentId;

    @ManyToOne
    @JoinColumn(name = "Order_Key", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Orders orderKey;

    @Column(name="Payment_Status")
    private String paymentStatus;

    public Integer getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }

    public Orders getOrderKey() {
        return orderKey;
    }

    public void setOrderKey(Orders orderKey) {
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
