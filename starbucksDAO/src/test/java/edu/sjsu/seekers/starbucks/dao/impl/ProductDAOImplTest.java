package edu.sjsu.seekers.starbucks.dao.impl;

import edu.sjsu.seekers.starbucks.dao.*;
import edu.sjsu.seekers.starbucks.dao.repository.ProductRepository;
import edu.sjsu.seekers.starbucks.dao.repository.SizeRepository;
import edu.sjsu.seekers.starbucks.dao.repository.StoresRepository;
import edu.sjsu.seekers.starbucks.model.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("unit-test")
public class ProductDAOImplTest {


    @Autowired
    OrderDAO orderDAO;

    @Autowired
    OrderDetailsDAO orderDetailsDAO;

    @Autowired
    StoresDAO storesDAO;

    @Autowired
    UserDAO userDAO;

    @Autowired
    AddressDAO addressDAO;

    @Autowired
    SizeRepository sizeRepository;

    @Autowired
    StoresRepository storesRepository;

    @Autowired
    PaymentCardDetailsDAO paymentCardDetailsDAO;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductDAO productDAO;

    Orders order,order1;
    User user;
    Address address;
    Stores stores;
    OrderDetails orderDetails;
    Size sizeSmall,sizeMedium,sizeLarge;
    PaymentCardDetails paymentCardDetails;
    Products products;

    @Before
    public void setup()
    {
        System.out.println("************running setup for ProductDAOImplTest************");
        createTestProduct();

    }

    private void createTestProduct()
    {
        products = new Products();
        products.setActive(true);
        products.setProductDescription("test description");
        products.setProductImageLink("test link");
        products.setProductName("test product");
        products.setProductStatus(true);
        products = productRepository.save(products);
    }

    @Test
    public void getProduct() throws SQLException {
        System.out.println("************running getProduct test************");
        Optional<Products> productsGet = productDAO.get(products.getProductKey());
        assertNotNull(productsGet.get());

    }
}