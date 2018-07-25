package edu.sjsu.seekers.starbucks.dao.repository;

import edu.sjsu.seekers.starbucks.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Integer> {

    @Query("SELECT o FROM Orders o WHERE o.userKey.userKey = :userkey")
    List<Orders> findByUserKey(@Param("userkey") Integer userKey);


    @Query("SELECT o FROM Orders o WHERE o.userKey.userKey = :userkey and o.orderStatus = :status")
    Optional<Orders> findByUserKeyAndOrderStatus(@Param("userkey") Integer userKey, @Param("status") String status);

}
