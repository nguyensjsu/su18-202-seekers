package edu.sjsu.seekers.starbucks.dao;

import edu.sjsu.seekers.starbucks.model.Size;

import java.util.Optional;

public interface SizeDAO {
    public Optional<Size> get(Integer id);
    public Size getSizeByName(String name);
}
