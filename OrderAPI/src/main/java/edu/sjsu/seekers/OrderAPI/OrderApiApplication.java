package edu.sjsu.seekers.OrderAPI;

import edu.sjsu.seekers.starbucks.dao.OrderDAO;
import edu.sjsu.seekers.starbucks.dao.impl.OrderDAOImpl;
import edu.sjsu.seekers.starbucks.dao.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableAutoConfiguration
@EnableJpaRepositories("edu.sjsu.seekers.starbucks")
@ComponentScan("edu.sjsu.seekers")
public class OrderApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderApiApplication.class, args);
	}
}
