package edu.sjsu.seekers.starbucks.dao.impl;

import edu.sjsu.seekers.starbucks.dao.UserDAO;
import edu.sjsu.seekers.starbucks.dao.repository.UserRepository;
import edu.sjsu.seekers.starbucks.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired
    UserRepository userRepository;

    @Override
    public Optional<User> get(Integer id) {

        Optional<User> user;
        user =userRepository.findById(id);
        return user;
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public String getUserPassword(String userName) {
        return userRepository.getPassword(userName);
    }

    @Override
    public int resetUserPassword(Integer userKey, String password )
    {
       int result= userRepository.resetPassword(userKey,password);
       return result;
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public Optional<User> findUserByUsername(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public Optional<User> findUserByEmailId(String emailId) {
        return userRepository.findByEmailId(emailId);
    }

    @Override
    public Integer getUserKey(String userName) {
        return userRepository.getUserKey(userName);
    }

}
