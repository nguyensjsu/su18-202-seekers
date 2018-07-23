package edu.sjsu.seekers.OrderAPI.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import edu.sjsu.seekers.OrderAPI.request.ProductRequest;
import edu.sjsu.seekers.OrderAPI.response.ProductResponse;
import edu.sjsu.seekers.OrderAPI.response.ProductsResponse;
import edu.sjsu.seekers.OrderAPI.service.OrderServiceAPI;
import edu.sjsu.seekers.starbucks.model.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class OrderController {

    @Autowired
    OrderServiceAPI orderServiceAPI;


    ObjectMapper mapper = new ObjectMapper();
//    @RequestMapping(value = "/getAllProducts",method = RequestMethod.GET, produces = "application/json")
//    @ResponseBody
//    public ResponseEntity<ProductsResponse> getAllProducts(@RequestBody ProductsRequest request) {
//
//        ResponseEntity<ProductsResponse> responseEntity = null;
//        ProductsResponse response ;
//        List<Products> products = orderServiceAPI.getAllProducts();
//    }

    @RequestMapping(value = "/product",method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ResponseEntity<ProductResponse> getAllProducts(@RequestBody ProductRequest request) {
        System.out.println("Product request: "+ request);
        ResponseEntity<ProductResponse> responseEntity = null;
        ProductResponse response  = orderServiceAPI.getSpecificProduct(request.getId());
        responseEntity = new ResponseEntity<ProductResponse>(response,HttpStatus.OK);
        return responseEntity;
    }
}
