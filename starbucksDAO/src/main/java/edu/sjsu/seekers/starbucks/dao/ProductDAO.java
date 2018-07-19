package edu.sjsu.seekers.starbucks.dao;

import edu.sjsu.seekers.starbucks.model.Products;

import java.util.Optional;

public interface ProductDAO {
    public Optional<Products> get(Integer id);
    public void save(Products products);
}
