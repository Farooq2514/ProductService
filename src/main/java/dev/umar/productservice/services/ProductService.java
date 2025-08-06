package dev.umar.productservice.services;

import dev.umar.productservice.exceptions.ProductNotFoundException;
import dev.umar.productservice.models.Product;

import java.util.List;

public interface ProductService {
    //public Product getAllProducts();

    Product createProduct(String title,
                          String description,
                          String image,
                          String category,
                          double price);

    public Product getSingleProduct(Long id) throws ProductNotFoundException;
   public  List<Product> getAllProducts();
   public Product deleteProduct(Long id) throws ProductNotFoundException;
   public Product updateProduct(Long id , Product product)throws ProductNotFoundException;
}
