package edu.sjsu.seekers.starbucks.dao;

import edu.sjsu.seekers.starbucks.model.ProductCatalog;
import edu.sjsu.seekers.starbucks.model.Products;
import edu.sjsu.seekers.starbucks.model.Stores;

import java.util.List;
import java.util.Optional;

public interface StoresDAO {

    /*public Optional<Stores> findStoresByAddressKey(Integer addressKey);*/
    public Optional<Stores> get(Integer id);
    public List<Stores> getAll();
    /*public Optional<Stores> findStoresNearUser(Integer addressKey);*/
    public Optional<Stores> getStoreByName(String storeName);
}
