package edu.sjsu.seekers.starbucks.dao;

import edu.sjsu.seekers.starbucks.model.Address;

import java.util.List;
import java.util.Optional;

public interface AddressDAO {

    public Optional<Address> get(Integer Id);
    public void save(Address address);
    public void update(Address address);
}
