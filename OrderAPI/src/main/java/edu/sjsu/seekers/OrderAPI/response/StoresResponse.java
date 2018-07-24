package edu.sjsu.seekers.OrderAPI.response;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import edu.sjsu.seekers.starbucks.model.Products;
import edu.sjsu.seekers.starbucks.model.Stores;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StoresResponse extends GenericResponse {



    public StoresResponse() {

    }

    private static final long serialVersionUID = 1L;


    @JsonIgnore
    private List<Stores> storesList;
    @JsonIgnore
    public List<Stores> getStoresList() {
        return storesList;
    }

    public void setStoresList(List<Stores> product) {
        this.storesList = product;
    }



    public void setFinalMessage() throws JsonProcessingException {


        Map<String,Object> outputMap = new HashMap<>();
        Map<String,Object> productMap = new HashMap<>();


        for(Stores str : storesList) {
            productMap = new HashMap<>();
            productMap.put("Address:",str.getAddressKey().getAddressLine1() + ", "
                    + str.getAddressKey().getAddressLine2()
                    + ", " + str.getAddressKey().getCity() + ", "+  str.getAddressKey().getState()
                    + ", " + str.getAddressKey().getCountry() + ", " +  str.getAddressKey().getZipCode());

            outputMap.put(str.getStoreName(),productMap);
        }

        setMessage(outputMap);
    }
}
