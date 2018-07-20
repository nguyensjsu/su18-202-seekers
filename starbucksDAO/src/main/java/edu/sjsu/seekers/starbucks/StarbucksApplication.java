package edu.sjsu.seekers.starbucks;

import edu.sjsu.seekers.starbucks.dao.OrderDetailsDAO;
import edu.sjsu.seekers.starbucks.dao.PaymentDetailsDAO;

import edu.sjsu.seekers.starbucks.model.*;
import edu.sjsu.seekers.starbucks.model.OrderDetails;
import edu.sjsu.seekers.starbucks.dao.impl.OrderDAOImpl;
import edu.sjsu.seekers.starbucks.dao.impl.ProductCatalogDAOImpl;
import edu.sjsu.seekers.starbucks.dao.impl.ProductDAOImpl;
import edu.sjsu.seekers.starbucks.dao.impl.SizeDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;
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
    @Autowired
	OrderDetailsDAO orderDetailsDAO;
	@Autowired
	PaymentDetailsDAO paymentDetailsDao;

	public static void main(String[] args) {
		SpringApplication.run(StarbucksApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
        Optional<OrderDetails> orders = orderDetailsDAO.get(1);
        System.out.println("Order: " + orders.toString());

		Optional<PaymentDetails> payments = paymentDetailsDao.get(1);
		System.out.println("Payments: " + payments.toString());

        Optional<Orders> order = orderDAOImpl.get(1);
        System.out.println("order: " + order.toString());

        Optional<Products> product = productDAOImpl.get(1);
        System.out.println("product: " + product.toString());

        OrderDetails orderLineItems = new OrderDetails();
        orderLineItems.setOrderKey(order.get());
        orderLineItems.setProductKey(product.get());
        orderLineItems.setOrderQuantity(95);
        orderLineItems.setNetPrice(123.9);
        orderDetailsDAO.save(orderLineItems);

        Optional<Size> size = sizeDAOImpl.get(1);
        System.out.println("size: " + size.toString());

        Optional<ProductCatalog> productCatalog = productCatalogDAOImpl.get(1);
        System.out.println("productCatalog: " + productCatalog.toString());

        List<Orders> ordersList = orderDAOImpl.findOrdersByUserKey(1);
        for (Orders ord: ordersList) {
            System.out.println("orderList: " + ord.toString());
        }
	}
}
