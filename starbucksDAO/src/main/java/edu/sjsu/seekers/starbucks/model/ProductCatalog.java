package edu.sjsu.seekers.starbucks.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
public class ProductCatalog {

    @ManyToOne
    @JoinColumn(name = "Product_Key", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Products products;

    @ManyToOne
    @JoinColumn(name = "Size_Key", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Size size;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="Product_Catalog_Key")
    private Integer productCatalogKey;


    @Column(name="Price")
    private Double price;

    @Column(name="Rewards_Earned")
    private Double rewards;


    public Double getRewards() {
        return rewards;
    }

    public void setRewards(Double rewards) {
        this.rewards = rewards;
    }

    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }


    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Integer getProductCatalogKey() {
        return productCatalogKey;
    }

    public void setProductCatalogKey(Integer productCatalogKey) {
        this.productCatalogKey = productCatalogKey;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }


    @Override
    public String toString() {
        return "ProductCatalog{" +
                "products=" + products +
                ", size=" + size +
                ", productCatalogKey=" + productCatalogKey +
                ", price=" + price +
                ", rewards=" + rewards +
                '}';
    }
}


