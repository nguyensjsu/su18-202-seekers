package edu.sjsu.seekers.starbucks.dao.impl;


import edu.sjsu.seekers.starbucks.dao.StoresDAO;
import edu.sjsu.seekers.starbucks.dao.repository.StoresRepository;
import edu.sjsu.seekers.starbucks.model.Stores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Optional;

@Configuration
public class StoresDAOImpl implements StoresDAO {

    @Autowired
    StoresRepository storesRepository;
    @Override
    public Optional<Stores> get(Integer id) {

        Optional<Stores> stores;
        stores = storesRepository.findById(id);
        return stores;
    }

    @Override
    public List<Stores> getAll(){
        return storesRepository.findAll();
    }

    @Override
    public Optional<Stores> getStoreByName(String storeName) {
        return storesRepository.getStoreByName(storeName);
    }

}
