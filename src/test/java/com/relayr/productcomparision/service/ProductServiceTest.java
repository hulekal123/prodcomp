package com.relayr.productcomparision.service;

import com.relayr.productcomparision.model.Product;
import com.relayr.productcomparision.repository.ProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.BDDMockito.given;


@RunWith(SpringJUnit4ClassRunner.class)
public class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;

    private Product mockedProduct;

    @Before
    public void setup() {

        productService = new ProductService(productRepository);

        mockedProduct = new Product();
        mockedProduct.setId("2");
        mockedProduct.setName("mobile#");
        mockedProduct.setCategory("electronics#");
        mockedProduct.setVendor("Amazon");
        mockedProduct.setPrice(420);
        mockedProduct.setRating(4);
    }

    @Test
    public void shouldSaveProduct() {

        assertThatCode(() -> productService.importProducts(mockedProduct));
    }


    @Test
    public void getProductsByCategoryandName() {

        given(productRepository.findByCategoryAndName("electronics#","mobile#"))
                .willReturn(asList(mockedProduct));

        //When
        List<Product> productList = productService.getProductsByCategoryandName("electronics#","mobile#");

        //Then
        assertThat(productList).containsExactly(mockedProduct);
    }
}
