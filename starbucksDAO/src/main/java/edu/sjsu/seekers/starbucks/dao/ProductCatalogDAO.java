package edu.sjsu.seekers.starbucks.dao;

import edu.sjsu.seekers.starbucks.model.ProductCatalog;

import java.util.Optional;

public interface ProductCatalogDAO {
    public Optional<ProductCatalog> get(Integer id);
}
