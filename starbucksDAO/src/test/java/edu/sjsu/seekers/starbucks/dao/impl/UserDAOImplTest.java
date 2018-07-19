package edu.sjsu.seekers.starbucks.dao.impl;

import edu.sjsu.seekers.starbucks.StarbucksApplication;
import edu.sjsu.seekers.starbucks.UnitTestDatabaseConfig;
import edu.sjsu.seekers.starbucks.dao.repository.UserRepository;
import edu.sjsu.seekers.starbucks.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {StarbucksApplication.class,UnitTestDatabaseConfig.class})
@ActiveProfiles("unit-test")
public class UserDAOImplTest {


    @Autowired
    DataSource dataSource;

    @Autowired
    UserRepository userRepository;



    @Test
    public void get() throws SQLException {
        System.out.println("Our DataSource is = " + dataSource.getConnection());
//        User user = new User();
//        user.setFullname("test test");
//        user.setUsername("test");
//        userRepository.save(user);

        Iterable<User> users;
        users = userRepository.findAll();
        for(User usr : users)
            System.out.println("test user is: " + usr);

    }
}