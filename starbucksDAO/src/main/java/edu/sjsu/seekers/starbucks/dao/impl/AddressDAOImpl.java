package edu.sjsu.seekers.starbucks.dao.impl;

import edu.sjsu.seekers.starbucks.dao.AddressDAO;
import edu.sjsu.seekers.starbucks.dao.repository.AddressRepository;
import edu.sjsu.seekers.starbucks.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Optional;

@Configuration
public class AddressDAOImpl implements AddressDAO {

    @Autowired
    AddressRepository addressRepository;

    @Override
    public Optional<Address> get(Integer Id) {

        Optional<Address> address;
        address =addressRepository.findById(1);
        return address;
    }

    @Override
    public void save(Address address) {
        addressRepository.save(address);
    }


    @Override
    public void update(Address address) {

    }


}
