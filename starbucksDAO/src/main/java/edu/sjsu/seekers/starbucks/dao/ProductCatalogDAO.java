package edu.sjsu.seekers.starbucks.dao;

import edu.sjsu.seekers.starbucks.model.ProductCatalog;

import java.util.List;
import java.util.Optional;

public interface ProductCatalogDAO {
    public Optional<ProductCatalog> get(Integer id);
    public List<ProductCatalog> getAllForProductByProductID(Integer id);
    public ProductCatalog getProductCatalogByIdAndSize(Integer productId,Integer sizeId);
}
