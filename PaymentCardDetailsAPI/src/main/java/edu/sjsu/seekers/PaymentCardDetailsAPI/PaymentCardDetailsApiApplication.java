package edu.sjsu.seekers.PaymentCardDetailsAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableAutoConfiguration
@EnableJpaRepositories("edu.sjsu.seekers.starbucks")
@ComponentScan("edu.sjsu.seekers")
public class PaymentCardDetailsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentCardDetailsApiApplication.class, args);
	}
}
