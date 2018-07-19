package edu.sjsu.seekers.starbucks.dao;

import edu.sjsu.seekers.starbucks.model.User;

import java.util.Optional;

public interface UserDAO {
    public Optional<User> get(Integer id);
}
