package edu.sjsu.seekers.starbucks.dao.repository;

import edu.sjsu.seekers.starbucks.model.OrderDetails;
import edu.sjsu.seekers.starbucks.model.Orders;
import org.hibernate.criterion.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Integer> {

//    void findOrderDetailsforOrder(Integer orderKey);

//    @Query("SELECT o FROM OrderDetails o WHERE o.orderKey.orderKey = :orderKey")
    @Transactional
    @Modifying
    @Query("DELETE FROM OrderDetails o WHERE o.orderKey.orderKey = :orderKey")
    void deleteOrderDetailsforOrder(@Param("orderKey") Integer orderKey);

    @Query("SELECT o FROM OrderDetails o WHERE o.orderKey.orderKey = :orderKey")
    List<OrderDetails> findAllOrderDetailsforOrderKey(@Param("orderKey")Integer orderKey);

//
}
