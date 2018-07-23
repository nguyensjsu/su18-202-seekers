package edu.sjsu.seekers.UserProfileAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableAutoConfiguration
@EnableJpaRepositories("edu.sjsu.seekers.starbucks")
@ComponentScan("edu.sjsu.seekers")
//@PropertySource(value = "classpath:application.properties")
public class UserProfileApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserProfileApiApplication.class, args);
	}
}
