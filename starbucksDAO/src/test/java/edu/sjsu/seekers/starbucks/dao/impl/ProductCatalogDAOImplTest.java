package edu.sjsu.seekers.starbucks.dao.impl;

import edu.sjsu.seekers.starbucks.dao.*;
import edu.sjsu.seekers.starbucks.dao.repository.ProductCatalogRepository;
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

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("unit-test")
public class ProductCatalogDAOImplTest {




    @Autowired
    SizeRepository sizeRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductCatalogRepository productCatalogRepository;

    @Autowired
    ProductCatalogDAO productCatalogDAO;

    Size sizeSmall,sizeMedium,sizeLarge;
    ProductCatalog productCatalog;
    Products products;

    @Before
    public void setup()
    {
        System.out.println("************running ProductCatalogDAOImplTest setup************");
        createTestSizes();
        createTestProduct();
        createTestProductCatalog();
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

    private void createTestSizes() {
        sizeSmall = new Size();
        sizeSmall.setSizeName("Small");
        sizeSmall = sizeRepository.save(sizeSmall);

        sizeMedium = new Size();
        sizeMedium.setSizeName("Medium");
        sizeMedium = sizeRepository.save(sizeMedium);

        sizeLarge = new Size();
        sizeLarge.setSizeName("Large");
        sizeLarge = sizeRepository.save(sizeLarge);
    }

    private void createTestProductCatalog() {
        productCatalog = new ProductCatalog();
        productCatalog.setPrice(1.11);
        productCatalog.setProducts(products);
        productCatalog.setRewards(1.00);
        productCatalog.setSize(sizeLarge);
        productCatalogRepository.save(productCatalog);

    }



    @Test
    public void getProductCatalog() throws SQLException {
        System.out.println("************running getProductCatalog test************");
        ProductCatalog pc = productCatalogDAO.getProductCatalogByIdAndSize(products.getProductKey(),sizeLarge.getSizeKey());
        assertNotNull(pc);
    }

}