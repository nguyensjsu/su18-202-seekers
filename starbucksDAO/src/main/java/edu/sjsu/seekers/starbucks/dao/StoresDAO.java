package edu.sjsu.seekers.starbucks.dao;

import edu.sjsu.seekers.starbucks.model.ProductCatalog;
import edu.sjsu.seekers.starbucks.model.Stores;

import java.util.Optional;

public interface StoresDAO {

    /*public Optional<Stores> findStoresByAddressKey(Integer addressKey);*/
    public Optional<Stores> get(Integer id);
    /*public Optional<Stores> findStoresNearUser(Integer addressKey);*/
}
