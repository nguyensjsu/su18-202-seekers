package edu.sjsu.seekers.starbucks;

import edu.sjsu.seekers.starbucks.dao.impl.OrderDAOImpl;
import edu.sjsu.seekers.starbucks.dao.impl.ProductCatalogDAOImpl;
import edu.sjsu.seekers.starbucks.dao.impl.ProductDAOImpl;
import edu.sjsu.seekers.starbucks.dao.impl.SizeDAOImpl;
import edu.sjsu.seekers.starbucks.model.Orders;
import edu.sjsu.seekers.starbucks.model.ProductCatalog;
import edu.sjsu.seekers.starbucks.model.Products;
import edu.sjsu.seekers.starbucks.model.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Optional;


@SpringBootApplication
@EnableJpaRepositories("edu.sjsu.seekers.starbucks.dao")
@EntityScan("edu.sjsu.seekers.starbucks.model")
public class StarbucksApplication implements CommandLineRunner {

	@Autowired
	OrderDAOImpl orderDAOImpl;

    @Autowired
    ProductDAOImpl productDAOImpl;

    @Autowired
    SizeDAOImpl sizeDAOImpl;

    @Autowired
    ProductCatalogDAOImpl productCatalogDAOImpl;

	public static void main(String[] args) {
		SpringApplication.run(StarbucksApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Optional<Orders> order = orderDAOImpl.get(1);
		System.out.println("order: " + order.toString());


        Optional<Products> product = productDAOImpl.get(1);
        System.out.println("product: " + product.toString());

        Optional<Size> size = sizeDAOImpl.get(1);
        System.out.println("size: " + size.toString());

        Optional<ProductCatalog> productCatalog = productCatalogDAOImpl.get(1);
        System.out.println("productCatalog: " + productCatalog.toString());

	}
}
