package edu.sjsu.seekers.starbucks.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
public class Stores {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Store_Key")
    private Integer storeKey;

    @ManyToOne
    @JoinColumn(name = "Address_Key", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Address addressKey;

    @Column(name = "Store_Name")

    private String storeName;

    @Column(name = "Store_Owner")
    private String storeOwner;


    public Integer getStoreKey() {
        return storeKey;
    }

    public void setStoreKey(Integer storeKey) {
        this.storeKey = storeKey;
    }

    public Address getAddressKey() {
        return addressKey;
    }

    public void setAddressKey(Address addressKey) {
        this.addressKey = addressKey;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreOwner() {
        return storeOwner;
    }

    public void setStoreOwner(String storeOwner) {
        this.storeOwner = storeOwner;
    }

    @Override
    public String toString() {
        return "Stores{" +
                "storeKey=" + storeKey +
                ", addressKey='" + addressKey + '\'' +
                ", storeName='" + storeName + '\'' +
                ", storeOwner='" + storeOwner + '\'' +
                '}';
    }
}

