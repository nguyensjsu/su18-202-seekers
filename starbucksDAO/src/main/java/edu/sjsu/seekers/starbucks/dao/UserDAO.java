package edu.sjsu.seekers.starbucks.dao;

import edu.sjsu.seekers.starbucks.model.User;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;


@Configuration
public interface UserDAO {

    public Optional<User> get(Integer Id);
    public void save(User user);
    public Optional<User> findUserByUsername(String userName);
    public Optional<User> findUserByEmailId(String emailId);
    public Integer getUserKey(String userName);
    public void delete(User user);
    public String getUserPassword(String userName);
    public int resetUserPassword(Integer userKey, String password);
}
