package edu.sjsu.seekers.starbucks.dao.impl;

import edu.sjsu.seekers.starbucks.dao.ProductCatalogDAO;
import edu.sjsu.seekers.starbucks.dao.ProductDAO;
import edu.sjsu.seekers.starbucks.dao.repository.ProductCatalogRepository;
import edu.sjsu.seekers.starbucks.dao.repository.ProductRepository;
import edu.sjsu.seekers.starbucks.model.ProductCatalog;
import edu.sjsu.seekers.starbucks.model.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;

@Configuration
public class ProductCatalogDAOImpl implements ProductCatalogDAO {

    @Autowired
    ProductCatalogRepository productCatalogRepository;

    @Override
    public Optional<ProductCatalog> get(Integer id) {

        Optional<ProductCatalog> productCataog;
        productCataog = productCatalogRepository.findById(id);
        return productCataog;
    }

}
