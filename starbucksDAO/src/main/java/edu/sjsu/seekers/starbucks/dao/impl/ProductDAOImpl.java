package edu.sjsu.seekers.starbucks.dao.impl;

import edu.sjsu.seekers.starbucks.dao.ProductDAO;
import edu.sjsu.seekers.starbucks.dao.repository.ProductRepository;
import edu.sjsu.seekers.starbucks.model.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Optional;

@Configuration
public class ProductDAOImpl implements ProductDAO {

    @Autowired
    ProductRepository productRepository;

    @Override
    public Optional<Products> get(Integer id) {

        Optional<Products> product;
        product = productRepository.findById(id);
        return product;
    }

    @Override
    public void save(Products products) {
        productRepository.save(products);
    }

    @Override
    public List<Products> getAll() {
        return  productRepository.findAll();
    }


}
