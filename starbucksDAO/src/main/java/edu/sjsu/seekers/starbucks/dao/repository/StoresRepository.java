package edu.sjsu.seekers.starbucks.dao.repository;

import edu.sjsu.seekers.starbucks.model.Stores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StoresRepository extends JpaRepository<Stores, Integer> {

    /*@Query("SELECT o FROM Stores o WHERE o.addresskey = :addresskey")
    Optional<Stores> findByAddressKey(@Param("addresskey") Integer addressKey);*/
}
