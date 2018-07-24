package edu.sjsu.seekers.OrderAPI.response;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import edu.sjsu.seekers.starbucks.model.ProductCatalog;
import edu.sjsu.seekers.starbucks.model.Products;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductsResponse extends GenericResponse {



    public ProductsResponse() {

    }

    private static final long serialVersionUID = 1L;


    @JsonIgnore
    private List<Products> productsList;
    @JsonIgnore
    public List<Products> getProduct() {
        return productsList;
    }

    public void setProduct(List<Products> product) {
        this.productsList = product;
    }



    public void setFinalMessage() throws JsonProcessingException {


        Map<String,Object> outputMap = new HashMap<>();
        Map<String,Object> productMap = new HashMap<>();


        for(Products prd : productsList) {
            productMap = new HashMap<>();
            productMap.put("Product Description",prd.getProductDescription());
            productMap.put("Product ImageLink",prd.getProductImageLink());
            outputMap.put(prd.getProductName(),productMap);
        }

        setMessage(outputMap);
    }
}
