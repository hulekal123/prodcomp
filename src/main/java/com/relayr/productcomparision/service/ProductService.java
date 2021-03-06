package com.relayr.productcomparision.service;

import com.relayr.productcomparision.model.Product;
import com.relayr.productcomparision.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public void importProducts(Product product) {
        productRepository.save(product);
    }

    public List<Product> getProductsByCategoryandName(String category, String name) {
        return productRepository.findByCategoryAndName(category,name);
    }
}