package edu.sjsu.seekers.starbucks.dao.repository;

import edu.sjsu.seekers.starbucks.model.ProductCatalog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCatalogRepository extends JpaRepository<ProductCatalog, Integer> {

    @Query("SELECT p FROM ProductCatalog p WHERE p.products.productKey = :productKey")
    List<ProductCatalog> getAllForProductByProductID(@Param("productKey") Integer productKey);

    @Query("SELECT p FROM ProductCatalog p WHERE p.products.productKey = :productKey and p.size.sizeKey = :sizeKey")
    ProductCatalog getProductCatalogByIdAndSize(@Param("productKey") Integer productKey,@Param("sizeKey") Integer sizeKey);
}
