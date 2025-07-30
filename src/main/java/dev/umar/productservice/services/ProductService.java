package dev.umar.productservice.services;

import dev.umar.productservice.models.Product;

import java.util.List;

public interface ProductService {
    public Product getAllProducts();

    Product createProduct(String title,
                          String description,
                          String image,
                          String category,
                          double price);

    public Product getSingleProduct(Long id);
    List<Product> getAllProducts(int page, int size);
}
