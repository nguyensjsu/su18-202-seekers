package edu.sjsu.seekers.starbucks.dao.repository;

import edu.sjsu.seekers.starbucks.model.User;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {


    @Query("SELECT u FROM User u WHERE u.userName = :userName")
    Optional<User> findByUserName(@Param("userName") String userName);

    @Query("SELECT u FROM User u WHERE u.emailId = :emailId")
    Optional<User> findByEmailId(@Param("emailId") String emailId);

    @Query("SELECT u.userKey FROM User u WHERE u.userName = :userName")
    Integer getUserKey(@Param("userName") String userName);

    @Query("SELECT u.password FROM User u WHERE u.userName = :userName")
    String getPassword(@Param("userName") String userName);

    @Transactional
    @Modifying
    @Query(value = "UPDATE User u SET u.password = :password WHERE u.userKey = :userKey")
    int resetPassword(@Param("userKey") Integer userKey, @Param("password") String password);



}
