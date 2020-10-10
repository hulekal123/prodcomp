package com.relayr.productcomparision.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.relayr.productcomparision.service.ProductService;
import com.relayr.productcomparision.model.Product;
import javax.validation.Valid;

import java.util.List;

@Validated
@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @PostMapping("/upload")
    public void importProducts(@RequestBody List<@Valid Product> products) {
        for(Product product : products) {
            productService.importProducts(product);
        }
    }

    @GetMapping(params = {"category", "name"})
    public List<Product> getProductsByCategoryandName(@RequestParam("category") String category,
                                     @RequestParam("name") String name) {
        return productService.getProductsByCategoryandName(category, name);
    }
}
