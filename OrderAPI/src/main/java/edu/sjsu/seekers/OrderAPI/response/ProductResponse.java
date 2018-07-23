package edu.sjsu.seekers.OrderAPI.response;


import edu.sjsu.seekers.starbucks.model.ProductCatalog;
import edu.sjsu.seekers.starbucks.model.Products;

import java.util.List;

public class ProductResponse extends GenericResponse {



    public ProductResponse() {

    }

    private static final long serialVersionUID = 1L;



    private Products product;

    private List<ProductCatalog> productCatalogs;

    public List<ProductCatalog> getProductCatalog() {
        return productCatalogs;
    }

    public void setProductCatalog(List<ProductCatalog> productCatalog) {
        this.productCatalogs = productCatalog;
    }

    public Products getProduct() {
        return product;
    }

    public void setProduct(Products product) {
        this.product = product;
    }



    public void     setFinalMessage() {
        String result = "Product Name:"
                + product.getProductName() + System.lineSeparator() +
                "Description:" + product.getProductDescription() + System.lineSeparator() +
                "Image Link:" + product.getProductImageLink() + System.lineSeparator() +
                "Sizes available:" + System.lineSeparator();

        for(ProductCatalog pc : productCatalogs)
        {
            result += "Size: " + pc.getSize().getSizeName() + " - " + pc.getPrice() + System.lineSeparator();
        }

        result +="status Code=" + getStatusCode();
        setMessage(result);
    }
}
