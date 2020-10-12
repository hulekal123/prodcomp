package com.relayr.productcomparision.repository;

import java.util.List;

import com.relayr.productcomparision.model.Product;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;



@DataMongoTest
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    private Product product;


    @Before
    public void setUp() {
        product= new Product();
        product.setId("3");
        product.setName("eyeliner#");
        product.setCategory("cosmetics#");
        product.setVendor("P-tech");
        product.setPrice(42);
        product.setRating(5);
    }


    @Test
    public void shouldFindProductWithMatchingParameters() {
        productRepository.save(product);

        List<Product> products = productRepository.findByCategoryAndName("cosmetics#", "eyeliner#");

        assertThat(products).contains(product);
    }


    @Test
    public void shouldNotFindProductsWithoutMatchingParameters() {
        productRepository.save(product);

        List<Product> products = productRepository.findByCategoryAndName("cosmetics#", "kajal#");

        assertThat(products).isEmpty();
    }
}

