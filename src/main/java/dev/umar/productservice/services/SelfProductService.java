package dev.umar.productservice.services;

import dev.umar.productservice.exceptions.ProductNotFoundException;
import dev.umar.productservice.models.Category;
import dev.umar.productservice.models.Product;
import dev.umar.productservice.repositories.CategoryRepository;
import dev.umar.productservice.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("selfProductService")
public class SelfProductService implements ProductService {
    ProductRepository productRepository;
    CategoryRepository categoryRepository;

    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product createProduct(String title, String description, String image, String category, double price) {
        Product product = new Product();
        product.setTitle(title);
        product.setDescription(description);
        product.setPrice(price);
        product.setImageUrl(image);
        Category category1 = categoryRepository.findByTitle(category);
        if (category1 == null) {
            category1 = new Category();
            category1.setTitle(category);
            category1 =  categoryRepository.save(category1);
        }
        //if product exists then what?
        product.setCategory(category1);
        Product saveProduct = productRepository.save(product);
        return saveProduct;
    }

    @Override
    public Product getSingleProduct(Long id) throws ProductNotFoundException {
        if (id == null || id < 0) {
            throw new IllegalArgumentException("Product Id must be positive");
        }
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()) {
            throw new ProductNotFoundException("product with id " + id + " is not exist");
        }
        return productOptional.get();
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product deleteProduct(Long id) throws ProductNotFoundException {
        if(id == null || id <= 0){
            throw new ProductNotFoundException("product id must be positive");
        }
        Optional<Product> productOptional = productRepository.findById(id);
        if(productOptional.isEmpty()){
            throw new ProductNotFoundException("product with id " + id + " is not exist");
        }
        productRepository.deleteById(id);
        return productOptional.get();
    }

    @Override
    public Product updateProduct(Long id , Product product) throws ProductNotFoundException {
        if(id == null || id <= 0){
            throw new ProductNotFoundException("product id must be positive");
        }
        Product existingProduct = productRepository.findById(id).orElseThrow(
                () -> new ProductNotFoundException("product with id " + id + " is not exist"));
        existingProduct.setTitle(product.getTitle());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setImageUrl(product.getImageUrl());
        String categoryTitle = product.getCategory().getTitle();
        Category category = categoryRepository.findByTitle(categoryTitle);
        if(category == null){
            category = new Category();
            category.setTitle(categoryTitle);
            categoryRepository.save(category);
        }
        existingProduct.setCategory(category);
        return productRepository.save(existingProduct);
    }
}
