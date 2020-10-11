package com.relayr.productcomparision.controller;

import com.relayr.productcomparision.model.Product;
import com.relayr.productcomparision.service.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.Arrays;


@RunWith(SpringRunner.class)
@WebMvcTest(controllers = ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProductService productService;

    private Product mockedProduct;

    @Before
    public void onStartUp() throws Exception {
        mockedProduct = new Product();
        mockedProduct.setId("1");
        mockedProduct.setName("game#");
        mockedProduct.setCategory("software#");
        mockedProduct.setVendor("Amazon");
        mockedProduct.setPrice(450);
        mockedProduct.setRating(4);
    }

    @Test
    public void shouldHandleConstraintViolationException() throws Exception {
        mvc.perform(post("/products/upload")
                .contentType(MediaType.APPLICATION_JSON)
                .content("[{\"name\":\"\",\"category\":\"\"}]"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(org.hamcrest.Matchers.startsWith("Validation errors")));
    }

    @Test
    public void shouldImportProduct() throws Exception {
        mvc.perform(post("/products/upload")
                .contentType(MediaType.APPLICATION_JSON)
                .content("[{\"name\":\"lipstick\",\"category\":\"Cosmetics\"}]"))
                .andExpect(status().isOk());
    }

    @Test
    public void getProductsBycategoryNameandUser() throws Exception {
        doReturn(Arrays.asList(mockedProduct))
                .when(productService)
                .getProductsByCategoryandName(mockedProduct.getCategory(), mockedProduct.getName());

        mvc.perform(get("/products")
                .param("category", mockedProduct.getCategory())
                .param("name", mockedProduct.getName()))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"products\":[{\"id\":\"1\",\"name\":\"game#\",\"category\":\"software#\",\"vendor\":\"Amazon\",\"price\":450.0,\"rating\":4}]}"));

    }

}
