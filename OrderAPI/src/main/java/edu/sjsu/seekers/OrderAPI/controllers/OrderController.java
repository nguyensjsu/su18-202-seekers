package edu.sjsu.seekers.OrderAPI.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import edu.sjsu.seekers.OrderAPI.request.*;
import edu.sjsu.seekers.OrderAPI.response.*;
import edu.sjsu.seekers.OrderAPI.service.OrderServiceAPI;
import edu.sjsu.seekers.starbucks.model.Stores;
import edu.sjsu.seekers.starbucks.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
public class OrderController {

    @Autowired
    OrderServiceAPI orderServiceAPI;

    @RequestMapping(value = "/products",method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ResponseEntity<ProductsResponse> getAllProducts(@RequestBody ProductsRequest request) {
        System.out.println("Products request: "+ request);
        ResponseEntity<ProductsResponse> responseEntity = null;
        Optional<User> inputUser = orderServiceAPI.findUserByUsername(request.getUserName());
        if(inputUser.isPresent() && inputUser.get().getIdActiveCustomer() != null && inputUser.get().getIdActiveCustomer().equals("Y")) {
            if(inputUser.get().getIsLoggedIn().equals("Y")) {
                ProductsResponse productsResponse = orderServiceAPI.getAllProductsResponse();
                responseEntity = new ResponseEntity<ProductsResponse>(productsResponse, HttpStatus.OK);
            }
            else {
                ProductsResponse productsResponse = new ProductsResponse();
                productsResponse.setMessage("Invalid session, please authenticate first!");
                productsResponse.setStatusCode(HttpStatus.EXPECTATION_FAILED.toString());
                responseEntity = new ResponseEntity<ProductsResponse>(productsResponse, HttpStatus.OK);
            }
        }
        else {
            ProductsResponse productsResponse = new ProductsResponse();
            productsResponse.setMessage("Invalid user, please signup first!");
            productsResponse.setStatusCode(HttpStatus.EXPECTATION_FAILED.toString());
            responseEntity = new ResponseEntity<ProductsResponse>(productsResponse, HttpStatus.OK);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/product",method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ResponseEntity<ProductResponse> getAllProducts(@RequestBody ProductRequest request) {
        System.out.println("Product request: "+ request);
        ResponseEntity<ProductResponse> responseEntity = null;
        Optional<User> inputUser = orderServiceAPI.findUserByUsername(request.getUserName());
        if(inputUser.isPresent() && inputUser.get().getIdActiveCustomer() != null && inputUser.get().getIdActiveCustomer().equals("Y")) {
            if(inputUser.get().getIsLoggedIn().equals("Y")) {
                ProductResponse response = orderServiceAPI.getSpecificProduct(request.getName());
                responseEntity = new ResponseEntity<ProductResponse>(response, HttpStatus.OK);
            }
            else {
                ProductResponse productResponse = new ProductResponse();
                productResponse.setMessage("Invalid session, please authenticate first!");
                productResponse.setStatusCode(HttpStatus.EXPECTATION_FAILED.toString());
                responseEntity = new ResponseEntity<ProductResponse>(productResponse, HttpStatus.OK);
            }
        }
        else {
            ProductResponse productResponse = new ProductResponse();
            productResponse.setMessage("Invalid user, please signup first!");
            productResponse.setStatusCode(HttpStatus.EXPECTATION_FAILED.toString());
            responseEntity = new ResponseEntity<ProductResponse>(productResponse, HttpStatus.OK);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/stores",method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ResponseEntity<StoresResponse> getAllStores(@RequestBody StoresRequest request) {
        System.out.println("Stores request: "+ request);
        ResponseEntity<StoresResponse> responseEntity = null;
        Optional<User> inputUser = orderServiceAPI.findUserByUsername(request.getUserName());
        if(inputUser.isPresent() && inputUser.get().getIdActiveCustomer() != null && inputUser.get().getIdActiveCustomer().equals("Y")) {
            if(inputUser.get().getIsLoggedIn().equals("Y")) {
                StoresResponse storesResponse = orderServiceAPI.getAllStoresResponse();
                responseEntity = new ResponseEntity<StoresResponse>(storesResponse, HttpStatus.OK);
            }
            else {
                StoresResponse storesResponse = new StoresResponse();
                storesResponse.setMessage("Invalid session, please authenticate first!");
                storesResponse.setStatusCode(HttpStatus.EXPECTATION_FAILED.toString());
                responseEntity = new ResponseEntity<StoresResponse>(storesResponse, HttpStatus.OK);
            }
        }
        else {
            StoresResponse storesResponse = new StoresResponse();
            storesResponse.setMessage("Invalid user, please signup first!");
            storesResponse.setStatusCode(HttpStatus.EXPECTATION_FAILED.toString());
            responseEntity = new ResponseEntity<StoresResponse>(storesResponse, HttpStatus.OK);
        }
        return responseEntity;
    }


    @RequestMapping(value = "/addToCart",method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ResponseEntity<GenericResponse> addToCart(@RequestBody CartRequest request) {
        System.out.println("Add to cart request: "+ request);
        ResponseEntity<GenericResponse> responseEntity = null;
        Optional<User> inputUser = orderServiceAPI.findUserByUsername(request.getUserName());
        if(inputUser.isPresent() && inputUser.get().getIdActiveCustomer() != null && inputUser.get().getIdActiveCustomer().equals("Y")) {
            if(inputUser.get().getIsLoggedIn().equals("Y")) {
                GenericResponse genericResponse = orderServiceAPI.addToCart(request.getProductDetails(),inputUser.get());
                responseEntity = new ResponseEntity<GenericResponse>(genericResponse, HttpStatus.OK);
            }
            else {
                GenericResponse genericResponse = new GenericResponse();
                genericResponse.setMessage("Invalid session, please authenticate first!");
                genericResponse.setStatusCode(HttpStatus.EXPECTATION_FAILED.toString());
                responseEntity = new ResponseEntity<GenericResponse>(genericResponse, HttpStatus.OK);
            }
        }
        else {
            GenericResponse genericResponse = new GenericResponse();
            genericResponse.setMessage("Invalid user, please signup first!");
            genericResponse.setStatusCode(HttpStatus.EXPECTATION_FAILED.toString());
            responseEntity = new ResponseEntity<GenericResponse>(genericResponse, HttpStatus.OK);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/addStore",method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ResponseEntity<GenericResponse> addStoreToCart(@RequestBody StoreRequest request) {
        System.out.println("Add store request: "+ request);
        ResponseEntity<GenericResponse> responseEntity = null;
        Optional<User> inputUser = orderServiceAPI.findUserByUsername(request.getUserName());
        if(inputUser.isPresent() && inputUser.get().getIdActiveCustomer() != null && inputUser.get().getIdActiveCustomer().equals("Y")) {
            if(inputUser.get().getIsLoggedIn().equals("Y")) {
                GenericResponse genericResponse = orderServiceAPI.addStoreToCart(request.getStoreName(),inputUser.get());
                responseEntity = new ResponseEntity<>(genericResponse, HttpStatus.OK);
            }
            else {
                GenericResponse genericResponse = new GenericResponse();
                genericResponse.setMessage("Invalid session, please authenticate first!");
                genericResponse.setStatusCode(HttpStatus.EXPECTATION_FAILED.toString());
                responseEntity = new ResponseEntity<>(genericResponse, HttpStatus.OK);
            }
        }
        else {
            GenericResponse genericResponse = new GenericResponse();
            genericResponse.setMessage("Invalid user, please signup first!");
            genericResponse.setStatusCode(HttpStatus.EXPECTATION_FAILED.toString());
            responseEntity = new ResponseEntity<>(genericResponse, HttpStatus.OK);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/reviewCart",method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ResponseEntity<ReviewCartResponse> reviewCart(@RequestBody ProductsRequest request) {
        System.out.println("Review Cart request: "+ request);
        ResponseEntity<ReviewCartResponse> responseEntity = null;
        Optional<User> inputUser = orderServiceAPI.findUserByUsername(request.getUserName());
        if(inputUser.isPresent() && inputUser.get().getIdActiveCustomer() != null && inputUser.get().getIdActiveCustomer().equals("Y")) {
            if(inputUser.get().getIsLoggedIn().equals("Y")) {
                ReviewCartResponse reviewCartResponse = orderServiceAPI.reviewCartResponse(inputUser.get());
                responseEntity = new ResponseEntity<>(reviewCartResponse, HttpStatus.OK);
            }
            else {
                ReviewCartResponse reviewCartResponse = new ReviewCartResponse();
                reviewCartResponse.setMessage("Invalid session, please authenticate first!");
                reviewCartResponse.setStatusCode(HttpStatus.EXPECTATION_FAILED.toString());
                responseEntity = new ResponseEntity<>(reviewCartResponse, HttpStatus.OK);
            }
        }
        else {
            ReviewCartResponse reviewCartResponse = new ReviewCartResponse();
            reviewCartResponse.setMessage("Invalid user, please signup first!");
            reviewCartResponse.setStatusCode(HttpStatus.EXPECTATION_FAILED.toString());
            responseEntity = new ResponseEntity<>(reviewCartResponse, HttpStatus.OK);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/editCart",method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ResponseEntity<GenericResponse> editCart(@RequestBody CartEditRequest request) {
        System.out.println("edit cart request: "+ request);
        ResponseEntity<GenericResponse> responseEntity = null;
        Optional<User> inputUser = orderServiceAPI.findUserByUsername(request.getUserName());
        if(inputUser.isPresent() && inputUser.get().getIdActiveCustomer() != null && inputUser.get().getIdActiveCustomer().equals("Y")) {
            if(inputUser.get().getIsLoggedIn().equals("Y")) {
                GenericResponse genericResponse = orderServiceAPI.editCart(request.getProductDetails(),inputUser.get());
                responseEntity = new ResponseEntity<GenericResponse>(genericResponse, HttpStatus.OK);
            }
            else {
                GenericResponse genericResponse = new GenericResponse();
                genericResponse.setMessage("Invalid session, please authenticate first!");
                genericResponse.setStatusCode(HttpStatus.EXPECTATION_FAILED.toString());
                responseEntity = new ResponseEntity<GenericResponse>(genericResponse, HttpStatus.OK);
            }
        }
        else {
            GenericResponse genericResponse = new GenericResponse();
            genericResponse.setMessage("Invalid user, please signup first!");
            genericResponse.setStatusCode(HttpStatus.EXPECTATION_FAILED.toString());
            responseEntity = new ResponseEntity<GenericResponse>(genericResponse, HttpStatus.OK);
        }
        return responseEntity;
    }
}
