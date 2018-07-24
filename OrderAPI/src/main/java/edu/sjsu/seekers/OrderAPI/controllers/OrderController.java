package edu.sjsu.seekers.OrderAPI.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import edu.sjsu.seekers.OrderAPI.request.ProductRequest;
import edu.sjsu.seekers.OrderAPI.request.ProductsRequest;
import edu.sjsu.seekers.OrderAPI.response.ProductResponse;
import edu.sjsu.seekers.OrderAPI.response.ProductsResponse;
import edu.sjsu.seekers.OrderAPI.service.OrderServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class OrderController {

    @Autowired
    OrderServiceAPI orderServiceAPI;


    ObjectMapper mapper = new ObjectMapper();
    @RequestMapping(value = "/products",method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ResponseEntity<ProductsResponse> getAllProducts(@RequestBody ProductsRequest request) {
        System.out.println("Products request: "+ request);
        ResponseEntity<ProductsResponse> responseEntity = null;
        ProductsResponse productsResponse = orderServiceAPI.getAllProductsResponse();
        responseEntity = new ResponseEntity<ProductsResponse>(productsResponse,HttpStatus.OK);
        return responseEntity;
    }

    @RequestMapping(value = "/product",method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ResponseEntity<ProductResponse> getAllProducts(@RequestBody ProductRequest request) {
        System.out.println("Product request: "+ request);
        ResponseEntity<ProductResponse> responseEntity = null;
        ProductResponse response  = orderServiceAPI.getSpecificProduct(request.getName());
        responseEntity = new ResponseEntity<ProductResponse>(response,HttpStatus.OK);
        return responseEntity;
    }
}
