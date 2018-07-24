package edu.sjsu.seekers.starbucks.dao.repository;

import edu.sjsu.seekers.starbucks.model.Orders;
import edu.sjsu.seekers.starbucks.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Products, Integer> {
    @Query("SELECT p FROM Products p WHERE p.productName = :productName and p.active = true")
    Products getProductByProductName(@Param("productName") String productName);

    @Query("SELECT p FROM Products p WHERE p.active = true")
    List<Products> getAllActiveProducts();
}
