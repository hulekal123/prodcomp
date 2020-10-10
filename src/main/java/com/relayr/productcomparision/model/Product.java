package com.relayr.productcomparision.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import java.util.Objects;

@Document(collection = "Products")
@Getter
@Setter
public class Product{
    
    @Id
    private String id;

    @NotEmpty(message = "Product name cannot be empty.")
    private String name;

    @NotEmpty(message = "Product category cannot be empty.")
    private String category;

    private float price;

    private int rating;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}