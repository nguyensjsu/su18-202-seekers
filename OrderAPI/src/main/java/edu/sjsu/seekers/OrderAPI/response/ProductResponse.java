package edu.sjsu.seekers.OrderAPI.response;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.sjsu.seekers.starbucks.model.ProductCatalog;
import edu.sjsu.seekers.starbucks.model.Products;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductResponse extends GenericResponse {



    public ProductResponse() {

    }

    private static final long serialVersionUID = 1L;



    @JsonIgnore
    private Products product;

    @JsonIgnore
    private List<ProductCatalog> productCatalogs;
    @JsonIgnore
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

    public void setFinalMessage() throws JsonProcessingException {

        Map<String,Object> outputMap = new HashMap<>();
        outputMap.put("Product Name",product.getProductName());
        outputMap.put("Product Description",product.getProductDescription());
        outputMap.put("Product ImageLink",product.getProductImageLink());

        Map<String,String> childOutputMap = new HashMap<>();
        for(ProductCatalog pc : productCatalogs)
        {
            childOutputMap.put(pc.getSize().getSizeName(),"Price: $" + pc.getPrice().toString() + ", Reward Points: $" + pc.getRewards().toString());
        }
        outputMap.put("Sizes",childOutputMap);
        setMessage(outputMap);
    }
}
