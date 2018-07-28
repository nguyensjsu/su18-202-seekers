package edu.sjsu.seekers.starbucks.dao.impl;

import edu.sjsu.seekers.starbucks.StarbucksApplication;
import edu.sjsu.seekers.starbucks.dao.AddressDAO;
import edu.sjsu.seekers.starbucks.dao.UserDAO;
import edu.sjsu.seekers.starbucks.dao.repository.AddressRepository;
import edu.sjsu.seekers.starbucks.dao.repository.UserRepository;
import edu.sjsu.seekers.starbucks.model.Address;
import edu.sjsu.seekers.starbucks.model.User;
import org.junit.After;
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
public class UserDAOImplTest {

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
       System.out.println("************running UserDAOImplTest setup************");
       createTestAddress();
       createTestUser();
   }



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

    public void createTestUser()
   {
       user= new User();
       user.setUserName("Sindhu_TestUser2");
       user.setDateOfBirth("2001-04-09");
       user.setEmailId("sindhutest2@gmail.com");
       user.setFullName("Sindhu TestUser");
       user.setDefaultStoreKey(1);
       user.setAddressKey(address);
       user.setIdActiveCustomer("1");
       user.setIsLoggedIn("1");
       user.setPassword("testPassword");
       user.setPhoneNumber("9876543210");
       user.setRewardPoints(0.0);
       user.setIdActiveCustomer("Y");
       user.setIsLoggedIn("N");
       user.setAddressKey(address);
       userDAO.save(user);
   }

   @Test
   public void findUserByUsername() throws SQLException
   {
       System.out.println("************running findUserByUserName test************");
       Exception ex = null;
       try {

           Optional<User> userDetails= userDAO.findUserByUsername("Sindhu_TestUser2");

       }
       catch(Exception e)
       {
           System.out.println("************inside findByUserName catch************ " + e.getMessage());
           ex = e;
       }
       assertNull(ex);
   }

   @Test
   public void findUserByEmailId() throws SQLException
   {
       System.out.println("************running findUserByEmailId test************");
       Exception ex = null;
       try {

           Optional<User> userDetails= userDAO.findUserByEmailId("sindhutest2@gmail.com");

       }
       catch(Exception e)
       {
           System.out.println("************inside findUserByEmailId catch************ " + e.getMessage());
           ex = e;
       }
       assertNull(ex);
   }

   @Test
   public void resetUserPassword() throws SQLException
   {
       System.out.println("************running resetUserPassword test************");
       Exception ex = null;
       try {

           int result= userDAO.resetUserPassword(1,"testPassword");

       }
       catch(Exception e)
       {
           System.out.println("************inside resetUserPassword catch************ " + e.getMessage());
           ex = e;
       }
       assertNull(ex);

   }

    @After
    public void testEnd()
    {
        System.out.println("************running testEnd setup************");
        userRepository.delete(user);
        addressRepository.delete(address);

    }

}