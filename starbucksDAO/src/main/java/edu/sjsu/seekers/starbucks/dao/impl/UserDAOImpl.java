package edu.sjsu.seekers.starbucks.dao.impl;

import edu.sjsu.seekers.starbucks.dao.UserDAO;
import edu.sjsu.seekers.starbucks.dao.repository.UserRepository;
import edu.sjsu.seekers.starbucks.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;

@Configuration
public class UserDAOImpl implements UserDAO {

    @Autowired
    UserRepository userRepository;

    @Override
    public Optional<User> get(Integer id) {

        Optional<User> user;
        user =userRepository.findById(1);
        return user;
    }
}
