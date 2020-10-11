package com.relayr.productcomparision.repository;

import com.relayr.productcomparision.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {

  public List<Product> findByCategoryAndName(String category, String name);

  public Product findByName(String name);
}