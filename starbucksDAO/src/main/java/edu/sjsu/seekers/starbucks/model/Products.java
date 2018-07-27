package edu.sjsu.seekers.starbucks.model;

import javax.persistence.*;

@Entity
public class Products {




    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="Product_Key")
    private Integer productKey;

    @Column(name="Product_Name")
    private String productName;

    @Column(name="Product_Description")
    private String productDescription;

    @Column(name="Product_Image_Link")
    private String productImageLink;

    @Column(name="Active")
    private boolean active;

    public Integer getProductKey() {
        return productKey;
    }

    public void setProductKey(Integer productKey) {
        this.productKey = productKey;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductImageLink() {
        return productImageLink;
    }

    public void setProductImageLink(String productImageLink) {
        this.productImageLink = productImageLink;
    }

    public boolean getProductStatus() {
        return active;
    }

    public void setProductStatus(boolean productStatus) {
        this.active = productStatus;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }


    @Override
    public String toString() {
        return "Products{" +
                "productKey=" + productKey +
                ", productName='" + productName + '\'' +
                ", productDescription='" + productDescription + '\'' +
                ", productImageLink='" + productImageLink + '\'' +
                ", active=" + active +
                '}';
    }
}
