package edu.sjsu.seekers.starbucks.dao.impl;

import edu.sjsu.seekers.starbucks.dao.AddressDAO;
import edu.sjsu.seekers.starbucks.dao.UserDAO;
import edu.sjsu.seekers.starbucks.dao.repository.AddressRepository;
import edu.sjsu.seekers.starbucks.dao.repository.UserRepository;
import edu.sjsu.seekers.starbucks.model.Address;
import edu.sjsu.seekers.starbucks.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;
import java.util.Optional;

import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("unit-test")
public class AddressDAOImplTest {
    @Autowired
    UserRepository userRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    UserDAO userDAO;

    @Autowired
    AddressDAO addressDAO;



    User user;
    Address address;

    @Before
    public void setup()
    {
        System.out.println("************running AddressDAOImplTest setup************");
        createTestAddress();
    }

    @Test
    public void createTestAddress()
    {
        address = new Address();
        address.setAddressLine1("Test address");
        address.setAddressLine2("apt 122");
        address.setCity("TCity");
        address.setCountry("TCountry");
        address.setState("TState");
        address.setZipCode(12345);
        addressDAO.save(address);

    }

    @Test
    public void getLastRow() throws SQLException
    {

        System.out.println("************running getlastRow test************");
        Exception ex = null;
        try {

            Optional<Address> addressDetails= addressDAO.getLastInsertedRow();

        }
        catch(Exception e)
        {
            System.out.println("************inside getLastRow catch************ " + e.getMessage());
            ex = e;
        }
        assertNull(ex);
    }

    @Test
    public void getAddress() throws SQLException
    {
        System.out.println("************running getAddress test************");
        Exception ex = null;
        try {

            Optional<Address> addressDetails= addressDAO.get(1);

        }
        catch(Exception e)
        {
            System.out.println("************inside getAddress catch************ " + e.getMessage());
            ex = e;
        }
        assertNull(ex);
    }

}
