package edu.sjsu.seekers.starbucks.dao;

import edu.sjsu.seekers.starbucks.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDAO {

    public Optional<User> get(Integer Id);
    public void save(User user);
    public Optional<User> findUserByUsername(String userName);
    public Optional<User> findUserByEmailId(String emailId);
    public void delete(User user);
    public int resetUserPassword(String password, String userName);

}
