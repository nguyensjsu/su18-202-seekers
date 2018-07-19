package edu.sjsu.seekers.starbucks;

import edu.sjsu.seekers.starbucks.dao.impl.UserDAOImpl;
import edu.sjsu.seekers.starbucks.dao.repository.UserRepository;
import edu.sjsu.seekers.starbucks.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.sql.DataSource;
import java.util.Optional;


@SpringBootApplication
@EnableJpaRepositories("edu.sjsu.seekers.starbucks.dao")
@EntityScan("edu.sjsu.seekers.starbucks.model")
public class StarbucksApplication implements CommandLineRunner {

	@Autowired
	UserDAOImpl userDAOImpl;

	public static void main(String[] args) {
		SpringApplication.run(StarbucksApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
        Optional<User> user = userDAOImpl.get(1);
        System.out.println("user: " + user.toString());

	}
}
