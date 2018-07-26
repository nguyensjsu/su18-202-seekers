package edu.sjsu.seekers.starbucks;

import edu.sjsu.seekers.starbucks.dao.OrderDetailsDAO;
import edu.sjsu.seekers.starbucks.dao.PaymentCardDetailsDAO;
import edu.sjsu.seekers.starbucks.dao.PaymentDetailsDAO;

import edu.sjsu.seekers.starbucks.dao.UserDAO;
import edu.sjsu.seekers.starbucks.dao.impl.*;
import edu.sjsu.seekers.starbucks.dao.repository.UserRepository;
import edu.sjsu.seekers.starbucks.model.*;
import edu.sjsu.seekers.starbucks.model.OrderDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.sql.DataSource;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@SpringBootApplication
@EnableJpaRepositories("edu.sjsu.seekers.starbucks.dao")
@EntityScan("edu.sjsu.seekers.starbucks.model")
public class StarbucksApplication implements CommandLineRunner {

//    @Autowired
//    DataSource dataSource;
//
//	@Autowired
//	OrderDAOImpl orderDAOImpl;
//
//    @Autowired
//    ProductDAOImpl productDAOImpl;
//
//    @Autowired
//    SizeDAOImpl sizeDAOImpl;
//
//    @Autowired
//    ProductCatalogDAOImpl productCatalogDAOImpl;
//
//    @Autowired
//	OrderDetailsDAO orderDetailsDAO;
//
//    @Autowired
//	PaymentDetailsDAO paymentDetailsDao;
//
//    @Autowired
//    PaymentCardDetailsDAOImpl paymentCardDetailsDAOImpl;
//
//    @Autowired
//    UserDAOImpl userDAOImpl;
//
//    @Autowired
//    StoresDAOImpl storDAOImpl;

	public static void main(String[] args) {
		SpringApplication.run(StarbucksApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//        System.out.println("test datasource is: " + dataSource.getConnection());

//        Optional<Products> product = productDAOImpl.get(3);
//        System.out.println("product: " + product.toString());

//        Orders orderSave = new Orders();
//        orderSave.setOrderStatus("Temp");
//        orderSave.setOrderDate(new Date());
//        orderSave.setOrderAmount(111.11);
//        orderSave.setCardKey(paymentCardDetailsDAOImpl.get(1).get());
//        orderSave.setUserKey(userDAOImpl.get(1).get());
//        orderSave.setStoreKey(storDAOImpl.get(1).get());
//        orderSave = orderDAOImpl.save(orderSave);

//        OrderDetails orderLineItems = new OrderDetails();
//        orderLineItems.setOrderKey(orderSave);
//        orderLineItems.setProductKey(product.get());
//        orderLineItems.setOrderQuantity(95);
//        orderLineItems.setNetPrice(123.9);
//        orderDetailsDAO.save(orderLineItems);

//        orderSave.setOrderStatus("Confirmed");
//        orderDAOImpl.update(orderSave);

//        Optional<Size> size = sizeDAOImpl.get(1);
//        System.out.println("size: " + size.toString());

//        Optional<ProductCatalog> productCatalog = productCatalogDAOImpl.get(1);
//        System.out.println("productCatalog: " + productCatalog.toString());

//        List<Orders> ordersList = orderDAOImpl.findOrdersByUserKey(1);
//        for (Orders ord: ordersList) {
//            System.out.println("orderList: " + ord.toString());
//        }
	}
}
