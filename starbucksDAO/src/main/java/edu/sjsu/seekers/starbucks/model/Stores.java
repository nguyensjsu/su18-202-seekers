package edu.sjsu.seekers.starbucks.model;

import javax.persistence.*;

@Entity
public class Stores {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Store_Key")
    private Integer storeKey;

    @Column(name = "Address_Key")
    private Integer addressKey;

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

    public Integer getAddressKey() {
        return addressKey;
    }

    public void setAddressKey(Integer addressKey) {
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

