package edu.sjsu.seekers.starbucks.dao.repository;

import edu.sjsu.seekers.starbucks.model.ProductCatalog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCatalogRepository extends JpaRepository<ProductCatalog, Integer> {

}
