package edu.sjsu.seekers.starbucks.dao.impl;


import edu.sjsu.seekers.starbucks.dao.repository.StoresDAO;
import edu.sjsu.seekers.starbucks.dao.repository.StoresRepository;
import edu.sjsu.seekers.starbucks.model.Products;
import edu.sjsu.seekers.starbucks.model.Stores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

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
}
