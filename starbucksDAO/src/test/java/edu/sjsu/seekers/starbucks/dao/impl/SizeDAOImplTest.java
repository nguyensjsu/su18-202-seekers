package edu.sjsu.seekers.starbucks.dao.impl;

import edu.sjsu.seekers.starbucks.dao.*;
import edu.sjsu.seekers.starbucks.dao.repository.ProductRepository;
import edu.sjsu.seekers.starbucks.dao.repository.SizeRepository;
import edu.sjsu.seekers.starbucks.dao.repository.StoresRepository;
import edu.sjsu.seekers.starbucks.model.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("unit-test")
public class SizeDAOImplTest {



    @Autowired
    StoresDAO storesDAO;

    @Autowired
    UserDAO userDAO;

    @Autowired
    AddressDAO addressDAO;


    @Autowired
    StoresRepository storesRepository;

    Address address;
    Stores stores;

    @Before
    public void setup()
    {
        System.out.println("************running setup for SizeDAOImplTest************");
        createTestAddress();
        createTestStore();

    }
    private void createTestAddress() {
        address = new Address();
        address.setAddressLine1("address line 1");
        address.setAddressLine2("address line 2");
        address.setCity("testCity");
        address.setCountry("testCountry");
        address.setState("testState");
        address.setZipCode(12345);
        addressDAO.save(address);
    }

    private void createTestStore() {
        stores = new Stores();
        stores.setAddressKey(address);
        stores.setStoreName("test store");
        stores.setStoreOwner("test owner");
        stores = storesRepository.save(stores);
    }


    @Test
    public void geStore() throws SQLException {
        System.out.println("************running geStore test************");
        Optional<Stores> storesGet = storesDAO.get(stores.getStoreKey());
        assertNotNull(storesGet.get());

    }
}