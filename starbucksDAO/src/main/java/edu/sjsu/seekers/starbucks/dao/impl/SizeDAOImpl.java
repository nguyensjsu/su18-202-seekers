package edu.sjsu.seekers.starbucks.dao.impl;

import edu.sjsu.seekers.starbucks.dao.SizeDAO;
import edu.sjsu.seekers.starbucks.dao.repository.SizeRepository;
import edu.sjsu.seekers.starbucks.model.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;

@Configuration
public class SizeDAOImpl implements SizeDAO {

    @Autowired
    SizeRepository sieRepository;

    @Override
    public Optional<Size> get(Integer id) {

        Optional<Size> size;
        size = sieRepository.findById(id);
        return size;
    }
}
